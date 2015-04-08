package com.yarenty.spark.workers;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

public class WorkerServiceTest{
	final static Logger LOG = Logger.getLogger(WorkerServiceTest.class);

	
	WorkerService service;
	
	@Before
	public void initialize() {
	

		LOG.getParent().setLevel(Level.ALL);
		LOG.getParent().addAppender(new ConsoleAppender(new SimpleLayout()));
		service = WorkerService.getInstance();
		
	}
	
	@Test
	public void simpleMultiplicity(){
		Float[] a = new Float[] {1.0f, 2.0f, 3.0f};
		Float[] b = new Float[] {1.0f, 2.0f, 3.0f};
		Float[] c = new Float[] {1.0f, 4.0f, 9.0f};
		
		Float[] out = service.exampleMultiplication(a, b);

		for (int i=0; i<a.length; i++) {
			System.out.print(out[i]+", ");
		}

		for (int i=0; i<a.length; i++) {
			assertEquals("ID:"+i,out[i],c[i]);
		}
		
		
		
	}
	
	
}