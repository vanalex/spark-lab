package com.vanalex.introduction

import com.vanalex.config.SparkSessionWrapper
import com.vanalex.dataframe.{DataframeFactory, Operations}
import com.vanalex.model.Flight
import org.apache.spark.sql.functions.{col, window}

object ToolsetIntro extends SparkSessionWrapper{
  import spark.implicits._
  def main(args: Array[String]): Unit = {

    val flightsDF = DataframeFactory.dataframeByParquetFile(spark,"src/main/resources/flight/2010-summary.parquet")
    val flights = Operations.applyEncoder[Flight](flightsDF)

    flights.show(truncate = false)

    flights
      .filter(flight_row => flight_row.ORIGIN_COUNTRY_NAME != "Canada")
      .map(flight_row => flight_row)
      .take(5)
    flights.show(truncate = false)

    flights
      .take(5)
      .filter(flight_row => flight_row.ORIGIN_COUNTRY_NAME != "Canada")
      .map(fr => Flight(fr.DEST_COUNTRY_NAME, fr.ORIGIN_COUNTRY_NAME, fr.count + 5))

    flights.show(truncate = false)

    val staticDataFrame = spark.read.format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load("src/main/resources/retail-data/by-day/*.csv")

    staticDataFrame.show(truncate = false)

    staticDataFrame.createOrReplaceTempView("retail_data")
    val staticSchema = staticDataFrame.schema
    println(staticSchema)

    staticDataFrame
      .selectExpr(
        "CustomerId",
        "(UnitPrice * Quantity) as total_cost",
        "InvoiceDate")
      .groupBy(
        col("CustomerId"), window(col("InvoiceDate"), "1 day"))
      .sum("total_cost")
      .show(5)

    spark.conf.set("spark.sql.shuffle.partitions", "5")

    val streamingDataFrame = spark.readStream
      .schema(staticSchema)
      .option("maxFilesPerTrigger", 1)
      .format("csv")
      .option("header", "true")
      .load("src/main/resources/retail-data/by-day/*.csv")

    println(streamingDataFrame.isStreaming)

    val purchaseByCustomerPerHour = streamingDataFrame
      .selectExpr(
        "CustomerId",
        "(UnitPrice * Quantity) as total_cost",
        "InvoiceDate")
      .groupBy(
        $"CustomerId", window($"InvoiceDate", "1 day"))
      .sum("total_cost")
    purchaseByCustomerPerHour.show(truncate = false)
  }
}
