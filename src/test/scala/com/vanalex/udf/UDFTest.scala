package com.vanalex.udf

import com.vanalex.config.SparkSessionWrapper
import com.vanalex.udf.operations.cubed
import org.junit.Assert.assertEquals
import org.scalatest.FunSpec

class UDFTest extends FunSpec with SparkSessionWrapper {

  spark.udf.register("cubed", cubed)

  describe("test UDF") {

    it("define and test UDF") {

      // Create temporary view
      spark.range(1, 9).createOrReplaceTempView("udf_test")

      val df = spark.sql("SELECT id, cubed(id) AS id_cubed FROM udf_test")

      assertEquals(df.first().getLong(0), Long.box(1));
      assertEquals(df.first().getLong(1), Long.box(1));
    }

    it("Building a User Defined Function") {

      val df = spark.createDataFrame(
        spark.sparkContext.parallelize(DataSourceBuilder.buildArrayData()),
        DataSourceBuilder.arraySchema()
      )
      df.createOrReplaceTempView("table")
      df.printSchema()
      df.show()
    }

    it("sql explode") {
      val df = spark.createDataFrame(
        spark.sparkContext.parallelize(DataSourceBuilder.buildArrayData()),
        DataSourceBuilder.arraySchema()
      )
      df.createOrReplaceTempView("table")
      val newDF = spark.sql("""
      SELECT id, collect_list(value + 1) AS newValues
      FROM  (SELECT id, explode(values) AS value
      FROM table) x
      GROUP BY id
      """)
    }
  }
}
