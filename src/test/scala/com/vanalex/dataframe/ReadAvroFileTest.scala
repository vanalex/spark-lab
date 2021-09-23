package com.vanalex.dataframe

import com.vanalex.SparkSessionTestWrapper
import org.junit.Assert.assertNotNull
import org.scalatest.FunSpec

class ReadAvroFileTest extends FunSpec with SparkSessionTestWrapper {

  describe("loads avro dataframe") {

    it("it loads dataframe given avro path") {
      val pathFile = "src/main/resources/flight/avro/*"

      val dataframe = spark.read.format("avro").option("path", pathFile).load()
      assertNotNull(dataframe)
      val schema = dataframe.encoder.schema
      println(schema)
    }
  }
}
