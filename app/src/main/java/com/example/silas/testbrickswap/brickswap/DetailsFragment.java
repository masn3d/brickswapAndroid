package com.example.silas.testbrickswap.brickswap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.silas.testbrickswap.R;

/**
 * Created by Silas on 05-06-2016.
 */
public class DetailsFragment extends Fragment {



    public DetailsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View detailsView = inflater.inflate(R.layout.fragment_details, container, false);

        TextView detailsTitle = (TextView) detailsView.findViewById(R.id.detailsTitle);
        TextView detailsPrice = (TextView) detailsView.findViewById(R.id.detailsPrice);
        TextView detailsCity = (TextView) detailsView.findViewById(R.id.detailsCity);
        TextView detailsDate = (TextView) detailsView.findViewById(R.id.detailsDate);
        TextView detailsAuthor = (TextView) detailsView.findViewById(R.id.detailsAuthor);
        TextView detailsPhone = (TextView) detailsView.findViewById(R.id.detailsPhone);
        EditText detailsDescritpion = (EditText) detailsView.findViewById(R.id.detailsDescription);

        Button detailsBackButton = (Button) detailsView.findViewById(R.id.detailsBackButton);



        return detailsView;
    }

}
