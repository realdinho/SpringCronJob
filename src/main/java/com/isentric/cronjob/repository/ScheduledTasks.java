package com.isentric.cronjob.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks
{
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	// execute a task 2 in 2 seconds 
	@Scheduled(fixedRate = 2000)
	public void scheduleTaskWithFixedRate() 
	{
		logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
		
		// printout the current thread 
		logger.info("Current Thread : {}", Thread.currentThread().getName());
	}

	// execute a task with a delay of 2 seconds
	@Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() 
    {
    	logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    	
    	try 
    	{
			TimeUnit.SECONDS.sleep(5);
		} 
    	catch (InterruptedException ex) 
    	{
    		logger.error("Ran into an error {}", ex);
    		
    		throw new IllegalStateException(ex);
		}
    }

	// after 5 seconds, execute a task with 2 in 2 seconds
	@Scheduled(fixedRate = 2000, initialDelay = 5000)
//	@Scheduled()
    public void scheduleTaskWithInitialDelay() 
    {
    	logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

	//  execute a task every 1 minute 
	// second -- minute -- hour -- day -- days of week 
	// * = matches any -- ? = no specific value -- */X = every X
	@Scheduled(cron = "0 * * * * ?") 
    public void scheduleTaskWithCronExpression() 
    {
		logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }
	
}
