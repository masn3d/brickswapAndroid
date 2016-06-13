package com.example.silas.testbrickswap.brickswap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.silas.testbrickswap.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<LegoSet> {

    public CustomAdapter(Context context, ArrayList<LegoSet> legoSetsObjects) {
        super(context, R.layout.search_costom_row, legoSetsObjects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String imageUrl;
        ArrayList imageList;
        int defaultImage = R.mipmap.legomixed;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.search_costom_row, parent, false);

        LegoSet singleLegoSetsItem = getItem(position);

        imageList = singleLegoSetsItem.getImageList();

        ImageView listViewImage = (ImageView) customView.findViewById(R.id.listViewImage);
        TextView listViewTitle = (TextView) customView.findViewById(R.id.listViewTitle);
        TextView listViewProductName = (TextView) customView.findViewById(R.id.listViewProductName);
        TextView listViewPostDate = (TextView) customView.findViewById(R.id.listViewPostDate);
        TextView listViewPrice = (TextView) customView.findViewById(R.id.listViewPrice);

        listViewTitle.setText(singleLegoSetsItem.getTitle());
        listViewProductName.setText(singleLegoSetsItem.getProductName());
        listViewPostDate.setText(singleLegoSetsItem.getPostDate());
        listViewPrice.setText(singleLegoSetsItem.getPrice() + " kr.");

        //listViewImage.setImageResource(defaultImage);


        if(imageList == null) {

            imageUrl = "brickswap-msncphbusiness.rhcloud.com/postImages/575d6f9775baccb0344ba30c/postImg_1.jpg";
            Picasso.with(getContext()).load(imageUrl).into(listViewImage);

            //listViewImage.setImageDrawable(Drawable.createFromPath(imageUrl));
        }else{
            imageUrl = imageList.get(0).toString();
            Picasso.with(getContext()).load(imageUrl).into(listViewImage);
        }


        return customView;
    }
}

