package com.example.silas.testbrickswap.brickswap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.silas.testbrickswap.R;
import com.example.silas.testbrickswap.extras.GlobalVariables;
import com.example.silas.testbrickswap.extras.StaticVariables;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameText;
    EditText passwordText;
    Button loginButton;
    Button signupButton;
    String UPLOAD_URL; //Using serverUrl
    String KEY_USERNAME = "userName";
    String KEY_PASSWORD = "userPassword";
    String userName;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = (EditText) findViewById(R.id.usernameText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        loginButton = (Button) findViewById(R.id.loginButton);
        signupButton = (Button) findViewById(R.id.signUpButton);
        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
    }

    /*
        private void logInBeta(){

            String theUsername = usernameText.getText().toString().trim();
            String thePassword = passwordText.getText().toString().trim();

            String url = "http://abfaea33.ngrok.io/users/login";


            Map<String, String> params = new HashMap();
            params.put("userName", theUsername);
            params.put("userPassword", thePassword);

            JSONObject parameters = new JSONObject(params);
            System.out.println();
            System.out.println("params.toString();");
            System.out.println(params.toString());


            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {


                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    if(error != null) {
                        Toast.makeText(LoginActivity.this, "ERROR: " + error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "ERROR: IS ALL NULL BITCH", Toast.LENGTH_LONG).show();
                    }
                }
            });

            //Creating a Request Queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            //Adding request to the queue
            requestQueue.add(jsonRequest);
        }
    */
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
                        Toast.makeText(LoginActivity.this, "Successful login. Welcome " + userName, Toast.LENGTH_LONG).show();
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

    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            logIn();
        }
        if (v == signupButton) {

            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);

            //change to signup activity
        }
    }
}