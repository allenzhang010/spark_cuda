package com.yarenty.spark.workers;

import org.apache.log4j.Logger;
/**
 * @author yarenty
 */
public abstract class AbstractWorker  implements Worker {
	final static Logger LOG = Logger.getLogger(AbstractWorker.class);

	protected final int id;
	
	public AbstractWorker(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	@Override
	public void initialize() {
		LOG.info("Hello World!");
	}

	public void clean() {
		LOG.info("Cleaning..");
	}

}
