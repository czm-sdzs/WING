package com.wing.pi

object App {
  def main(args: Array[String]): Unit = {
//    print(("20000.00".trim.toDouble.toLong))
    var args = new Array[String](5)
    args(0) = "test"
    args(1) = "H"//SET
    args(2) = "r"
    args(3) = "dataPlatform.generalApply_CreditLoan.channelSource"//
    args(4) = "01:拉卡拉钱包APP,02:H5,03:多媒体"
    val env:String = args(0)
    val _redisType:String = args(1)
    val _type:String = args(2)//w:write;r:read;d:delete
    val _key:String = args(3)
    var _value:String = ""
    if("w".equals(_type)){
      _value = args(4)
    }
    redisDo(env,_redisType,_type,_key,_value)
  }

  private def redisDo(env:String,_redisType:String,_type:String,key:String,_value:String): Unit ={
    if("r".equals(_type)){
      println("----读取数据-------")
      if("H".equals(_redisType)){
        ReadUtil.getHm(env,key)
      }else if("S".equals(_redisType)){
        ReadUtil.getS(env,key)
      }
    }
    if("w".equals(_type)){
      println("----写数据-------")
      if("H".equals(_redisType)){
        WreteUtil.writeHash2Redis(env,key,_value)
      }else if("S".equals(_redisType)){
        WreteUtil.writeRedisStr(env,key,_value)
      }
    }
    if("d".equals(_type)){
      println("----删key-------")
      DeleteUtil.deleteKeyRedis(env,key)
    }
  }

}
