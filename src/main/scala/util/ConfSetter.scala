import models.configurationModel
import org.apache.spark.{SparkConf, sql}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.streaming.DataStreamWriter

object ConfSetter {

  def getSparkSession(confModel: configurationModel): SparkSession = {
    val conf = new SparkConf()
    confModel.sparkProperties.foreach(prop => conf.set(prop._1, prop._2))

    SparkSession
      .builder()
      .config(conf)
      .appName(confModel.appName)
      .getOrCreate()
  }

  def getKafkaInputWriter(spark: SparkSession,
                          confModel: configurationModel): sql.DataFrame = {
    val streamer = spark.readStream.format("kafka")
    confModel.kafkaInputProperties.foreach(prop =>
      streamer.option(prop._1, prop._2))
    streamer.load()
  }

  def getKafkaOutputWriter(spark: SparkSession,
                           confModel: configurationModel,
                           df: sql.DataFrame): DataStreamWriter[Row] = {
    val streamer = df.writeStream
      .format("kafka")
    confModel.kafkaOutputProperties.foreach(prop =>
      streamer.option(prop._1, prop._2))
    streamer
  }
}
