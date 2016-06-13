package com.example.silas.testbrickswap.brickswap;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.silas.testbrickswap.R;
import com.example.silas.testbrickswap.extras.GlobalVariables;
import com.example.silas.testbrickswap.extras.StaticVariables;
import com.example.silas.testbrickswap.network.ServerFacade;
import com.example.silas.testbrickswap.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.xml.transform.ErrorListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultsFragment extends Fragment {

    GlobalVariables gv = new GlobalVariables();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    VolleySingleton volleySingleton;
    RequestQueue requestQueue;
    ImageLoader imageLoader;
    private String searchText;

    View resultsView;

    ArrayList<LegoSet> legoSetsList = new ArrayList();

    public ResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultsFragment newInstance(String param1, String param2) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

     public JsonArrayRequest getAllPosts(RequestQueue requestQueue, View v) {

         View view = v;

        final String jsonURL = StaticVariables.serverUrl;

        //LegoSet[] legoSets;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                jsonURL + "/posts",
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
                                    System.out.println("B4 TryCatch");
                                    try {
                                        System.out.println("B4 jsonObject = jsonArray.getJSONOBject(j) - j is: " + j + " jsonObject is: " + jsonArray.get(j));

                                        arrayObject = jsonArray.get(j).toString();

                                        /*
                                        System.out.println("B4 lengthOfString");
                                        int lengthOfString = new String(arrayObject.toString()).length();
                                        System.out.println("B4 startSUB");
                                        String startSub = arrayObject.toString().substring(0,24);
                                        System.out.println("B4 endSub - startSub is: " + startSub);
                                        String endSub = arrayObject.toString().substring(24, lengthOfString);
                                        System.out.println("B4 finalURL - endSub is: " + endSub);
                                        String finalUrl = startSub + endSub;
                                        System.out.println("FINAL URL: " + finalUrl);
                                        */
                                        String finalUrl = jsonURL + "/postImages/" + arrayObject;
                                        imageList.add(finalUrl);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                }

                                System.out.println("ImageList is: " + imageList);

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
                        System.out.println("LEGOLIST" + legoSetsList);


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

    public View getView(){
        return this.resultsView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        resultsView = inflater.inflate(R.layout.fragment_list_view, container, false);

        TextView text = (TextView) resultsView.findViewById(R.id.tempTextView);

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        searchText = getArguments().getString("input");

        requestQueue.add(getAllPosts(requestQueue, resultsView));

        ListAdapter legoListAdapter = new CustomAdapter(getContext(), legoSetsList);
        ListView legoListView = (ListView) resultsView.findViewById(R.id.projectsListView);
        legoListView.setAdapter(legoListAdapter);

        return resultsView;
    }


    private void setUpAdapter(View resultsView){

        ListAdapter legoListAdapter = new CustomAdapter(getContext(), legoSetsList);
        ListView legoListView = (ListView) resultsView.findViewById(R.id.projectsListView);
        legoListView.setAdapter(legoListAdapter);
    }


    private void parseJSONResponse(JSONObject response){
        if (response == null || response.length() == 0){
            return;
        }
    }

}
