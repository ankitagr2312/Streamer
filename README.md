# Streamer

This project is for getting data from streaming source and processing.  
Currently source is Kafka.  
At the run time argument is required which is a json file.  
Sample json structure:

```
{
  "appName": "Just a check",
  "sparkProperties" : {
    "spark.master": "local[*]"
  },
  "kafkaInputProperties" : {
    "kafka.bootstrap.servers": "localhost:9092",
    "subscribe": "inputTopic",
    "startingOffsets" : "earliest"
  },
  "kafkaOutputProperties": {
    "kafka.bootstrap.servers": "localhost:9092",
    "checkpointLocation": "/home/ankitagr/Projects/Streamer/checkPointDir",
    "topic": "outputTopic"
  }
}
```
