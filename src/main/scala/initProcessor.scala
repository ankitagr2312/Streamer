import org.apache.spark.sql.streaming.Trigger
import util.FileReaderAndMapper

object initProcessor {
  def main(args: Array[String]): Unit = {

    val confModel = FileReaderAndMapper.getJobConfigurationModel(args(0))
    val spark = ConfSetter.getSparkSession(confModel)

    val df = ConfSetter.getKafkaInputWriter(spark,confModel)

    import spark.sqlContext.implicits._
    val dfExtracted  =  df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]

    val outputDf = dfExtracted.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
    val writer = ConfSetter.getKafkaOutputWriter(spark,confModel,outputDf)
      .trigger(Trigger.ProcessingTime("10 seconds"))
      .start()

    writer.awaitTermination()
  }
}
