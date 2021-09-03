package com.vanalex.introduction

import com.vanalex.config.SparkSessionWrapper
import com.vanalex.model.Flight

object ToolsetIntro extends SparkSessionWrapper{
  import spark.implicits._
  def main(args: Array[String]): Unit = {

    val flightsDF = spark.read
      .parquet("src/main/resources/flight/2010-summary.parquet")
    val flights = flightsDF.as[Flight]

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

  }
}
