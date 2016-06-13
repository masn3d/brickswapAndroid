package com.example.silas.testbrickswap.brickswap;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.silas.testbrickswap.R;
import com.example.silas.testbrickswap.interfaces.IFragmentListener;

/**
 * Created by Silas on 03-06-2016.
 */
public class HomeFragment extends Fragment {

    IFragmentListener activityCommander;

    public HomeFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        return homeView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCommander = (IFragmentListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public void buttonClicked(View view){
    }
}
