package com.foo

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object KafkaProducer {

  def main(args: Array[String]): Unit = {

    writeToKafka("first")
  }

  def writeToKafka(topic: String)={

    val Properties = new Properties()
    Properties.setProperty("bootstrap.servers", "node32:9092")
    Properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    Properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String,String](Properties)

    val source = io.Source.fromFile("D:\\IDEA\\UserBehaviorAnalysised\\HotItemsAnalysis\\src\\main\\resources\\UserBehavior.csv")

     for(line <- source.getLines()){

       val value = new ProducerRecord[String, String](topic, line)

       producer.send(value)

     }

    producer.close()

  }



}
