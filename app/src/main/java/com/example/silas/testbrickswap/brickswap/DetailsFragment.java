package com.example.silas.testbrickswap.brickswap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        View detailsView = inflater.inflate(R.layout.activity_details, container, false);

        return detailsView;
    }

}
