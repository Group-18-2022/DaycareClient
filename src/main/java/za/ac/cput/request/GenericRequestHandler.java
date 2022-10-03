package za.ac.cput.request;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenericRequestHandler<T> {
    private static OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public void postRequestHandler(T o, String createUrl) {
        try {
            final String URL = createUrl;
            Gson g = new Gson();
            String jsonString = g.toJson(o);

            RequestBody body = RequestBody.create(JSON, jsonString);
            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteRequestHandler(String id, String deleteUrl)
    {
        String url = deleteUrl + id;
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        try(Response response = client.newCall(request).execute()) {

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRequestHandler(String id, String readUrl)
    {
        try {
            String url = readUrl + id;

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            return response.body().string();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public List<Object> getAllRequestHandler(String allUrl)
    {
        List<Object> objectList = new ArrayList<>();
        try {
            String URL = allUrl;

            Request request = new Request.Builder()
                    .url(URL)
                    .build();
            Response response = client.newCall(request).execute();

            String responseBod = response.body().string();

            JSONArray identities = new JSONArray(responseBod);


            for (int i =0; i<identities.length(); i++) {
                JSONObject identity = identities.getJSONObject(i);
                Gson g = new Gson();
                var returnType = g.fromJson(identity.toString(), Object.class);
                objectList.add(returnType);
                System.out.println(returnType.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return objectList;
    }
}
