package com.example.silas.testbrickswap.brickswap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.silas.testbrickswap.R;
import com.example.silas.testbrickswap.interfaces.IFragmentListener;


public class MyProjectsFragment extends Fragment {

    IFragmentListener activityCommander;

    public MyProjectsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View projectView = inflater.inflate(R.layout.fragment_my_projects, container, false);

        final Button addProjectButton = (Button) projectView.findViewById(R.id.addProjectButton);

        addProjectButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(v);
                    }
                }
        );

           return projectView;
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
        Intent intent = new Intent(getContext(), AddPostActivity.class);
        startActivity(intent);
    }

}
