package com.yarenty.spark.workers;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

public class AbstractWorkerPool implements WorkerPool {

	final static Logger LOG = Logger.getLogger(AbstractWorkerPool.class);

	LinkedBlockingQueue<Worker> workersQueue;
	private int capacity;

	public AbstractWorkerPool(int capacity) {
		LOG.info("Pool initialization:" + capacity);
		this.capacity = capacity;
		if (capacity > 0) {
			workersQueue = new LinkedBlockingQueue<Worker>(capacity);
		}
	}

	protected void addWorkers(Worker[] workers) {
		LOG.info("..adding workers");
		workersQueue.addAll(Arrays.asList(workers));
	}

	@Override
	public boolean canWork() {
		LOG.info("SIZE::" + workersQueue.size());
		return workersQueue.size() > 0;
	}

	@Override
	public Worker getWorker() {
		Worker selectedWorker = null;
		if (capacity > 0) {
			try {
				selectedWorker = workersQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
				LOG.error("... this should wait " + e, e);
			}
		} else {
			LOG.error("Sorry, no workers in pool.");
		}
		return selectedWorker;
	}

	@Override
	public void returnWorker(Worker worker) {
		if (worker != null) {
			worker.clean();
			workersQueue.add(worker);
		}

	}

	@Override
	public <T extends Number> void prepareStaticData(final T[] arr,
			final String name, final GPUType type) {

		LOG.info("preparind DB in all workers");
		for (Worker worker : workersQueue) {
			worker.prepareStaticData(arr, name, type);
		}

	}

}
