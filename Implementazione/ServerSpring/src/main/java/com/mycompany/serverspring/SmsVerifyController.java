/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author march
 */
@RestController
public class SmsVerifyController {
    private static String param = ImportTxt.importJSON("settings_verify_sms.json");
    private static JSONObject obj = new JSONObject(param);
    private static String serviceSid = obj.getString("serviceSid");
    private static String account_Sid = obj.getString("account_Sid");
    private static String auth_token= obj.getString("auth_token");
    /**
     * Costruttore della classe
     */
    public SmsVerifyController() {
    }
    
    public String getserviceSid(){
       return SmsVerifyController.serviceSid;
    }
    
    public void setserviceSid(String serviceSid){
        SmsVerifyController.serviceSid = serviceSid;
    }
    
     public void setaccount_Sid(String account_Sid){
        SmsVerifyController.account_Sid = account_Sid;
    }
     
    public String getaccount_Sid(){
       return SmsVerifyController.account_Sid;
    }
    
    public void setauth_token(String auth_token){
        SmsVerifyController.account_Sid = auth_token;
    }
     
    public String getauth_token(){
       return SmsVerifyController.auth_token;
    }
    
    @PostMapping("smsVerify")
    @ResponseBody
    private boolean sendVerificaToken(@RequestParam("number") String number){
        try{
            Twilio.init(SmsVerifyController.account_Sid, SmsVerifyController.auth_token);
            Verification verification = Verification.creator(
                SmsVerifyController.serviceSid,
                "+39"+ number,
                "sms")
            .create();
            System.out.println("verification.getStatus()sendVerificaToken ---> " +  verification.getStatus());
            return true; //ok
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    @PostMapping("checkNumberToken")
    @ResponseBody
    private boolean sendVerificaToken(@RequestParam("tokenNumber") String tokenNumber, @RequestParam("number") String number){
        try{
            Twilio.init(SmsVerifyController.account_Sid, SmsVerifyController.auth_token);
                VerificationCheck verificationCheck = VerificationCheck.creator(
                    SmsVerifyController.serviceSid,
                    tokenNumber)
                .setTo("+39" + number).create();
            System.out.println("verificationCheck.getStatus() checkNumberToken --->" + verificationCheck.getStatus());
            if("approved".equals(verificationCheck.getStatus())){ //ok codice approvato
               return true; //approved
            }
            else if("pending".equals(verificationCheck.getStatus())) //codice errato
                return false;
            else 
                return false;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}
