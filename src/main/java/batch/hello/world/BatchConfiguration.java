package batch.hello.world;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("job")
                .start(step1())
//                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet(helloWorldTasklet())
                .listener(promotionListener())
                .build();
    }

//    @Bean
//    public Step step2() {
//        this.stepBuilderFactory.get("step2")
//                .tasklet(new GoodByeTasklet())
//                .build();
//    }

    @Bean
    public Tasklet helloWorldTasklet() {
        return new HelloWorld();
    }


    @Bean
    public StepExecutionListener promotionListener() {
        ExecutionContextPromotionListener listener =
                new ExecutionContextPromotionListener();

        listener.setKeys(new String[]{"name"});

        return listener;
    }
}