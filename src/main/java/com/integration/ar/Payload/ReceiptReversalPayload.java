package com.integration.ar.Payload;

import java.util.Map;

import com.integration.ar.WebService.WebServiceDetail;

public class ReceiptReversalPayload {
    private static String username;
    private static String password;
    private static String encoded;


    public static String receiptReversalPayload(Map<String, Object> content) {
        
        System.out.println("<==>"+content.get("environment"));
        if( content.get("environment")==null||
            content.get("environment").toString().equals("prod")){
            username=WebServiceDetail.prod_UserName;
            password=WebServiceDetail.prod_Password;
            encoded=WebServiceDetail.prod_Encoded;                        
        }else{
            username=WebServiceDetail.test_UserName;
            password=WebServiceDetail.test_Password;
            encoded=WebServiceDetail.test_Encoded;
        }

String xml =
"<soapenv:Envelope xmlns:com=\"http://xmlns.oracle.com/apps/financials/receivables/receipts/shared/standardReceiptService/commonService/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://xmlns.oracle.com/apps/financials/receivables/receipts/shared/standardReceiptService/commonService/types/\">\n" +
"   <soapenv:Header>\n" +
"      <wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
"         <wsu:Timestamp wsu:Id=\"TS-FB0DF24C82A22794BF16254987919802\">\n" +
"            <wsu:Created>"+WebServiceDetail.getCreatedDate()+"</wsu:Created>\n" +
"            <wsu:Expires>"+WebServiceDetail.getExpiredDate()+"</wsu:Expires>\n" +
"         </wsu:Timestamp>\n" +
"         <wsse:UsernameToken wsu:Id=\"UsernameToken-FB0DF24C82A22794BF16254987749311\">\n" +
"            <wsse:Username>"+username+"</wsse:Username>\n" +
"            <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"+password+"</wsse:Password>\n" +
"            <wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">"+encoded+"</wsse:Nonce>\n" +
"            <wsu:Created>"+WebServiceDetail.getCreatedDate()+"</wsu:Created>\n" +
"         </wsse:UsernameToken>\n" +
"      </wsse:Security>\n" +
"   </soapenv:Header>\n" +
"   <soapenv:Body>\n" +
"      <typ:createReverseReceipt>\n" +
"         <typ:reverseReceipt>\n" +
"            <!--Optional:-->\n" +
"            <com:ReceiptNumber>"+content.get("receiptNumber")+"</com:ReceiptNumber>\n" +
"            <!--Optional:-->\n" +
"            <com:ReversalCategory>"+content.get("reversalCategory")+"</com:ReversalCategory>\n" +
"            <!--Optional:-->\n" +
"            <com:ReversalDate>"+content.get("reversalDate")+"</com:ReversalDate>\n" +
"            <!--Optional:-->\n" +
"            <!--<com:ReversalReasonCode></com:ReversalReasonCode>-->\n" +
"            <!--Optional:-->\n" +
"            <com:ReversalComments>"+content.get("reversalComments")+"</com:ReversalComments>\n" +
"            <!--Optional:-->\n" +
"            <com:BusinessUnit>"+content.get("businessUnit")+"</com:BusinessUnit>\n" +
"            <!--Optional:-->\n" +
"            <!--<com:ReversalCategoryName>?</com:ReversalCategoryName>-->\n" +
"            <!--Optional:-->\n" +
"            <com:ReversalGlDate>"+content.get("reversalGlDate")+"</com:ReversalGlDate>\n" +
"            <!--Optional:-->\n" +
"            <com:ReversalReasonName>"+content.get("reversalReasonName")+"</com:ReversalReasonName>\n" +
"         </typ:reverseReceipt>\n" +
"      </typ:createReverseReceipt>\n" +
"   </soapenv:Body>\n" +
"</soapenv:Envelope>";

    // System.out.println("==>"+createReciept);
        return xml;
    }



}