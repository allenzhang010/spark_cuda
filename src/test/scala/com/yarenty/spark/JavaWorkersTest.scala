package com.yarenty.spark


import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import com.yarenty.spark.workers.WorkerService
import scala.collection.JavaConverters._
import org.apache.log4j.Logger
import org.apache.log4j.ConsoleAppender
import org.apache.log4j.SimpleLayout
import org.apache.log4j.Level

@RunWith(classOf[JUnitRunner])
class JavaWorkersTest extends FunSuite {

  val LOG = Logger.getLogger("test")
  
  trait TestWorker {

    LOG.getParent.addAppender(new ConsoleAppender(new SimpleLayout))
    LOG.getParent.setLevel(Level.ALL)
    val a: Array[java.lang.Float] = Array(0.1f, 0.3f, 10.0f, 12.0f)
    val b: Array[java.lang.Float] = Array(101.1f, 30.3f, 50.0f, 10.0f)
  }
  
    test("check output row") {
    new TestWorker {
      val service = WorkerService.getInstance

      val out = service.exampleMultiplication(
        a,
        b
        )

      assert(out.toIterator.next() === 10.11f)
      assert(out(1) === 9.09f)
      assert(out(2) === 500.0f)
      assert(out(3) === 120.0f)
      //or simply:
      assert(out === Array(10.11f, 9.09f, 500.0f,120.0f))
      
    }
  }

  test("print all") {
    new TestWorker {
      val service = WorkerService.getInstance
     
    val out = service.exampleMultiplication(
        a,
        b
        )

      out.foreach(println)
    }
  }

}