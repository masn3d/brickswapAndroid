package com.example.silas.testbrickswap.brickswap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.silas.testbrickswap.extras.StaticVariables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by masn3d on 13-06-2016.
 */
public class RequestHandler {

    private String UPLOAD_URL;
    private String userName;
    private String userID;

    private String KEY_USERNAME;
    private String KEY_PASSWORD;

    private void logIn() {

        //setting UPLOAD_URL to correct server url.
        UPLOAD_URL = StaticVariables.serverUrl;

        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Logging in...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL + "/users/login",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {

                        if (res.contains("#SUCCES#")) {

                            String[] responseArray = res.split(",");

                            userName = responseArray[1];
                            userID = responseArray[2];

                            StaticVariables.userId = userID;
                            StaticVariables.username = userName;

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                            startActivity(intent);
                        }

                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(LoginActivity.this, "Succesful login. Welcome " + userName, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(LoginActivity.this, "ERROR: " + volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Getting Image Name
                String theUsername = usernameText.getText().toString().trim();
                String thePassword = passwordText.getText().toString().trim();


                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_USERNAME, theUsername);
                params.put(KEY_PASSWORD, thePassword);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void uploadImage() {

        String UPLOAD_URL = StaticVariables.serverUrl;

        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL + "/posts/upload",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(AddPostActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(AddPostActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String title = editTextTitle.getText().toString().trim();
                String price = editTextPrice.getText().toString().trim();
                String city = editTextCity.getText().toString().trim();
                String type = spinnerValue;

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_TITLE, title);
                params.put(KEY_PRICE, price);
                params.put(KEY_TYPE, type);
                params.put(KEY_CITY, city);
                params.put(KEY_POSTERID, StaticVariables.userId); //remember to change this to real posterID*/

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    public JsonArrayRequest getAllPosts(RequestQueue requestQueue, View v) {

        View view = v;

        String jsonURL = StaticVariables.serverUrl;

        //LegoSet[] legoSets;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                jsonURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        System.out.println("JSON ARRAY Response: " + response.toString());
                        System.out.println("RESPONSE LENGTH: " + response.length());
                        try {
                            System.out.println("Trying to 4 loop");

                            for (int i = 0; i < response.length(); i++) {
                                LegoSet set;
                                JSONObject responseObject = response.getJSONObject(i);
                                System.out.println("Inside 4 loop");

                                System.out.println("JSON OBJECT: " + responseObject.toString());


                                String id = responseObject.getString("_id");
                                String title = responseObject.getString("title");
                                String postDate = responseObject.getString("postDate");
                                String posterId = responseObject.getString("posterId");
                                String price = responseObject.getString("price");
                                String productName = responseObject.getString("productName");

                                JSONArray jsonArray = responseObject.getJSONArray("imageLinks");

                                ArrayList<String> imageList = new ArrayList<>();

                                if(!imageList.isEmpty()){
                                    for (int j = 0; j < jsonArray.length(); j++) {
                                        try {
                                            JSONObject jsonObject = jsonArray.getJSONObject(j);

                                            //jsonObject.toString().replace("\\/","/");
                                            //String startSub = jsonObject.toString().substring(0,23);
                                            //String endSub = jsonObject.toString().substring(25);
                                            //String combined = startSub + endSub;

                                            //StringBuilder sb = new StringBuilder(jsonObject.toString());
                                            //sb.deleteCharAt(24);
                                            //String done = sb.toString();
                                            //System.out.println("MAGIC: " + done);

                                            imageList.add(jsonObject.toString());
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
                            setUpAdapter(getView());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("LEGO_SET_SIZE: " + legoSetsList.size());


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
