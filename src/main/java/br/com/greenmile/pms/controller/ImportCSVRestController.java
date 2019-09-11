package br.com.greenmile.pms.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/v1/imports")
public class ImportCSVRestController {

    private static final String FOLDER = "uploads";

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @PostMapping(value = "/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importCSV(@RequestParam("file") MultipartFile multipart) {
        try {
            String path = new ClassPathResource(FOLDER + "/").getURL().getPath();
            File file = new File(path + multipart.getOriginalFilename());

            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(multipart.getInputStream(), outputStream);
            outputStream.flush();
            outputStream.close();

            this.jobLauncher.run(this.job, new JobParametersBuilder()
                    .addString("fullPathFileName", file.getAbsolutePath())
                    .toJobParameters());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
