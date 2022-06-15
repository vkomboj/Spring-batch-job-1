package com.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.core.io.FileSystemResource;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import org.springframework.core.io.Resource;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import com.batch.listener.JobCompletionListener;

import com.batch.model.User;
//import com.batch.processor.UserItemProcessor;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            ItemReader<User> itemReader,
            ItemProcessor<User, User> itemProcessor,
            ItemWriter<User> itemWriter
	) {

			Step step = stepBuilderFactory.get("ETL-file-load")
						.<User, User>chunk(100)
						.reader(itemReader)
						.processor(itemProcessor)
						.writer(itemWriter)
						.build();


			return jobBuilderFactory.get("ETL-Load")
					.incrementer(new RunIdIncrementer())
					.start(step)
					.build();
	}
	
	//Create Reader using FlatFileItemReader to define file properties
	@Bean
    public FlatFileItemReader<User> itemReader() {
        // create FlatFileItemReader
        FlatFileItemReader<User> reader = new FlatFileItemReader<User>();
       
        // skip header
        reader.setLinesToSkip(1);
        reader.setResource(new FileSystemResource("//hfdvvcdshdb01.vm.itg.corp.us.shldcorp.com/training/Contacts.csv"));
       
        reader.setName("CSV-Reader");
        reader.setLineMapper(lineMapper());
        return reader;
        
	}
	
	@Bean
	public LineMapper<User> lineMapper() {
		DefaultLineMapper<User> lineMapper = new DefaultLineMapper<User>();
		
		// tokenizer for delimited file
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(";");
		tokenizer.setNames(new String[] { "userId", "firstName", "lastName", "phone", "mailId" });
		tokenizer.setStrict(false);
		
		BeanWrapperFieldSetMapper<User> mapper = new BeanWrapperFieldSetMapper<User>();
		mapper.setTargetType(User.class);
		
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(mapper);
		
		return lineMapper;
	}
	
//    @Bean
//	public UserItemProcessor processor() {
//		return new UserItemProcessor();
//	}
//	
//	@Bean
//	public JdbcBatchItemWriter<User> writer(DataSource dataSource) {
//	    return new JdbcBatchItemWriterBuilder<User>()
//		  .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>())
//		  .sql("INSERT INTO User (userId, firstName, lastName, phone, mailId) VALUES (:userId, :firstName, :lastName, :phone, :mailId)")
//		  .dataSource(dataSource)
//		  .build();
//	}

//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://hfdvvcdshdb01.vm.itg.corp.us.shldcorp.com/training");
//		dataSource.setUsername("training_rw");
//		dataSource.setPassword("Tr@in$n%R&W12!");
//		
//		return dataSource;
//	}
	
//	@Bean
//	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//	    return new JdbcTemplate(dataSource);
//	}
//
//	@Override
//	public void setDataSource(DataSource dataSource) {
//	}
		
}
