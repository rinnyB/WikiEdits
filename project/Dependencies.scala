import sbt._

object Dependencies {

  lazy val sparkVersion = "2.4.0"

  val sparkCore =  "org.apache.spark" %% "spark-core" % sparkVersion
  val sparkStreaming = "org.apache.spark" %% "spark-streaming" % sparkVersion
  val sparkStreamingKafka = "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion

  val sparkStreamingDeps: Seq[ModuleID] = Seq(sparkCore, sparkStreaming, sparkStreamingKafka)
  
  
  

}
