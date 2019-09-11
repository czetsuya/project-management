package br.com.greenmile.pms.common.config;

import br.com.greenmile.pms.entity.PMSFile;
import br.com.greenmile.pms.entity.User;
import br.com.greenmile.pms.processor.UserItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
public class JobConfig {

    private static final int PROJECT_NAME_INDEX = 0;
    private static final int PLANNED_START_INDEX = 1;
    private static final int PLANNED_END_INDEX = 2;
    private static final int PM_INDEX = 3;
    private static final int PM_EMAIL_INDEX = 4;
    private static final int PM_SKILLS_INDEX = 5;
    private static final int EMPLOYEE_NAME_INDEX = 6;
    private static final int EMPLOYEE_EMAIL_INDEX = 7;
    private static final int EMPLOYEE_TEAM_INDEX = 8;
    private static final int EMPLOYEE_SKILLS_INDEX = 9;

    private static final String USER_NAME = "employeeName";
    private static final String USER_EMAIL = "employeeEmail";
    private static final String USER_TEAM = "team";
    private static final String USER_SKILLS = "employeeSkills";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(ItemReader<PMSFile> reader) {
        return this.jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .flow(step(reader))
                .end()
                .build();
    }

    @Bean
    public Step step(ItemReader<PMSFile> reader) {
        return this.stepBuilderFactory.get("step")
                .<PMSFile, User> chunk(2)
                .reader(reader)
                .processor(processor())
                .writer(write())
                .build();
    }

    @Bean
    @Scope(value = "step", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public FlatFileItemReader<PMSFile> reader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile) {
        FlatFileItemReader<PMSFile> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new FileSystemResource(pathToFile));
        reader.setLineMapper(new DefaultLineMapper<PMSFile>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(USER_NAME, USER_EMAIL, USER_SKILLS);
                setIncludedFields(EMPLOYEE_NAME_INDEX, EMPLOYEE_EMAIL_INDEX, EMPLOYEE_SKILLS_INDEX);
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<PMSFile>() {{
                setTargetType(PMSFile.class);
            }});
        }});
        return reader;
    }

    @Bean
    public UserItemProcessor processor() {
        return new UserItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<User> write() {
        String sql = "INSERT INTO users (name, email) VALUES (:name, :email)";
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setDataSource(this.dataSource);
        writer.setSql(sql);
        return writer;
    }
}
