package com.example.admin.e_sapa_ver_3_00.RecourseFile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 28.07.2015.
 */
public class Connection {
    public String Connection() {
        StringBuffer response = new StringBuffer();
        try {
            URL url1 = new URL(resourceFile.url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("SOAPAction", resourceFile.soapAction);
            connection.setRequestProperty("Content-type", "text/xml; charset=utf-8");
            connection.setConnectTimeout(4000);
            connection.setRequestProperty("Content-Length", String.valueOf(resourceFile.envelope.length()));
            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(resourceFile.envelope);
            wr.flush();
            wr.close();

            InputStream is = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }


    public String Connection(String request) {
        StringBuffer response = new StringBuffer();
        try {
            /*
             *
             * There i build request string, and set property to connection exemplar like "Content_type" etc...
             * and return String response data
             *
             * */
            URL url = new URL(resourceFile.ajaxreferrer);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Host", "kgd.gov.kz");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("Content-Length", String.valueOf(request.getBytes().length));
            conn.setConnectTimeout(4000);
            DataOutputStream DOS = new DataOutputStream(conn.getOutputStream());
            DOS.write(request.getBytes());
            DOS.flush();
            DOS.close();

            InputStream is = conn.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
