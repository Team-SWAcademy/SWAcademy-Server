package carbonneutral.academy.api.service.Receipt;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.UUID;

public class OCRService {
    private static final String SECRET_KEY = " Vm9QSkROV1NlaVl6ZGxVQk5lZUxEdm1oamtCUEVEcG8=";
    private static final String API_URL = "https://dje491zhuj.apigw.ntruss.com/custom/v1/32024/0efb15320e6ff62e0a0dcb29ba1ae58276f31593f5d8c53020f4373761a39a2d/general";

    public static String OCRParse(String imageUrl){
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = createRequestHeader(url);
            createRequestBody(connection, imageUrl);

            StringBuilder response = getResponse(connection);

            StringBuilder result = parseResponseData(response);

            return result.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static HttpURLConnection createRequestHeader(URL url)  {
        try{
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;");
            connection.setRequestProperty("X-OCR-SECRET", SECRET_KEY);

            return connection;
        }
        catch(Exception e) {
            System.out.println("Error in createRequestHeader");
            e.printStackTrace();

            return null;
        }
    }

    private static void createRequestBody(HttpURLConnection connection, String imageUrl) throws IOException {
        JSONObject image = new JSONObject();

        image.put("format", "PNG");
        image.put("name", "requestImage");
        image.put("url", imageUrl);

        JSONArray images = new JSONArray();
        images.add(image);

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("version","V1");
        jsonRequest.put("requestId", UUID.randomUUID().toString());
        jsonRequest.put("timestamp",System.currentTimeMillis());
        jsonRequest.put("lang", "ko");
        jsonRequest.put("resultType","string");
        jsonRequest.put("images",images);

        connection.connect();
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.write(jsonRequest.toString().getBytes(StandardCharsets.UTF_8));
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    private static BufferedReader checkResponse(HttpURLConnection connection) throws IOException, NullPointerException{
        int responseCode = connection.getResponseCode();
        BufferedReader bufferedReader;


        if (responseCode == 200) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        return bufferedReader;
    }

    private static StringBuilder getResponse(HttpURLConnection connection) throws IOException {
        BufferedReader bufferedReader = checkResponse(connection);

        String str;
        StringBuilder response = new StringBuilder();

        while((str = bufferedReader.readLine()) != null){
            response.append(str);
        }
        bufferedReader.close();

        return response;
    }

    private static StringBuilder parseResponseData(StringBuilder response) throws ParseException {
        JSONParser responseParser = new JSONParser(response.toString());
        LinkedHashMap<String, String> resultMap = (LinkedHashMap<String, String>) responseParser.parse();

        JSONObject parsed = new JSONObject(resultMap);
        JSONArray parsedImages = (JSONArray) parsed.get("images");
        StringBuilder result = new StringBuilder();

        if(parsedImages != null){
            JSONObject parsedImage = (JSONObject) parsedImages.get(0);
            JSONArray parsedText = (JSONArray) parsedImage.get("fields");

            for(int i=0 ; i < parsedText.size(); i++){
                JSONObject text = (JSONObject) parsedText.get(i);
                result.append(text.getAsString("inferText")).append(" ");
            }
        }

        return result;
    }
}
