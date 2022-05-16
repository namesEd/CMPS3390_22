package a11.ekyles.caloriecounter;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.api.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class APIBridge {

    private String apiKey, stringQueryURL;
    private UIBind uiBind;
    private CalorieModel calorieModel = new CalorieModel();

    public APIBridge(UIBind uiBind, Context context) {
        this.uiBind = uiBind;
        String apiJson = null;
        try {
            InputStream is = context.getAssets().open("api.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            apiJson = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(apiJson);
            this.apiKey = jsonObject.getString("apikey");
            this.stringQueryURL = jsonObject.getString("queryURL");
            Log.i("JSON", "We have a json object");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void GenerateCalorieModel(String item) {
        String url = null;
        try {
            url = String.format(this.stringQueryURL,
                    URLEncoder.encode(item, String.valueOf(StandardCharsets.UTF_8)), this.apiKey);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.i("REST", url);

        /*unfinished :(
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                response -> {
                    Log.i("REST", String.valueOf(response));
                    try{
                        JSONArray tmp = new JSONArray((response));
                        //calorieModel.
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });






    public void getCalories(String text) {
        String url = String.format(this.stringQueryURL, text, this.apiKey);

    }
*/

/*
  String query = "pizza";
    Response response = DownloadManager.Request.Get("https://api.calorieninjas.com/v1/nutrition?query="+query)
            .addHeader("X-Api-Key", "apikey")
            .execute();



    }

 */

    }
}
