package com.example.silas.testbrickswap.brickswap;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.silas.testbrickswap.R;
import com.example.silas.testbrickswap.interfaces.IFragmentListener;


public class SearchFragment extends Fragment {

    private static EditText searchInput;

    IFragmentListener activityCommander;

        public SearchFragment() {
            // Empty constructor required for fragment subclasses
        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View searchView = inflater.inflate(R.layout.fragment_search, container, false);

        searchInput = (EditText) searchView.findViewById(R.id.searchInput);
        final Button searchButton = (Button) searchView.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(v);
                    }
                }
        );

        return searchView;
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
        activityCommander.search(searchInput.getText().toString());
    }

    }
