package com.integration.ar.Payload;

import java.util.Map;

import com.integration.ar.PoJo.CreateReceiptBody;
import com.integration.ar.WebService.WebServiceDetail;


public class ReceiptPayload {

    private static String username;
    private static String password;
    private static String encoded;


    public static String recieptCreationPayload(CreateReceiptBody recieptData) {

        if(recieptData.getEnvironment()==null||
           recieptData.getEnvironment().toString().equals("prod")){
            username=WebServiceDetail.prod_UserName;
            password=WebServiceDetail.prod_Password;
            encoded=WebServiceDetail.prod_Encoded;                        
        }else{
            username=WebServiceDetail.test_UserName;
            password=WebServiceDetail.test_Password;
            encoded=WebServiceDetail.test_Encoded;
        }

        String createReciept = "<soapenv:Envelope xmlns:com=\"http://xmlns.oracle.com/apps/financials/receivables/receipts/shared/standardReceiptService/commonService/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:stan=\"http://xmlns.oracle.com/apps/financials/receivables/receipts/shared/model/flex/StandardReceiptDff/\" xmlns:stan1=\"http://xmlns.oracle.com/apps/financials/receivables/receipts/shared/model/flex/StandardReceiptGdf/\" xmlns:typ=\"http://xmlns.oracle.com/apps/financials/receivables/receipts/shared/standardReceiptService/commonService/types/\">\n"
                + "   <soapenv:Header>\n"
                + "      <wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n"
                + "         <wsu:Timestamp wsu:Id=\"TS-F84AFFFAB131372E9415323382493425\">\n"
                + "            <wsu:Created>" + WebServiceDetail.getCreatedDate() + "</wsu:Created>\n"
                + "            <wsu:Expires>" + WebServiceDetail.getExpiredDate() + "</wsu:Expires>\n"
                + "         </wsu:Timestamp>\n"
                + "         <wsse:UsernameToken wsu:Id=\"UsernameToken-F84AFFFAB131372E9415323307403511\">\n"
                + "            <wsse:Username>" + username + "</wsse:Username>\n"
                + "            <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">" + password + "</wsse:Password>\n"
                + "            <wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">" + encoded + "</wsse:Nonce>\n"
                + "            <wsu:Created>" + WebServiceDetail.getCreatedDate() + "</wsu:Created>\n"
                + "         </wsse:UsernameToken>\n"
                + "      </wsse:Security>\n"
                + "   </soapenv:Header>\n"
                + "   <soapenv:Body>\n"
                + "      <typ:createStandardReceipt>\n"
                + "         <typ:standardReceipt>\n"
                + "            <com:Amount currencyCode=\"" + recieptData.getCurrencyCode() + "\">" + recieptData.getAmount() + "</com:Amount>\n"
                + "            <com:CurrencyCode>" + recieptData.getCurrencyCode() + "</com:CurrencyCode>\n"
                + "            <com:GlDate>" + recieptData.getGlDate() + "</com:GlDate>\n"
                + "            <com:MaturityDate>" + recieptData.getMaturityDate() + "</com:MaturityDate>\n"
                + "            <com:OrgId>" + recieptData.getOrgId() + "</com:OrgId>\n"
                + "            <com:CustomerId>" + recieptData.getCustomerId() + "</com:CustomerId>\n"
                + "            <com:ReceiptDate>" + recieptData.getReceiptDate() + "</com:ReceiptDate>\n"
                + "            <com:ReceiptMethodId>" + recieptData.getReceiptMethodId() + "</com:ReceiptMethodId>\n"
                + "            <com:ReceiptNumber>" + recieptData.getReceiptNumber() + "</com:ReceiptNumber>\n"
                + "            <com:StructuredPaymentReference>" + recieptData.getChequeNo() + "</com:StructuredPaymentReference>\n"
                + "            <com:StandardReceiptFLEXVA>\n"
                + "               <stan:leasenumber>" + recieptData.getLeaseNumber() + "</stan:leasenumber>\n"
                + "               <stan:bookingNumber>" + recieptData.getBookingNumber() + "</stan:bookingNumber>\n"
                + "                <stan:building>" + recieptData.getBuilding() + "</stan:building>\n"
                + "                <stan:unit>" + recieptData.getUnit() + "</stan:unit>\n"
                + "                <stan:checkNo>" + recieptData.getChequeNo() + "</stan:checkNo>\n"
                + "            </com:StandardReceiptFLEXVA>\n"
                + "         </typ:standardReceipt>\n"
                + "      </typ:createStandardReceipt>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";
        System.out.println("==>"+createReciept);
        return createReciept;
    }



