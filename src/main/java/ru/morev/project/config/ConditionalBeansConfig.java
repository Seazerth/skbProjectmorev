package ru.morev.project.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

@Slf4j
@Configuration
public class ConditionalBeansConfig {

    @Bean
    @Profile("test")
    public String testOnlyBean() {
        log.info("🔨 Бин testOnlyBean активен (только в test)");
        return "TestOnlyBean";
    }

    @Bean
    @ConditionalOnBean(name = "testOnlyBean")
    public String dependentOnTestOnlyBean() {
        log.info("📎 dependentOnTestOnlyBean создан — testOnlyBean есть");
        return "DependentBean";
    }

    @Bean
    @ConditionalOnProperty(name = "app.env-variable", havingValue = "not-default")
    public String envBasedBean() {
        log.info("🌍 Бин envBasedBean активен (env-переменная задана)");
        return "EnvBasedBean";
    }
}
