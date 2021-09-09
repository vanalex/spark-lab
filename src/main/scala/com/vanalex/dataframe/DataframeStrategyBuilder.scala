package com.vanalex.dataframe

import com.vanalex.dataframe.file.FileType
import org.apache.spark.sql.{DataFrame, SparkSession}

object DataframeStrategyBuilder {

  def build(path: String, spark: SparkSession): DataFrame = {
    val fileType = FileType.getFileType(path)
    fileType match {
      case FileType.csv => DataframeBuilder.forCsv(spark, path)
      case FileType.parquet => DataframeBuilder.forParquet(spark, path)
      case FileType.json => DataframeBuilder.forJson(spark, path)
    }
  }
}
