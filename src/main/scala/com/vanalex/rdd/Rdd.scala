package com.vanalex.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

import scala.reflect.ClassTag

object Rdd {

  def create[T: ClassTag](sparkSession: SparkSession, data: Seq[T]): RDD[T] ={
    sparkSession.sparkContext.parallelize(data)
  }

  def create[T:ClassTag](sparkSession: SparkSession, path: String): RDD[String] = {
    sparkSession.sparkContext.textFile(path)
  }

}
