/**
 *
 */
package com.yarenty.spark


import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import com.yarenty.spark.workers.WorkerService

import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class JavaWorkersTest extends FunSuite {

  trait TestWorker {
    val a = {0.1; 0.3; 10.0; 12.0}
    val b = {101.1; 30.3; 50.0; 10.0}
    
    
  }
  
    test("check first row") {
    new TestWorker {
      val service = new WorkerService

      val out = service.exampleMultiplication(
        a.asJava,
        b.asJava)

    val a = {0.1; 0.3; 10.0; 12.0}
    }
  }

  test("print all") {
    new TestWorker {
      val alg = new WorkerService
      
      val out = alg.exampleMultiplication(
        a.asJava,
        b.asJava)
      out.foreach(println)
    }
  }

}