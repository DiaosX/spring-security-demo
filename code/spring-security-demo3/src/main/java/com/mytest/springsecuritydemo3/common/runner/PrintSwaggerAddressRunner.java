package com.mytest.springsecuritydemo3.common.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PrintSwaggerAddressRunner implements ApplicationRunner {

    @Value("${server.port}")
    private int serverPort;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Swagger-Address: http://localhost:" + serverPort + "/swagger-ui.html");
    }
}
