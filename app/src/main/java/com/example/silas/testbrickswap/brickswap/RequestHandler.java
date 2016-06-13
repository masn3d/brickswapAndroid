package com.example.silas.testbrickswap.brickswap;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.silas.testbrickswap.extras.StaticVariables;

import java.util.ArrayList;

public class RequestHandler {

    ArrayList<LegoSet> legoSetsList = new ArrayList();

    //We delete the user.
    public JsonObjectRequest deleteUser(RequestQueue requestQueue, View v) {

        String deleteURL = StaticVariables.serverUrl + "/users/" + StaticVariables.userId;

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, deleteURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Toast.makeText(LoginActivity.this, "ERROR: " + response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(LoginActivity.this, "ERROR: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        return getRequest;
    }


    //Takes in posterID. Is used when we click on a post on a list.
    public JsonObjectRequest getPoster(RequestQueue requestQueue, View v, String posterID) {

        String deleteURL = StaticVariables.serverUrl + "/users/" + posterID;

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, deleteURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {



                        // Toast.makeText(LoginActivity.this, "ERROR: " + response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(LoginActivity.this, "ERROR: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        return getRequest;
    }


    //Gets all posts assosiated with the posterID. Can be used for our logged in user or other posters.
    public JsonArrayRequest getUsersPosts(RequestQueue requestQueue, View v, String posterID) {

        View view = v;

        final String jsonURL = StaticVariables.serverUrl;
        String getUsersPostsURL = jsonURL + "/posts/myPosts/" + posterID;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                jsonURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for (int i = 0; i < response.length(); i++) {
                                LegoSet set;
                                JSONObject responseObject = response.getJSONObject(i);

                                String id = responseObject.getString("_id");
                                String title = responseObject.getString("title");
                                String postDate = responseObject.getString("postDate").substring(0,10);
                                String posterId = responseObject.getString("posterId");
                                String price = responseObject.getString("price");
                                String productName = responseObject.getString("productName");

                                JSONArray jsonArray = responseObject.getJSONArray("imageLinks");
                                System.out.println("LENGTH OF JSONARRAY: " + jsonArray.length());
                                ArrayList<String> imageList = new ArrayList<>();

                                JSONObject jsonObject;
                                String arrayObject;

                                if(jsonArray.length() > 0){
                                    for (int j = 0; j < jsonArray.length(); j++) {
                                        try {
                                            arrayObject = jsonArray.get(j).toString();

                                            String finalUrl = jsonURL + "/postImages/" + arrayObject;
                                            imageList.add(finalUrl);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                if(!imageList.isEmpty()) {
                                    set = new LegoSet(id, postDate, posterId, price, productName, title, imageList);
                                }else{
                                    set = new LegoSet(id, postDate, posterId, price, productName, title);
                                }
                                legoSetsList.add(set);
                            }
                         //   setUpAdapter(getView());


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


    public JsonObjectRequest getPostByID(RequestQueue requestQueue, View v, String postID) {

        final String jsonURL = StaticVariables.serverUrl;

        String getPostURL = jsonURL + "/posts/" + postID;

        JsonObjectRequest getPostByIDRequest = new JsonObjectRequest(Request.Method.GET, getPostURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            LegoSet set;
                            JSONObject responseObject = response;

                            String id = responseObject.getString("_id");
                            String title = responseObject.getString("title");
                            String postDate = responseObject.getString("postDate").substring(0,10);
                            String posterId = responseObject.getString("posterId");
                            String price = responseObject.getString("price");
                            String productName = responseObject.getString("productName");

                            JSONArray jsonArray = responseObject.getJSONArray("imageLinks");
                            ArrayList<String> imageList = new ArrayList<>();

                            JSONObject jsonObject;
                            String arrayObject;

                            if(jsonArray.length() > 0){
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    try {
                                        arrayObject = jsonArray.get(j).toString();

                                        String finalUrl = jsonURL + "/postImages/" + arrayObject;
                                        imageList.add(finalUrl);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            if(!imageList.isEmpty()) {
                                set = new LegoSet(id, postDate, posterId, price, productName, title, imageList);
                            }else{
                                set = new LegoSet(id, postDate, posterId, price, productName, title);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Toast.makeText(LoginActivity.this, "ERROR: " + response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(LoginActivity.this, "ERROR: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        return getPostByIDRequest;
    }
}