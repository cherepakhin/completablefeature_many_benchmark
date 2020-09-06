package ru.perm.v.completablefeature_many_benchmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.perm.v.completablefeature_many_benchmark.config.TestControllerConfig;

@SpringBootApplication
@Import(value = {
        TestControllerConfig.class
}
)
public class CompletablefeatureManyBenchmarkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompletablefeatureManyBenchmarkApplication.class, args);
    }

}
