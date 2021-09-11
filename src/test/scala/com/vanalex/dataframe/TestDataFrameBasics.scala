package com.vanalex.dataframe

import com.vanalex.config.SparkSessionWrapper
import org.apache.spark.sql.functions.{col, desc}
import org.apache.spark.sql.types.IntegerType
import org.junit.Assert.{assertEquals, assertTrue}
import org.scalatest.FunSpec

class TestDataFrameBasics extends FunSpec with SparkSessionWrapper{

  import spark.implicits._
  spark.sparkContext.setLogLevel("WARN")
  describe("Dataframe Basics") {
    it("Sequence to Dataframe") {
      val data = Seq(
        "Hello, this is an example sentence",
        "And this is a second sentence")
        .toDF("text")

      val firstRow = data.select("text").first().getString(0)
      assertEquals(firstRow, "Hello, this is an example sentence")
    }

    it("mnm count Dataframe") {
      val path = "src/main/resources/mnm/mnm_dataset.csv"

      val dataframe = DataframeStrategyBuilder.build(path, spark)
      val dfWithColumnChanged = dataframe.withColumn("Count",col("Count").cast(IntegerType))

      assertTrue(dfWithColumnChanged.schema("Count").dataType.typeName == "integer")

      val countMnMDF = dfWithColumnChanged.select("State", "Color", "Count")
        .groupBy("State", "Color")
        .sum("Count")
        .orderBy(desc("sum(Count)"))
      val firstRowValueAsString = countMnMDF.first().toString()
      assertEquals(firstRowValueAsString, "[CA,Yellow,100956]")
    }
  }
}
