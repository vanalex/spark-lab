package com.vanalex.dataframe

import com.vanalex.config.PathUtil.extractExtension
import org.apache.spark.sql.{DataFrame, SparkSession}

object DataframeStrategyBuilder {

  def build(path: String, spark: SparkSession): DataFrame = {
    val extension = extractExtension(path)
    extension match {
      case "csv" => DataframeBuilder.forCsv(spark, path)
      case "parquet" => DataframeBuilder.forParquet(spark, path)
      case "json" => DataframeBuilder.forJson(spark, path)
    }
  }
}
