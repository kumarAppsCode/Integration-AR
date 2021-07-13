package com.integration.ar.WebService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.integration.ar.Controller.ReceiptCo;
import com.integration.ar.Payload.ReceiptReversalPayload;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class InvokeReceiptReversal {

    public String responseToCreateReciept(String result) {
        JSONObject json = new JSONObject();
        json.put("cash_reciept_id", result);
        return json.toJSONString();
    }

    // create Standard Receipt Reversal
public Map<String, Object> createReverseReceipt(Map<String, Object> content) throws IOException {
    String baseUrl=WebServiceDetail.baseUrl(content.get("environment"));
    OkHttpClient client = new OkHttpClient();
    MediaType mediaType = MediaType.parse("text/xml");
    // System.out.println(baseUrl);
    String xmlInput = ReceiptReversalPayload.receiptReversalPayload(content);
    // System.out.println(xmlInput);
    RequestBody body = RequestBody.create(mediaType, xmlInput);
    Request request
    = new Request.Builder().url(baseUrl
            + "/fscmService/StandardReceiptService?wsdl").post(body).addHeader("content-type",
              "text/xml").addHeader("cache-control",
              "no-cache").addHeader("SOAPACTION", "createReverseReceipt").build();
    Response response = null;

    response = client.newCall(request).execute();
    // System.out.println("response=>"+response);
    InputStream isr = response.body().byteStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(isr));
    StringBuilder out = new StringBuilder();
    String resultsXml;
    while ((resultsXml = reader.readLine()) != null) {
        out.append(resultsXml);
    }
    String responseString = out.toString();
    responseString = responseString.substring(responseString.indexOf("<?xml"), responseString.indexOf("</env:Envelope>")) + "</env:Envelope>";
    // System.out.println("==>1"+responseString);
    return xmlResponsetoJsonConvert(response.code(), responseString);
    
}

// create Standard Receipt-Json Conversion
public Map<String, Object> xmlResponsetoJsonConvert(int responseCode, String responseString) {
    Map<String, Object> ls = new HashMap();
    try {
        ls.put("responseCode", responseCode);
        DocumentBuilder builder
                = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(responseString));
        Document doc = builder.parse(src);
        if (responseCode != 200) {
            Element rootElement = doc.getDocumentElement();
            // faultstring
            NodeList item = rootElement.getElementsByTagName("faultstring");
            String out = null;
            // System.out.println("out==>"+out);
            if(responseCode==500){
                out = item.item(0).getTextContent();
           }else{
                out = item.item(0).getTextContent();
                out = out.substring(out.indexOf("<TEXT>") + 6, out.indexOf("</TEXT>"));
            }
            // System.out.println("out==>"+out);
            // faultcode
            NodeList item1 = rootElement.getElementsByTagName("faultcode");
            String out1 = item1.item(0).getTextContent();
            ls.put("faultstring", out);
            ls.put("faultcode", out1);
            ls.put("reversalCategory", null);
            ls.put("receiptNumber", null);
            return ls;
        } else {
            String reversalCat= doc.getElementsByTagName("ns3:ReversalCategory").item(0).getTextContent();
            String receiptNum= doc.getElementsByTagName("ns3:ReceiptNumber").item(0).getTextContent();
            ls.put("faultstring", null);
            ls.put("faultcode", null);
            ls.put("reversalCategory", reversalCat);
            ls.put("receiptNumber", receiptNum);
            return ls;
        }
    } catch (ParserConfigurationException ex) {
        Logger.getLogger(ReceiptCo.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SAXException ex) {
        Logger.getLogger(ReceiptCo.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(ReceiptCo.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ls;
}







}