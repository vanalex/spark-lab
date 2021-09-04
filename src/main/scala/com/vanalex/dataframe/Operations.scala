package com.vanalex.dataframe

import com.vanalex.config.SparkSessionWrapper
import org.apache.spark.sql.{DataFrame, Dataset, Encoder}


object Operations extends SparkSessionWrapper{
  def applyEncoder[U:Encoder](dataframe: DataFrame): Dataset[U] = {
    dataframe.as[U]
  }
}
