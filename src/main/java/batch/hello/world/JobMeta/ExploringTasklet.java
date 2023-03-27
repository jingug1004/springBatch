package batch.hello.world.JobMeta;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

public class ExploringTasklet implements Tasklet {

    private JobExplorer explorer;

    public ExploringTasklet(JobExplorer explorer) {
        this.explorer = explorer;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String jobName = chunkContext.getStepContext().getJobName();

        List<JobInstance> instances = explorer.getJobInstances(jobName, 0, Integer.MAX_VALUE);

        System.out.println(
                String.format("l~ There are %d job instances for the job %s",
                        instances.size(),
                        jobName));

        System.out.println("l~ They have the following results");
        System.out.println("l~ *******************************");

        for (JobInstance instance : instances) {
            List<JobExecution> jobExecutions =
                    this.explorer.getJobExecutions(instance);

            System.out.println(
                    String.format("l~ Instance %d had %d executions",
                            instance.getInstanceId(),
                            jobExecutions.size()));

            for (JobExecution jobExecution : jobExecutions) {

                System.out.println(
                        String.format("l~ \t Execution %d resulted in Exit Status %s",
                                jobExecution.getId(),
                                jobExecution.getExitStatus()));
            }
        }

        return RepeatStatus.FINISHED;
    }
}