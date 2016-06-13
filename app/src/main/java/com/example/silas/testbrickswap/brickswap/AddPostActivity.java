package com.example.silas.testbrickswap.brickswap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.silas.testbrickswap.R;
import com.example.silas.testbrickswap.extras.StaticVariables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    //-----------------Upload Image Variables--------------------------
    private Button buttonChoose;
    private Button buttonUpload;
    private ImageView imageView;
    private EditText editTextTitle;
    private EditText editTextPrice;
    private EditText editTextCity;
    private Spinner spinnerType;
    private String spinnerValue = "SpinnerEventNotTriggered";
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    private String KEY_IMAGE = "image";
    private String KEY_TITLE = "title";
    private String KEY_PRICE = "price";
    private String KEY_TYPE = "type";
    private String KEY_CITY = "city";
    private String KEY_POSTERID = "posterId";

    //--------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        //Intent intent = getIntent();
        //  UPLOAD_URL = intent.getStringExtra("url");
        // tempPosterID = intent.getStringExtra("userID");

        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        editTextTitle = (EditText) findViewById(R.id.titleText);
        editTextPrice = (EditText) findViewById(R.id.priceText);
        editTextCity = (EditText) findViewById(R.id.cityText);
        spinnerType = (Spinner) findViewById(R.id.legoTypeSpinner);
        imageView = (ImageView) findViewById(R.id.imageView);
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        spinnerType.setOnItemSelectedListener(this);
    }

    //-------------------------------------------SELECT IMAGE FROM GALLERY--------------------------------------

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //-------------------------------------------UPLOADING IMAGE-------------------------------------------------------------

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

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onClick(View v) {

        if (v == buttonChoose) {
            showFileChooser();
        }
        if (v == buttonUpload) {
            System.out.println(editTextTitle.getText().toString().trim());

            uploadImage();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
        spinnerValue = parent.getItemAtPosition(pos).toString();
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        spinnerValue = "unknown";
    }
}