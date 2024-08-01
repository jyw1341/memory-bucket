package com.memorybucket.batch;

import com.memorybucket.core.domain.DeletedObject;
import com.memorybucket.core.repository.DeletedObjectRepository;
import com.memorybucket.storage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    @Bean
    public Job objectDeleteJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("objectDeleteJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step objectDeleteStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<DeletedObject> objectDeleteItemReader,
            ItemProcessor<DeletedObject, DeletedObject> objectDeleteItemProcessor,
            ItemWriter<DeletedObject> objectDeleteItemWriter
    ) {
        return new StepBuilder("objectDeleteStep", jobRepository)
                .<DeletedObject, DeletedObject>chunk(100, transactionManager)
                .reader(objectDeleteItemReader)
                .processor(objectDeleteItemProcessor)
                .writer(objectDeleteItemWriter)
                .build();
    }

    @Bean
    public ItemReader<DeletedObject> objectDeleteItemReader(DeletedObjectRepository deletedObjectRepository) {
        RepositoryItemReader<DeletedObject> itemReader = new RepositoryItemReader<>();
        itemReader.setName("objectItemReader");
        itemReader.setRepository(deletedObjectRepository);
        itemReader.setMethodName("findAll");
        itemReader.setPageSize(100);

        HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);
        itemReader.setSort(sorts);

        return itemReader;
    }

    @Bean
    public ItemProcessor<DeletedObject, DeletedObject> objectDeleteItemProcessor(ObjectStorageService objectStorageService) {
        return new DeletedObjectProcessor(objectStorageService);
    }

    @Bean
    public ItemWriter<DeletedObject> objectDeleteItemWriter(DeletedObjectRepository deletedObjectRepository) {
        RepositoryItemWriter<DeletedObject> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(deletedObjectRepository);
        itemWriter.setMethodName("delete");

        return itemWriter;
    }
}
