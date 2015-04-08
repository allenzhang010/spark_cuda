package com.yarenty.spark.workers.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yarenty.spark.workers.AbstractWorker;
import com.yarenty.spark.workers.GPUType;
import com.yarenty.spark.workers.Kernel;
import com.yarenty.spark.workers.Worker;

//import static scala.collection.JavaConversions.*;

public class CPUWorker extends AbstractWorker implements Worker {
	final static Logger LOG = Logger.getLogger(CPUWorker.class);


	Map<String, Number[]> arrMap = new HashMap<String, Number[]>();
	Map<String, Number> valMap = new HashMap<String, Number>();

	
	
	public CPUWorker(int id) {
		super(id);
		LOG.info("CPU Worker no:" + id + " created.");
	}


	/**
	 * 
	 * Use this or similar function to prepare static data ahead of processing.
	 * Internally it will be created as arrays of float, ints, or doubles. 
	 * 
	 * @param arr
	 * @param name
	 * @param type - possible types : TEXTURE, GLOBAL MEMORY
	 */
	public <T extends Number> void prepareStaticData(final T[] arr, final String name, final GPUType type) {
		arrMap.put(name, arr);
	}

	
	/** 
	 * This is used to set data for calculations.
	 * It could be called several times.
	 * @param arr - contains data
	 * @param name - internal name on GPU side
	 * @param type
	 */
	public <T extends Number> void setArray(final T[] arr, final String name, final GPUType type) {
		arrMap.put(name, arr);
	}

	/**
	 * To send individual values. 
	 * @param val
	 * @param name
	 * @param type
	 */
	public <T extends Number> void setValue(final T val, final String name, final GPUType type) {
		valMap.put(name, val);
	}

	
	/**
	 * To run kernels.
	 * TODO: kernel args!
	 */
	public void runKernel() {
		kernel.run(arrMap, valMap);
	}
	

	/** 
	 * Get output array!
	 *  
	 * @param name
	 * @return
	 */
	public <T extends Number> T[] getArray(final String name){
		return (T[]) arrMap.get(name);
	}

	




	
}
