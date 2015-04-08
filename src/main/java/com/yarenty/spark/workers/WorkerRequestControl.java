package com.yarenty.spark.workers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerRequestControl {

	private AtomicBoolean acceptRequests;
	private AtomicInteger pendingRequests;
	private Semaphore okToStop;
	private CountDownLatch serviceStart;
	
	private AtomicBoolean firstRunStarted; //??

	public WorkerRequestControl(){
		acceptRequests = new AtomicBoolean(false);
		pendingRequests = new AtomicInteger(0);
		okToStop = new Semaphore(0);
		firstRunStarted = new AtomicBoolean(false);
	}
	
	
	public void setServiceStart() {
		serviceStart = new CountDownLatch(1);
	}
	
	public void checkServiceStart() throws InterruptedException {
		serviceStart.await();
	}
	
	public void serviceStarted() {
		serviceStart.countDown();
	}
	
	

	public void setOkToStop() {
		okToStop.release();
	}
	
	public void checkOkToStop() throws InterruptedException {
		if (firstRunStarted.get()){
			okToStop.acquire();
		}
	}
	
	public void markWorkerRun() {
		if (firstRunStarted.get()) {
			firstRunStarted.set(true);
		}
	}

	public boolean getAcceptRequest() {
		return acceptRequests.get();
	}
	
	public void setAcceptRequests(boolean acceptReq) {
		acceptRequests.set(acceptReq);
	}
	
	public AtomicInteger getPendingRequests() {
		return pendingRequests;
	}
	
}

