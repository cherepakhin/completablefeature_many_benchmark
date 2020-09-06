package ru.perm.v.completablefeature_many_benchmark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.v.completablefeature_many_benchmark.beans.TestExecutor;

@Configuration
public class TestControllerConfig {

    @Bean
    public TestExecutor testExecutor() {
        return new TestExecutor(100);
    }

}
