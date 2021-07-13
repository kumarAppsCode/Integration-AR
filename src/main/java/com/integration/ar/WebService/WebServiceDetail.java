package com.integration.ar.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.lang3.time.DateUtils;

public class WebServiceDetail {
 
    private static String createdDate;
    private static String expiredDate;
    public static final  String test_UserName = "PRISM@omniyat.com";
    public static final  String test_Password = "0mniy@t123";
    public static final  String test_Encoded = "gGz12EPTL044YTT/Ad+vXA==";

    public static final  String prod_UserName = "ERP_user";
    public static final  String prod_Password = "welcome@4i";
    public static final  String prod_Encoded = "fvHY1x86NR06HuudWKA+cw==";
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
    static Date date;

    public static String getCreatedDate() {

        date = new Date();
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        createdDate = dateFormat.format(new Date());
        return createdDate;
    }

    public static String getExpiredDate() {
        date = new Date();
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        expiredDate = dateFormat.format(DateUtils.addMinutes(date, 2));
        return expiredDate;
    }



    public static String baseUrl(Object object){
        String url=null;
      if (object == null || ((String) object).equalsIgnoreCase("prod")) {
            url="https://egzy.fa.em2.oraclecloud.com";
      } else {
            url="https://ejds-test.fa.em2.oraclecloud.com:443";
      }
      return url;
    }


    
}