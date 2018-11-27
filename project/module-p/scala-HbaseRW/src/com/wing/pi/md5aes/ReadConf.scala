package com.wing.pi.md5aes

import java.io.{BufferedInputStream, FileInputStream}
import java.util.Properties


object ReadConf {

  private var postgprop = new Properties
  private var config_path = ""


  def main(args: Array[String]): Unit = {
    initPath()
    loadProperties()
  }

  def initConf(): Unit = {
    initPath()
    loadProperties()
  }

  def initPath(): Unit = {
      config_path = "conf/scala_conf.properties"
      println("cluster execute...")
  }

  def loadProperties(): Unit = {
    println(s"current path:${System.getProperty("user.dir")}")
    val ipstream = new BufferedInputStream(new FileInputStream(config_path))
    postgprop.load(ipstream)
  }


  def getProperty(): Properties = {
    return postgprop;
  }

}
