package com.vanalex.preprocessing

import com.vanalex.config.SparkSessionWrapper
import org.apache.spark.sql.functions.{column, translate}

object BasicExample extends SparkSessionWrapper {

  def main(args: Array[String]): Unit = {
    val rawDF = spark.read
      .option("header", "true")
      .option("multiLine", "true")
      .option("inferSchema", "true")
      .option("escape", "\"")
      .csv("src/main/resources/airbnb/sf-airbnb.csv")

    //rawDF.show(truncate = false)

    val baseDF = rawDF.select(
      "host_is_superhost",
      "cancellation_policy",
      "instant_bookable",
      "host_total_listings_count",
      "neighbourhood_cleansed",
      "latitude",
      "longitude",
      "property_type",
      "room_type",
      "accommodates",
      "bathrooms",
      "bedrooms",
      "beds",
      "bed_type",
      "minimum_nights",
      "number_of_reviews",
      "review_scores_rating",
      "review_scores_accuracy",
      "review_scores_cleanliness",
      "review_scores_checkin",
      "review_scores_communication",
      "review_scores_location",
      "review_scores_value",
      "price"
    )

    baseDF.cache().count
    //baseDF.show(truncate = false)

    val fixedPriceDF = baseDF.withColumn(
      "price",
      translate(column("price"), "$,", "").cast("double")
    )
    fixedPriceDF.show(truncate = false)
  }
}
