package com.vanalex.dataframe

import org.apache.spark.sql.{DataFrame, SparkSession}

object DataframeFactory {
  def dataframeByCsvFile(spark: SparkSession, path: String): DataFrame = {
    spark.read.format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(path)
  }

  def dataframeByParquetFile(spark: SparkSession, path: String): DataFrame = {
    spark.read.parquet(path)
  }
}
