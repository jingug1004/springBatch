package batch.hello.world.stepFlow;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@EnableBatchProcessing
@SpringBootApplication
public class ConditionalJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Tasklet passTasklet() {
        return (contribution, chunkContext) -> {
//            return RepeatStatus.FINISHED;
            throw new RuntimeException("l~ Causing a failure");
        };
    }

    @Bean
    public Tasklet successTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("l~ Success! ");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Tasklet failTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("l~ Failure! ");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("conditionalJob")

                .start(firstStep())

                .on("FAILED")
                // .end .fail .stopAndRestart
                .stopAndRestart(successStep())
                .from(firstStep())
                .on("*")
                .to(successStep())
                .end()
//                .start(firstStep())
//                .next(decider())
//                .from(decider())
//
//                .on("FAILED")
//                .to(failureStep())
//                .from(decider())
//
//                .on("*")
//                .to(successStep())
//                .end()

                .build();
    }

    @Bean
    public Step firstStep() {
        return this.stepBuilderFactory.get("firstStep")
                .tasklet(passTasklet())
                .build();
    }

    @Bean
    public Step successStep() {
        return this.stepBuilderFactory.get("successStep")
                .tasklet(successTasklet())
                .build();
    }

    @Bean
    public Step failureStep() {
        return this.stepBuilderFactory.get("failureStep")
                .tasklet(failTasklet())
                .build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new RandomDecider();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConditionalJob.class, args);
    }

}