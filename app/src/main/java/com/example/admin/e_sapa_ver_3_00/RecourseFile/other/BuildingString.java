package com.example.admin.e_sapa_ver_3_00.RecourseFile.other;

import android.util.Log;

import com.example.admin.e_sapa_ver_3_00.RecourseFile.resourceFile;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 26.08.2015.
 */
public class BuildingString {

        public void SoapActionBuildString(String part1,String part2,String part3,String part4,int CityId ,String CityText){
        resourceFile.SupiCode=part1+part2+part3+part4;
        resourceFile.envelope="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:CheckcodeService>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:supiCode>"+ resourceFile.SupiCode+"</tem:supiCode>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:cityId>"+CityId+"</tem:cityId>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:city>"+CityText+"</tem:city>\n" +
                "      </tem:CheckcodeService>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
            Log.d("LOG", "Envelope text =" + resourceFile.envelope);
    }


    public void buildParamSendRequestString(String param1, String param2, String param3, String param4, String param5, String param6) {
        JSONObject jsonObjectParamUser = new JSONObject();
        try {
            jsonObjectParamUser.put("ContactName", param1);
            jsonObjectParamUser.put("ContactPhone", param2);
            jsonObjectParamUser.put("Description", param3);
            jsonObjectParamUser.put("Email", param4);
            jsonObjectParamUser.put("Location", param5);
            jsonObjectParamUser.put("PuidType", param6);
            resourceFile.sendrequestData = jsonObjectParamUser.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
