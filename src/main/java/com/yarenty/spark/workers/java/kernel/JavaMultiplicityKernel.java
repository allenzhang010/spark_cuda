package com.yarenty.spark.workers.java.kernel;

import java.util.HashMap;
import java.util.Map;

import com.yarenty.spark.workers.Kernel;

public class JavaMultiplicityKernel implements Kernel {

	@Override
	public void run( Map<String, Number[]> arrMap, Map<String, Number> valMap) {
		Float[] a = (Float[]) arrMap.get("a");
		Float[] b = (Float[]) arrMap.get("b");
		Float[] c = (Float[]) arrMap.get("c");
		
		for ( int i= 0; i <a.length; i++) {
			c[i] = a[i] * b[i];
		}

	}

}
