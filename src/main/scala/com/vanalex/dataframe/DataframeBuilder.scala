package com.vanalex.dataframe

import org.apache.spark.sql.{DataFrame, SparkSession}

object DataframeBuilder {

  def forParquet(spark: SparkSession, path: String): DataFrame = {
    spark.read.parquet(path)
  }

  def forCsv(spark: SparkSession, path: String): DataFrame = {
    spark.read
      .option("maxFilesPerTrigger", 1)
      .format("csv")
      .option("header", "true")
      .load(path)
  }

  def forJson(spark: SparkSession, path: String): DataFrame = {
    spark
      .read
      .format("json")
      .option("path", path)
      .load()
  }
}
