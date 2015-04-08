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
    val a = Array(0.1f, 0.3f, 10.0f, 12.0f)
    val b = Array(101.1f, 30.3f, 50.0f, 10.0f)
  }
  
    test("check first row") {
    new TestWorker {
      val service = WorkerService.getInstance

      val out = service.exampleMultiplication(
        a,
        b)

      assert(out.next() === (10.11, 10.1,500.0,120.0))
    }
  }

  test("print all") {
    new TestWorker {
      val alg = new WorkerService.getInstance
      
    val out = service.exampleMultiplication(
        a.asJava,
        b.asJava)

      out.foreach(println)
    }
  }

}