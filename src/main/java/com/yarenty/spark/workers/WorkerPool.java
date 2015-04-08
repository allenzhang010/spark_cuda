package com.yarenty.spark.workers;

/**
 * 
 * @author yarenty
 *
 */
public interface WorkerPool {
	
	Worker getWorker();
	
	public <T extends Number> void prepareStaticData(final T[] arr, final String name, final GPUType type);

	void returnWorker(Worker worker);

	boolean canWork();

}
