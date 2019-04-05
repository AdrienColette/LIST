package com.vogella.android.list.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vogella.android.list.R;
import com.vogella.android.list.activities.OnBeerClick;
import com.vogella.android.list.model.Beer;

import java.util.List;

public class AdapterBeer extends RecyclerView.Adapter<AdapterBeer.ViewHolder>{

    private final OnBeerClick click;
    private List<Beer> beerList;
    private Context context;


    public AdapterBeer(List<Beer> dataBase, Context context, OnBeerClick click)
    {
        beerList=dataBase;
        this.context=context;
        this.click=click;
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameBeer;
        public ImageView pictureBeer;
        public View layout;


        public ViewHolder(View vu)
        {
            super(vu);
            layout = vu;
            nameBeer = (TextView) vu.findViewById(R.id.beer_name);
            pictureBeer=(ImageView) vu.findViewById(R.id.beer_picture);
        }
    }

    @Override
    public AdapterBeer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vu=inflater.inflate(R.layout.anime_row_item,parent,false);
        ViewHolder vuH = new ViewHolder(vu);
        return vuH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Beer beerCurrent = beerList.get(position);
        final String name=beerCurrent.getName();

        holder.nameBeer.setText(name);
        Picasso.with(context)
                .load(beerCurrent.getImage_url())
                .into(holder.pictureBeer);

        holder.itemView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onBeerClick(beerCurrent);
            }
        }));
    }

    @Override
    public int getItemCount()
    {
        return beerList.size();
    }
}
