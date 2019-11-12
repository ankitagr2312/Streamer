package util

import models.configurationModel
import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.io.Source

object FileReaderAndMapper extends java.io.Serializable {

  def getJobConfigurationModel(jsonPath: String): configurationModel = {

    implicit val formats = DefaultFormats
    val jsonConfFile = Source.fromFile(jsonPath).getLines.mkString
    parse(jsonConfFile).extract[configurationModel]
  }
}
