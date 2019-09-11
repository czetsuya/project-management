package br.com.greenmile.pms.processor;

import br.com.greenmile.pms.entity.PMSFile;
import br.com.greenmile.pms.entity.Skill;
import br.com.greenmile.pms.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<PMSFile, User> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User process(PMSFile item) throws Exception {
        logger.info("Name: {}", item.getEmployeeName());
        logger.info("Email: {}", item.getEmployeeEmail());
        logger.info("Skills: {}", item.getEmployeeSkills());

        User user = new User();
        user.setName(item.getEmployeeName());
        user.setEmail(item.getEmployeeEmail());
        String[] strings = item.getEmployeeSkills().split(",");

        for (String str : strings) {
            Skill skill = new Skill();
            skill.setName(str);
            user.addSkill(skill);
        }
        return user;
    }
}
