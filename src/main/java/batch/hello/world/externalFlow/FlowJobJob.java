//package batch.hello.world.externalFlow;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//
//@EnableBatchProcessing
//@SpringBootApplication
//public class FlowJobJob {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Tasklet loadStockFile() {
//        return (contribution, chunkContext) -> {
//            System.out.println("l~ The stock file has been loaded l~ ");
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//    @Bean
//    public Tasklet loadCustomerFile() {
//        return (contribution, chunkContext) -> {
//            System.out.println("l~ The customer file has been loaded l~ ");
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//    @Bean
//    public Tasklet updateStart() {
//        return (contribution, chunkContext) -> {
//            System.out.println("l~ The start has been updated l~ ");
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//    @Bean
//    public Tasklet runBatchTasklet() {
//        return (contribution, chunkContext) -> {
//            System.out.println("l~ The batch has been run l~ ");
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//    @Bean
//    public Job preProcessingJob() {
//        return this.jobBuilderFactory.get("preProcessingJob04")
//                .start(loadFileStep())
//                .next(loadCustomerStep())
//                .next(updateStartStep())
//                .build();
//    }
//
//    @Bean
//    public Job conditionalStepLogicJob() {
//        return this.jobBuilderFactory.get("conditionalStepLogicJob04")
//                .start(initializeBatch())
//                .next(runBatch())
//                .build();
//    }
//
//    @Bean
//    public Step initializeBatch() {
//        return this.stepBuilderFactory.get("initializeBatch")
//                .job(preProcessingJob())
//                .parametersExtractor(new DefaultJobParametersExtractor())
//                .build();
//    }
//
//    @Bean
//    public Step loadFileStep() {
//        return this.stepBuilderFactory.get("loadFileStep")
//                .tasklet(loadStockFile())
//                .build();
//    }
//
//    @Bean
//    public Step loadCustomerStep() {
//        return this.stepBuilderFactory.get("loadCustomerStep")
//                .tasklet(loadCustomerFile())
//                .build();
//    }
//
//    @Bean
//    public Step updateStartStep() {
//        return this.stepBuilderFactory.get("updateStartStep")
//                .tasklet(updateStart())
//                .build();
//    }
//
//    @Bean
//    public Step runBatch() {
//        return this.stepBuilderFactory.get("runBatch")
//                .tasklet(runBatchTasklet())
//                .build();
//    }
//
//
//    public static void main(String[] args) {
//        SpringApplication.run(FlowJobJob.class, args);
//    }
//
//}