package com.yarenty.spark.workers;

/**
 * General structure to build on-the-fly CUDA calls.
 *   
 * @author yarenty
 */
public interface Worker {

	/**
	 * Worker ID.
	 * @return
	 */
	int getID();
	
	
	/**
	 * Initialize context.
	 */
	void initialize();

	/**
	 * 
	 * Use this or similar function to prepare static data ahead of processing.
	 * Internally it will be created as arrays of float, ints, or doubles. 
	 * 
	 * @param arr
	 * @param name
	 * @param type - possible types : TEXTURE, GLOBAL MEMORY
	 */
	public <T extends Number> void prepareStaticData(final T[] arr, final String name, final GPUType type);

	
	/** 
	 * This is used to set data for calculations.
	 * It could be called several times.
	 * @param arr - contains data
	 * @param name - internal name on GPU side
	 * @param type
	 */
	public <T extends Number> void setArray(final T[] arr, final String name, final GPUType type);

	/**
	 * To send individual values. 
	 * @param val
	 * @param name
	 * @param type
	 */
	public <T extends Number> void setValue(final T val, final String name, final GPUType type);

	
	/**
	 * To run kernels.
	 * TODO: kernel args!
	 */
	public void runKernel();
	

	/** 
	 * Get output array!
	 *  
	 * @param name
	 * @return
	 */
	public <T extends Number> T[] getArray(final String name);
	
	/**
	 * Free memory / destroy context!
	 */
	void clean();
}
