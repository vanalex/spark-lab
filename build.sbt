name := "spark-lab"

version := "0.0.1"

scalaVersion := "2.12.12"
val sparkVersion = "3.1.1"

libraryDependencies += "org.apache.spark" %% "spark-avro" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.1.1"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "3.1.1"
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.1.1"
libraryDependencies += "com.github.mrpowers" %% "spark-daria" % "0.38.2"
libraryDependencies += "com.github.mrpowers" %% "spark-fast-tests" % "0.21.3" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")