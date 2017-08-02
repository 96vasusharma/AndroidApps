package com.cris.android.sectionalattendance;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by Vasu Sharma on 22-07-2017.
 */

public class QueryUtils {
    public static List<Employee> extractEmployee(String employeeUrl){

        URL url = createUrl(employeeUrl);

        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return extractEmployeesFromJson(jsonResponse);
    }


    private static URL createUrl(String employeeUrl){
        URL url = null;
        try {
            url = new URL(employeeUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException
    {
        String jsonResponse = "";
        if (url == null){
            return jsonResponse;
        }
        HttpURLConnection httpConnection = null;
        InputStream inputStream = null;
        try{
            httpConnection = (HttpURLConnection)url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if (httpConnection.getResponseCode()==200){
                inputStream = httpConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (httpConnection!=null){
                httpConnection.disconnect();
            }
            if (inputStream!=null){
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException
    {
        StringBuilder output = new StringBuilder();
        String line;
        if (inputStream!=null){
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            line = bufferedReader.readLine();
            while (line!=null){
                output.append(line);
                line = bufferedReader.readLine();

            }
        }

        return output.toString();
    }
    private static List<Employee> extractEmployeesFromJson(String employeeJson){
        if (TextUtils.isEmpty(employeeJson)){
            Log.i(QueryUtils.class.getSimpleName(),"well done");
            return null;
        }

        ArrayList<Employee> employees = new ArrayList<>();

        // after i get the url

//        try {
//            JSONObject jsonObject = new JSONObject(employeeJson);
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//
        return null;
    }
}
