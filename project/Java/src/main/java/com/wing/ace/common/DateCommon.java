//new Date() 转换string
public static Date getNowDate() {    
    Date currentTime = new Date();   
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
    String dateString = formatter.format(currentTime);
    
}
