package com.lambda.essentialism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaAuditing
@EnableWebMvc
@SpringBootApplication
public class EssentialismApplication {

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(
      EssentialismApplication.class,
      args
    );

    DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean(
      "dispatcherServlet"
    );
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
  }

}

