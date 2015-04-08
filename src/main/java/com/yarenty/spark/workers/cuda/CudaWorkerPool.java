package com.yarenty.spark.workers.cuda;

import org.apache.log4j.Logger;

import com.yarenty.spark.workers.AbstractWorkerPool;
import com.yarenty.spark.workers.Worker;
import com.yarenty.spark.workers.WorkerPool;

/**
 * @author yarenty
 */
public class CudaWorkerPool extends AbstractWorkerPool implements WorkerPool {
	
	final static Logger LOG = Logger.getLogger(CudaWorkerPool.class);

	public CudaWorkerPool(final int numWorkers) {
		super(numWorkers);
		
		LOG.info("Trying to create CUDA powered workers pool with "+ numWorkers + " workers");
		Worker[] workers = new Worker[numWorkers];
		
		for (int i=0; i<numWorkers; i++) {
			workers[i] = new CudaWorker(i);
			LOG.info("Added CUDA worker ["+i+"] to the pool.");
		}
		
		addWorkers(workers);
		
	}
}