    public static String standardRecieptCreationPayload(Map<String, Object> recieptData) {
        
        System.out.println("<==>"+recieptData.get("environment"));
        if(recieptData.get("environment")==null||
           recieptData.get("environment").toString().equals("prod")){
            username=WebServiceDetail.prod_UserName;
            password=WebServiceDetail.prod_Password;
            encoded=WebServiceDetail.prod_Encoded;                        
        }else{
            username=WebServiceDetail.test_UserName;
            password=WebServiceDetail.test_Password;
            encoded=WebServiceDetail.test_Encoded;
        }

String createReciept =
"<soapenv:Envelope xmlns:com=\"http://xmlns.oracle.com/apps/financials/receivables/receipts/shared/standardReceiptService/commonService/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:stan=\"http://xmlns.oracle.com/apps/financials/receivables/receipts/shared/model/flex/StandardReceiptDff/\" xmlns:stan1=\"http://xmlns.oracle.com/apps/financials/receivables/receipts/shared/model/flex/StandardReceiptGdf/\" xmlns:typ=\"http://xmlns.oracle.com/apps/financials/receivables/receipts/shared/standardReceiptService/commonService/types/\">\n" +
"   <soapenv:Header>\n" +
"      <wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
"         <wsse:UsernameToken wsu:Id=\"UsernameToken-9B9EFFA9ECC1CA21A616254036475202\">\n" +
"            <wsse:Username>"+username+"</wsse:Username>\n" +
"            <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"+password+"</wsse:Password>\n" +
"            <wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">"+encoded+"</wsse:Nonce>\n" +
"            <wsu:Created>"+WebServiceDetail.getCreatedDate()+"</wsu:Created>\n" +
"         </wsse:UsernameToken>\n" +
"         <wsu:Timestamp wsu:Id=\"TS-9B9EFFA9ECC1CA21A616254036365611\">\n" +
"            <wsu:Created>"+WebServiceDetail.getCreatedDate()+"</wsu:Created>\n" +
"            <wsu:Expires>"+WebServiceDetail.getExpiredDate()+"</wsu:Expires>\n" +
"         </wsu:Timestamp>\n" +
"      </wsse:Security>\n" +
"   </soapenv:Header>\n" +
"   <soapenv:Body>\n" +
"      <typ:createStandardReceipt>\n" +
"         <typ:standardReceipt>\n" +
"            <com:Amount currencyCode=\""+recieptData.get("currencyCode")+"\">"+recieptData.get("amount")+"</com:Amount>\n" +
"            <com:GlDate>"+recieptData.get("glDate")+"</com:GlDate>\n" +
"            <com:MaturityDate>"+recieptData.get("maturityDate")+"</com:MaturityDate>\n" +
"            <com:OrgId>"+recieptData.get("orgId")+"</com:OrgId>\n" +
"            <com:CustomerId>"+recieptData.get("customerId")+"</com:CustomerId>\n" +
"            <com:ReceiptDate>"+recieptData.get("receiptDate")+"</com:ReceiptDate>\n" +
"            <com:ReceiptMethodId>"+recieptData.get("receiptMethodId")+"</com:ReceiptMethodId>\n" +
"            <com:ReceiptNumber>"+recieptData.get("receiptNumber")+"</com:ReceiptNumber>\n" +
"            <com:RemittanceBankAccountId>"+recieptData.get("remittanceBankAccountId")+"</com:RemittanceBankAccountId>\n" +
"         </typ:standardReceipt>\n" +
"      </typ:createStandardReceipt>\n" +
"   </soapenv:Body>\n" +
"</soapenv:Envelope>";

    // System.out.println("==>"+createReciept);
        return createReciept;
    }



}