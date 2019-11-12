package models

case class configurationModel(
    appName: String,
    sparkProperties: Map[String, String],
    kafkaInputProperties: Map[String, String],
    kafkaOutputProperties:Map[String, String]
)