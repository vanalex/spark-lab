package com.vanalex

import com.vanalex.config.SparkSessionWrapper
import org.junit.Assert.{assertEquals, assertNotNull}
import org.scalatest.FunSpec

class RDDTest extends FunSpec with SparkSessionWrapper {

  spark.sparkContext.setLogLevel("WARN")
  describe("RDD Basics") {
    it("Sequence to Dataframe") {
      val data = Seq(("java", 200000), ("python", 5000), ("scala", 10000), ("R", 3000))
      val rdd = spark.sparkContext.parallelize(data)
      assertNotNull(rdd)
      val firstRow = rdd.first()
      assertEquals(firstRow._1, "java")
      assertEquals(firstRow._2, 200000)
    }
  }
}