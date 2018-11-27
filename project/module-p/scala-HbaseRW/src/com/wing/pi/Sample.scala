package com.wing.pi

object Sample {
  def main(args: Array[String]): Unit = {
    val soruceMap = Map("A" -> "lakalaApp","01" -> "拉卡拉钱包APP","02" -> "H5","03" -> "多媒体","04" -> "收款宝","05" -> "微信","06" -> "超级手机银行","07" -> "手机收款宝MPOS","08" -> "线下","09" -> "M POS","10" -> "拉卡拉手环","11" -> "客户经理APP","12" -> "卡拉征信APP","13" -> "商户通","14" -> "9188公积金","15" -> "9188信用卡","16" -> "联想金融Y","17" -> "圈子账本","18" -> "信用码","19" -> "集团微信","20" -> "金融微信","21" -> "新浪有还","22" -> "翼龙贷","23" -> "收钱吧","31" -> "京东借钱")
    val source: String = "A"
    val keyList = soruceMap.map(_._1)
    keyList.foreach((x:String) => {
      println("*****x:"+x)
    })
  }

}
