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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.silas.testbrickswap.R;
import com.example.silas.testbrickswap.extras.StaticVariables;

import java.util.Hashtable;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    EditText newUsernameText;
    EditText newPasswordText;
    EditText firstNameText;
    EditText lastNameText;
    EditText phoneText;
    EditText emailText;

    Button createUserButton;
    Button backToLoginButton;
    String UPLOAD_URL;
    String KEY_NEWUSERNAME = "newUserName";
    String KEY_NEWPASSWORD = "newUserPassword";
    String KEY_FIRSTNAME = "newFirstName";
    String KEY_LASTNAME = "newLastName";
    String KEY_PHONE = "newPhone";
    String KEY_EMAIL = "newEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        newUsernameText = (EditText) findViewById(R.id.newUsernameText);
        newPasswordText = (EditText) findViewById(R.id.newPasswordText);
        firstNameText = (EditText) findViewById(R.id.firstNameText);
        lastNameText = (EditText) findViewById(R.id.lastNameText);
        phoneText = (EditText) findViewById(R.id.phoneText);
        emailText = (EditText) findViewById(R.id.emailText);

        createUserButton = (Button) findViewById(R.id.createUserButton);
        backToLoginButton = (Button) findViewById(R.id.backToLoginButton);
        createUserButton.setOnClickListener(this);
        backToLoginButton.setOnClickListener(this);
    }

    private void createNewUser() {

        UPLOAD_URL = StaticVariables.serverUrl;

        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Creating User...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL + "/users/signup",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        //Should set url in singleton!!
                        //should set username and ID in singleton!!
                        //Should start home activity/fragment!!!


                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(SignupActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(SignupActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Getting Image Name
                String newUsername = newUsernameText.getText().toString().trim();
                String newPassword = newPasswordText.getText().toString().trim();
                String firstName = firstNameText.getText().toString().trim();
                String lastName = lastNameText.getText().toString().trim();
                String phone = phoneText.getText().toString().trim();
                String email = emailText.getText().toString().trim();

                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_NEWUSERNAME, newUsername);
                params.put(KEY_NEWPASSWORD, newPassword);
                params.put(KEY_FIRSTNAME, firstName);
                params.put(KEY_LASTNAME, lastName);
                params.put(KEY_PHONE, phone);
                params.put(KEY_EMAIL, email);


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
        if (v == createUserButton) {
            createNewUser();
        }
        if (v == backToLoginButton) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

            //change to signup activity
        }
    }
}