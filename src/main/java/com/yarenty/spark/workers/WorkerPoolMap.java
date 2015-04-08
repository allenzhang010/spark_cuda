package com.yarenty.spark.workers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yarenty.spark.workers.cuda.CudaWorkerPool;
import com.yarenty.spark.workers.java.CPUWorkerPool;

/**
 * 
 * @author yarenty
 *
 */
public class WorkerPoolMap {
	final static Logger LOG = Logger.getLogger(WorkerPoolMap.class);

	private static WorkerPoolMap workersPoolMap = new WorkerPoolMap();
	
	private Map<WorkerType,WorkerPool> poolMap;
	
	private WorkerPoolMap() {
		poolMap = new HashMap<WorkerType,WorkerPool>();
	}

	/**
	 * Initialize optimal pool 
	 * @param PROCESSORS_NUM - number of available processors
	 * @param GPU_NUM - number of GPU devices
	 */
	public void initializePools(final int PROCESSORS_NUM, final int GPU_NUM) {
		if (poolMap.containsKey(WorkerType.CUDA)) { 	
			poolMap.remove(WorkerType.CUDA);
		}
		if (poolMap.containsKey(WorkerType.CPU)) { 	
			poolMap.remove(WorkerType.CPU);
		}
		poolMap.put(WorkerType.CUDA, new CudaWorkerPool(GPU_NUM));
		poolMap.put(WorkerType.CPU, new CPUWorkerPool(PROCESSORS_NUM-GPU_NUM));
	}
	
	public static WorkerPoolMap getInstance() {
		return workersPoolMap;
	}
	
	public WorkerPool getWorkerPool(WorkerType type) {
	 if (type!= null) {
		 return poolMap.get(type);
	 } else {
		 return null;
	 }
	}
	
}


