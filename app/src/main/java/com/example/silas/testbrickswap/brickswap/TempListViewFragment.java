package com.example.silas.testbrickswap.brickswap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.silas.testbrickswap.R;
import com.example.silas.testbrickswap.extras.StaticVariables;
import com.example.silas.testbrickswap.network.VolleySingleton;


/**
 * Created by Silas on 05-06-2016.
 */
public class TempListViewFragment extends Fragment {

    private String GET_URL;

    private TextView textView;

    public TempListViewFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.temp_list, container, false);

        textView = (TextView) layout.findViewById(R.id.tempTextView);


        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        GET_URL = StaticVariables.serverUrl;
        StringRequest request = new StringRequest(Request.Method.GET, GET_URL +"/posts", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                textView.setText("RESPONSE OMG " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                textView.setText("ERROR " + error.getMessage());
            }
        });

        requestQueue.add(request);

        return layout;
    }



}
