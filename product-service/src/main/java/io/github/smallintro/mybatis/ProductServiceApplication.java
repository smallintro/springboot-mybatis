package io.github.smallintro.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ProductServiceApplication {

    private Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);

    @Value("${server.port:8080}")
    String serverPort;

    @PostConstruct
    public void afterInit() {
        logger.info("access service at http://localhost:{}/swagger-ui/index.html", serverPort);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
