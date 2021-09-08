package com.vanalex.config

import java.nio.file.Paths

object PathUtil {

  def extractExtension(path : String): String ={
    Paths.get(path).getFileName.toString.split("\\.").last
  }
}
