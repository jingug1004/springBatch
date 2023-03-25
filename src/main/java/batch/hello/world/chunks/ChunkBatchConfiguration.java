//package batch.hello.world.chunks;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
//import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
//import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//@EnableBatchProcessing
//@SpringBootApplication
//public class ChunkBatchConfiguration {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return this.jobBuilderFactory.get("chunckJob")
//                .start(step1())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return this.stepBuilderFactory.get("step1")
//                .<String, String>chunk(10)
//                .reader(itemReader(null))
//                .writer(itemWriter(null))
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<String> itemReader(
//            @Value("#{jobParameters['inputFile']}") org.springframework.core.io.Resource inputfile
//    ) {
//
//        return new FlatFileItemReaderBuilder<String>()
//                .name("itemReader")
//                .resource(inputfile)
//                .lineMapper(new PassThroughLineMapper())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemWriter<String> itemWriter(
//            @Value("#{jobParameters['outputFile']}") org.springframework.core.io.Resource outputfile
//    ) {
//
//        return new FlatFileItemWriterBuilder<String>()
//                .name("itemWriter")
//                .resource(outputfile)
//                .lineAggregator(new PassThroughLineAggregator<>())
//                .build();
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(ChunkBatchConfiguration.class, args);
//    }
//}