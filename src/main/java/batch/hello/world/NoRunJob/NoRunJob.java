package batch.hello.world.NoRunJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;


@EnableBatchProcessing
@SpringBootApplication
public class NoRunJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("l~ step1 ran!");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    public static void main(String[] args) {
//        SpringApplication.run(NoRunJob.class, args);
        SpringApplication application = new SpringApplication(NoRunJob.class);

        Properties properties = new Properties();
        properties.put("spring.batch.job.enabled", false);

        application.setDefaultProperties(properties);

        application.run(args);
    }

}