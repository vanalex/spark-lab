package com.vanalex.udf

import org.apache.spark.sql.SparkSession

import scala.reflect.runtime.universe.TypeTag

class UDFRegister {

  def register[RT: TypeTag, A1: TypeTag](
    sparkSession: SparkSession,
    name: String,
    func: Function1[A1, RT]
  ): SparkSession = {
    sparkSession.udf.register(name, func)
    sparkSession
  }
}
