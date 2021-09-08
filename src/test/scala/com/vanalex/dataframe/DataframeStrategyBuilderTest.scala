package com.vanalex.dataframe

import com.vanalex.SparkSessionTestWrapper
import org.junit.Assert.assertNotNull
import org.scalatest.FunSpec

class DataframeStrategyBuilderTest extends FunSpec
  with SparkSessionTestWrapper {

  describe("loads dataframe") {

    it("it loads dataframe given parquet path") {
      val dataframe = DataframeStrategyBuilder.build("src/main/resources/flight/2010-summary.parquet", spark)
      assertNotNull(dataframe)
    }

    it("it loads dataframe given csv path") {
      val dataframe = DataframeStrategyBuilder.build("src/main/resources/retail-data/by-day/2011-12-09.csv", spark)
      assertNotNull(dataframe)
    }

    it("it loads dataframe given json path") {
      val dataframe = DataframeStrategyBuilder.build("src/main/resources/flight/2015-summary.json", spark)
      assertNotNull(dataframe)
    }
  }
}
