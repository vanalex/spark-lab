package com.vanalex.dataframe

import org.apache.spark.sql.{DataFrame, SparkSession}

trait Reader {
  val name: String
  def read(sparkSession: SparkSession): DataFrame
}
