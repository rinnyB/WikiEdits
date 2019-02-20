import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

object Streams {

  val sparkConf: SparkConf = new SparkConf()
    .setAppName("Streams")
    .setMaster("local[*]")
  val sc: SparkContext = new SparkContext(sparkConf)
  val ssc = new StreamingContext(sc, Seconds(10))


  // Kafka Setup
  val brokers: List[String] = List("localhost:9092")
  val groupId: String = "streamsConsumer"
  val topicsSet: Set[String] = Set("wikiedits")

  val kafkaParams = Map[String, Object](
    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers,
    ConsumerConfig.GROUP_ID_CONFIG -> groupId,
    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer])
  val messages = KafkaUtils.createDirectStream[String, String](
    ssc,
    PreferConsistent,
    Subscribe[String, String](topicsSet, kafkaParams))

  messages.print()

  ssc.start()
  ssc.awaitTermination()
}