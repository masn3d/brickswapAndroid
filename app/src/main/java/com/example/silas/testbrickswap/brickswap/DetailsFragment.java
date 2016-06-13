package com.example.silas.testbrickswap.brickswap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.silas.testbrickswap.R;

import com.example.silas.testbrickswap.extras.StaticVariables;
import com.example.silas.testbrickswap.network.VolleySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Silas on 05-06-2016.
 */
public class DetailsFragment extends Fragment {

    private String posterID;
    private String postID;

    private LegoSet item;
    private TextView detailsTitle;
    private TextView detailsPrice;
    private TextView detailsCity;
    private TextView detailsDate;
    private TextView detailsAuthor;
    private TextView detailsPhone;
    private TextView detailsDescription;

    private ImageView detailsImage;
    private Button detailsBackButton;

    private String title;
    private String price;
    private String city;
    private String date;
    private String author;
    private String phone;
    private String description;

    private String image;


    VolleySingleton volleySingleton;
    RequestQueue requestQueue;

    RequestHandler handler = new RequestHandler();

    public DetailsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View detailsView = inflater.inflate(R.layout.fragment_details, container, false);

        Bundle bundle = getArguments();
        posterID = (String) bundle.get("posterID");
        postID = (String) bundle.get("postID");
        System.out.println("BUNDLE: " + posterID + " " + postID);


        detailsImage = (ImageView) detailsView.findViewById(R.id.detailsImage);

        detailsTitle = (TextView) detailsView.findViewById(R.id.detailsTitle);
        detailsPrice = (TextView) detailsView.findViewById(R.id.detailsPrice);
        detailsCity = (TextView) detailsView.findViewById(R.id.detailsCity);
        detailsDate = (TextView) detailsView.findViewById(R.id.detailsDate);
        detailsAuthor = (TextView) detailsView.findViewById(R.id.detailsAuthor);
        detailsPhone = (TextView) detailsView.findViewById(R.id.detailsPhone);
        detailsDescription = (EditText) detailsView.findViewById(R.id.detailsDescription);

        detailsBackButton = (Button) detailsView.findViewById(R.id.detailsBackButton);


        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        requestQueue.add(getPoster(requestQueue, detailsView, posterID));





        return detailsView;
    }

    public void setup(){
        String imageUrl = item.getImageList().get(0).toString();
        Picasso.with(getContext()).load(imageUrl).into(detailsImage);

        detailsTitle.setText(item.getTitle());
        detailsPrice.setText(item.getPrice());

        detailsDate.setText(item.getPostDate());

        //detailsDescription.setText(item.getDescription());
    }

    public void setLegoSetData(LegoSet item){
        this.item = item;
        System.out.println("DETAILSFRAGMENT BABY");
        setup();

    }

    public JsonObjectRequest getPoster(RequestQueue requestQueue, View v, String posterID) {

        String deleteURL = StaticVariables.serverUrl + "/users/" + posterID;

        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, deleteURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            System.out.println("NIGGA" + response.getJSONObject("phone").toString());
                        detailsCity.setText(response.getJSONObject("city").toString());
                        detailsAuthor.setText(response.getJSONObject("username").toString());
                        detailsPhone.setText(response.getJSONObject("phone").toString());
                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                         Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
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



    //TODO METHODS
    //getPosterByID();

    //getPostByID();



}
