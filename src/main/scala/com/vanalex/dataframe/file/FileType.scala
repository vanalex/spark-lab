package com.vanalex.dataframe.file

import java.nio.file.Paths

object FileType extends Enumeration {

  type TableType = Value
  val parquet, json, csv = Value

  def isValidFileType(s: String): Boolean = values.exists(_.toString == s)

  def getFileType(path: String): TableType = {
    val extension = Paths.get(path).getFileName.toString.split("\\.").last
    if (isValidFileType(extension)) FileType.withName(extension) else parquet
  }
}
