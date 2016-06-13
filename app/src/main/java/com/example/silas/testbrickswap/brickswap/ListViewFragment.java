package com.example.silas.testbrickswap.brickswap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.silas.testbrickswap.R;


/**
 * Created by Silas on 03-06-2016.
 */
public class ListViewFragment extends Fragment {

    public ListViewFragment(){

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View resultsView = inflater.inflate(R.layout.fragment_list_view, container, false);
/*


        LegoSet[] legoSetsObjects = new LegoSet[]{
                new LegoSet("Star Wars", R.mipmap.legostarwars, "Lego set of the Star Wars branch.", 24.5, "George Walton Lucas", "Modesto, California", "25-05-2016"),
                new LegoSet("Marvel: Super Heroes", R.mipmap.legomarvel, "Lego set of the Marvel branch.", 11.3, "Stanley Martin Lieber", "New York", "30-06-2016"),
                new LegoSet("DC: Super Heroes", R.mipmap.legodc, "Lego set of the DC branch.", 2.0, "Malcom WN", "Burbank", "01-06-2016"),
                new LegoSet("Ninjago", R.mipmap.legoninjago, "Lego set of the Ninjago branch.", 0.4, "Bruce Lee", "Hong Kong", "04-05-2016"),
                new LegoSet("City", R.mipmap.legocity, "Lego set of the City branch.", 9.7, "Alice Wonder", "London", "30-02-2016"),
                new LegoSet("Technic", R.mipmap.legotechnic, "Lego set of the Technic branch.", 4.1, "Jason Statham", "Derbyshire", "10-04-2016"),
                new LegoSet("Mixed", R.mipmap.legomixed, "Mixed lego pieces.", 17.8, "The Riddler", "Gotham", "20-06-2016")


        };

        ListAdapter legoListAdapter = new CustomAdapter(getContext(), legoSetsObjects);

        ListView legoListView = (ListView) resultsView.findViewById(R.id.projectsListView);
        legoListView.setAdapter(legoListAdapter);
*/


        return resultsView;
    }


}
