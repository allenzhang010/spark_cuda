package com.yarenty.spark

/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "/opt/spark-1.3.0/README.md" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
    
    val data = 1 to 1000
    
    val accum = sc.accumulator(0, "My Accumulator")  
    val distData = sc.parallelize(data).foreach(x => accum += x)

    println("Accumulator: %s".format(accum));
    
    
  }
}
