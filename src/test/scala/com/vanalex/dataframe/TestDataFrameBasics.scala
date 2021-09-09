package com.vanalex.dataframe

import com.vanalex.config.SparkSessionWrapper
import org.junit.Assert.assertEquals
import org.scalatest.FunSpec

class TestDataFrameBasics extends FunSpec with SparkSessionWrapper{

  import spark.implicits._
  describe("Dataframe Basics") {
    it("Sequence to Dataframe") {
      val data = Seq(
        "Hello, this is an example sentence",
        "And this is a second sentence")
        .toDF("text")

      val firstRow = data.select("text").first().getString(0)
      assertEquals(firstRow, "Hello, this is an example sentence")
    }
  }
}
