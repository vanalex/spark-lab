package com.vanalex.udf

import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType

object DataSourceBuilder {

  def arraySchema(): StructType = {
    // Create schema
    import org.apache.spark.sql.types._
    new StructType()
      .add("id", IntegerType)
      .add("values", ArrayType(IntegerType))
  }

  def buildArrayData(): Seq[Row] =
    Seq(
      Row(1, List(1, 2, 3)),
      Row(2, List(2, 3, 4)),
      Row(3, List(3, 4, 5))
    )
}
