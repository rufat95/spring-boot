package com.ingress.fileuploadms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableFeignClients
@SpringBootApplication
public class FileUploadMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadMsApplication.class, args);
    }

}
