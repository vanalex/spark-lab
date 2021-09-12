package com.vanalex

import com.vanalex.config.SparkSessionWrapper
import com.vanalex.rdd.Rdd
import org.junit.Assert.{assertEquals, assertNotNull}
import org.scalatest.FunSpec

class RDDTest extends FunSpec with SparkSessionWrapper {

  spark.sparkContext.setLogLevel("WARN")
  describe("RDD Basics") {
    it("Sequence to RDD") {
      val data = Seq(("java", 200000), ("python", 5000), ("scala", 10000), ("R", 3000))
      val rdd = Rdd.create(spark, data)
      assertNotNull(rdd)
      val firstRow = rdd.first()
      assertEquals(firstRow._1, "java")
      assertEquals(firstRow._2, 200000)
    }

    it("given json path create RDD") {
      val rdd = Rdd.create(spark, "src/main/resources/people/people.json")
      assertNotNull(rdd)
      val firstRow = rdd.first()
      assertEquals(firstRow, "{\"name\":\"Michael\"}")
    }
  }
}