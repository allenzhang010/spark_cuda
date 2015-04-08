package com.yarenty.spark.workers.java;

import org.apache.log4j.Logger;

import com.yarenty.spark.workers.AbstractWorkerPool;
import com.yarenty.spark.workers.Worker;
import com.yarenty.spark.workers.WorkerPool;
import com.yarenty.spark.workers.WorkerType;

/**
 * @author yarenty
 */
public class CPUWorkerPool extends AbstractWorkerPool implements WorkerPool {
	
	final static Logger LOG = Logger.getLogger(CPUWorkerPool.class);

	public CPUWorkerPool(final int numWorkers) {
		super(numWorkers);
		
		LOG.info("Trying to create CPU powered workers pool with "+ numWorkers + " workers");
		Worker[] workers = new Worker[numWorkers];
		
		for (int i=0; i<numWorkers; i++) {
			workers[i] = new CPUWorker(i);
			LOG.info("Added CPU worker ["+i+"] to the pool.");
		}
		
		addWorkers(workers);
		
	}
	
	public WorkerType getWorkerType(){
		return WorkerType.CPU;
	}

}
