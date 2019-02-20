import Dependencies._

name := "WikiEditsCounts"

ThisBuild / organization := "com.github/rinnyB"
ThisBuild / version := "0.1"

ThisBuild / scalaVersion := "2.12.8"

libraryDependencies ++= sparkStreamingDeps

assemblyMergeStrategy in assembly := {
  case PathList("javax", xs @ _*)         => MergeStrategy.first
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList(ps @ _*) if ps.contains("aopalliance") => MergeStrategy.first
  case PathList(ps @ _*) if ps.contains("hadoop") => MergeStrategy.first
  case PathList(ps @ _*) if ps.contains("unused") => MergeStrategy.discard
  case "application.conf" => MergeStrategy.concat
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}