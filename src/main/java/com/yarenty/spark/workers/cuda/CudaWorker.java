/**
 * 
 */
package com.yarenty.spark.workers.cuda;

import com.yarenty.spark.workers.AbstractWorker;
import com.yarenty.spark.workers.GPUType;
import com.yarenty.spark.workers.Worker;
//import static jcuda.driver.JCudaDriver.*;
import org.apache.log4j.Logger;

//import static scala.collection.JavaConversions.*;
//import jcuda.*;
//import jcuda.driver.*;

/**
 * @author yarenty
 * 
 */
public class CudaWorker extends AbstractWorker implements Worker {

	final static Logger LOG = Logger.getLogger(CudaWorker.class);

	public CudaWorker(int id) {
		super(id);
		LOG.info("CUDA Worker no:" + id + " created.");
	}

	/*
	 * need to be * WARP, while WARP is 32, test with [1024 max!]
	 * 8438 items, size 256 => 2515ms
	 * 8438 items, size 512 => 2406ms
	 * 8438 items, size 768 => 2306ms
	 * 8438 items, size 1024 => 2315ms
	 *  
	 * 7857 items, size 256 => 1958ms
	 * 7857 items, size 512 => 1947ms
	 * 7857 items, size 768 => 2293ms
	 * 7857 items, size 1024 => 1951ms
	 * 
	 * N:	512: 1024:
	 * 8438	2406 2315 +	
	 * 7857	1947 1951 -
	 * 8890	2533 2603 -
	 * 8046 1998 2014 -
	 * 7548 1888 1900 -
	 * 7932 1980 1977 =
	 * 8989 2579 2480 +
	 * 8561 2451 2332 +
	 * 
	 * for more details @see: Occupancy Calculator 
	 * https://devtalk.nvidia.com/default/topic/368105/cuda-occupancy-calculator-helps-pick-optimal-thread-block-size/
	 */
	private static final int CUDA_BLOCK_SIZE = 1024; //


	/*
	 * This contains size on single slice of MRs to be send to calculations 
	 * WARNING: This is highly depending on size of input MRs
	 * By default bigger is better.
	 * However there are 2 stoppers to make it 1 single call:
	 * 	- size of page memory [OoM]
	 * 	- output could be calculated using shared memory which could drastically 
	 * 		speed up calculations => shared memory 49kB [single output elem 3*4B]
	 * 
	 *  SLICE	IN		TIME 	
	 *  1024	14493	20130
	 *  1024	14020	20149
	 *  1024	14029	19890
	 *  
	 *  2048	14493	19074
	 *  2048	14020	18471
	 *  2048	14029	17201
	 *  2048	12553	13574  <-	
	 *  2048	11477	12426  <-
	 *  
	 *  3076	14493	17318	
	 *  3076	14020	16260
	 *  3076	14029	15844
	 *  3076	12553	14681 <-
	 *  3076	11477	12328 <-
	 */
	final static int SLICE_SIZE = 3072;


	
	
	public void clean() {
		// Clean up.
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		try {
			// Clean up.
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Probably cleaning do the trick");
		}

	}

	@Override
	public <T extends Number> void prepareStaticData(T[] arr, String name,
			GPUType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Number> void setArray(T[] arr, String name, GPUType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Number> void setValue(T val, String name, GPUType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runKernel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Number> T[] getArray(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
