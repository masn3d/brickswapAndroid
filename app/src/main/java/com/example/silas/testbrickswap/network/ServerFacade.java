package com.example.silas.testbrickswap.network;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by martins on 6/3/16.
 */
public class ServerFacade {

    public boolean reqDone = false;


    static public JsonArrayRequest getAllPosts(RequestQueue requestQueue,String URL) {

        String jsonURL = URL;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                jsonURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String title = jsonObject.getString("title");
                                String imageUrl = jsonObject.getString("imageLinks");
                                String postDate = jsonObject.getString("postDate");
                                String posterId = jsonObject.getString("posterId");
                                String price = jsonObject.getString("price");
                                String productName = jsonObject.getString("productName");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", "ERROR");
                    }
                }
        );
        return jsonArrayRequest;
    }
}