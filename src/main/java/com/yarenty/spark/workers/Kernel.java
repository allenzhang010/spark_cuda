package com.yarenty.spark.workers;

import java.util.HashMap;
import java.util.Map;

public interface Kernel {

	public void run(Map<String, Number[]> arrMap, Map<String, Number> valMap);
}
