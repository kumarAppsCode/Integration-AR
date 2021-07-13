package com.integration.ar.Controller;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.ar.PoJo.CreateReceiptBody;
import com.integration.ar.WebService.InvokeReceipt;
import com.integration.ar.WebService.InvokeReceiptReversal;

import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/ar/receipt")
public class ReceiptCo {


    @RequestMapping(value ="/Receipt", method = RequestMethod.POST)
    public Response createReciept(String content) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        CreateReceiptBody bodyData=objectMapper.readValue(content, CreateReceiptBody.class);
        InvokeReceipt iw=new InvokeReceipt();
        
        return Response.ok()
        .header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
        .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
        .entity(iw.responseToCreateReciept(iw.createReciept(bodyData)))
        .build();
    } 

    
    @RequestMapping(value ="/createStandardReceipt", method = RequestMethod.POST)
    public ResponseEntity<Object> createStandardReciept(@RequestBody  Map<String, Object> content) throws IOException{
        System.out.println("Key==>"+content.get("environment"));
        InvokeReceipt iw=new InvokeReceipt();
        return ResponseEntity.ok(iw.createStandardReciept(content));
    } 

    @RequestMapping(value ="/createReverseReceipt", method = RequestMethod.POST)
    public ResponseEntity<Object> createReverseReceipt(@RequestBody  Map<String, Object> content) throws IOException{
        System.out.println("Key==>"+content.get("environment"));
        InvokeReceiptReversal iw=new InvokeReceiptReversal();
        return ResponseEntity.ok(iw.createReverseReceipt(content));
    } 



}