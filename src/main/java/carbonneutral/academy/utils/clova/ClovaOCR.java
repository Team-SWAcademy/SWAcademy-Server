package carbonneutral.academy.utils.clova;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class ClovaOCR {

    @Value("${naver.service.url}")
    private String API_URL;

    @Value("${naver.service.secretKey}")
    private String SECRET_KEY;

    public String OCRParse(String imageUrl){
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = createRequestHeader(url);
            createRequestBody(connection, imageUrl);

            StringBuilder response = getResponse(connection);

            // 응답 로그 출력
            System.out.println("API Response: " + response.toString());

            String result = parseResponseData(response.toString());

            return result;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpURLConnection createRequestHeader(URL url) throws IOException {
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

    private void createRequestBody(HttpURLConnection connection, String imageUrl) throws IOException {
        JSONObject image = new JSONObject();

        image.put("format", "png");
        image.put("name", "requestImage");
        image.put("url", imageUrl);

        JSONArray images = new JSONArray();
        images.put(image);

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("version", "V1");
        jsonRequest.put("requestId", UUID.randomUUID().toString());
        jsonRequest.put("timestamp", System.currentTimeMillis());
        jsonRequest.put("lang", "ko");
        jsonRequest.put("resultType", "string");
        jsonRequest.put("images", images);

        connection.connect();
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.write(jsonRequest.toString().getBytes(StandardCharsets.UTF_8));
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    private BufferedReader checkResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        BufferedReader bufferedReader;

        if (responseCode == 200) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        return bufferedReader;
    }

    private StringBuilder getResponse(HttpURLConnection connection) throws IOException {
        BufferedReader bufferedReader = checkResponse(connection);

        String str;
        StringBuilder response = new StringBuilder();

        while ((str = bufferedReader.readLine()) != null) {
            response.append(str);
        }
        bufferedReader.close();

        return response;
    }

    private String parseResponseData(String response) {
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray parsedImages = jsonResponse.getJSONArray("images");
        StringBuilder result = new StringBuilder();

        if (parsedImages != null && parsedImages.length() > 0) {
            JSONObject parsedImage = parsedImages.getJSONObject(0);
            JSONArray parsedText = parsedImage.getJSONArray("fields");

            for (int i = 0; i < parsedText.length(); i++) {
                JSONObject text = parsedText.getJSONObject(i);
                result.append(text.getString("inferText")).append(" ");
            }
        }

        // 파싱된 결과 로그 출력
        System.out.println("Parsed Result: " + result.toString());

        return result.toString();
    }
}
