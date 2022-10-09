package za.ac.cput.views.consoleapp;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.ClassRoom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleApp
{
    public ConsoleApp() {
    }
    private static OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    public void post(Object o, String createUrl) //pass the url from the Controller class for post/create
    //The o object is the whatever class object you are posting eg. Principal object, Child object, etc.
    {
        try
        {
            final String URL = createUrl;
            Gson g = new Gson();
            String jsonString = g.toJson(o);

            RequestBody body = RequestBody.create(JSON, jsonString);
            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
        }
        catch (IOException e)
        {
        }
    }

    public void delete(String id, String deleteUrl) //pass the url from the Controller class for delete
    {
        String url = deleteUrl+id;
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        try(Response response = client.newCall(request).execute())
        {
        }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
    }

    public String readOne(String id, String readUrl) //pass the url from the Controller class for getOne/readOne
    {
        try
        {
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
    public static List<Object> getAll(String allUrl) //pass the url from the Controller class for findAll/getAll
    {

        List<Object> objectList = new ArrayList<>();
        try
        {
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
                Object o = g.fromJson(identity.toString(), ClassRoom.class);
                objectList.add(o);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(objectList);
        return objectList;
    }


    public static void main(String[] args)
    {

    }
}
