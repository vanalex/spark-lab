package com.vanalex.udf

import com.vanalex.config.SparkSessionWrapper
import com.vanalex.udf.operations.cubed
import org.junit.Assert.assertEquals
import org.scalatest.FunSpec

class UDFTest extends FunSpec with SparkSessionWrapper{

  describe("test UDF") {

    it("define and test UDF") {
      spark.udf.register("cubed", cubed)

      // Create temporary view
      spark.range(1, 9).createOrReplaceTempView("udf_test")

      val df = spark.sql("SELECT id, cubed(id) AS id_cubed FROM udf_test")

      assertEquals(df.first().getLong(0), Long.box(1));
      assertEquals(df.first().getLong(1), Long.box(1));
    }
  }
}
