
package com.tc.pes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tc.pes.provider.dao")//这里mapper是你的mybatis的mapper目录。  classpath:ibatis
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
