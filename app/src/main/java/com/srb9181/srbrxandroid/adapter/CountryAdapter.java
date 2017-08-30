package com.srb9181.srbrxandroid.adapter;

import android.content.Context;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.srb9181.srbrxandroid.R;
import com.srb9181.srbrxandroid.pojo.Worldpopulation;

import java.util.List;

/**
 * Created by Rudra on 8/30/2017.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.PopulationView>{

    List<Worldpopulation> worldpopulations;
    Context context;
    private OnItemCLickListener mItemClickListener;


    public interface OnItemCLickListener {
        void onItemClick(View imageView, int imageUri, String imageTransitionName);
    }

    public void setOnItemClickListener(final OnItemCLickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    public CountryAdapter(List<Worldpopulation> worldpopulations, Context context) {
        this.worldpopulations = worldpopulations;
        this.context = context;
    }

    @Override
    public PopulationView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopulationView(LayoutInflater.from(parent.getContext()).inflate(R.layout.population_item,parent,false));
    }

    @Override
    public void onBindViewHolder(PopulationView holder, int position) {


        Worldpopulation population = worldpopulations.get(position);
        holder.name.setText(population.getCountry());
        holder.population.setText("Population: "+population.getPopulation());
        holder.rank.setText("Rank: "+String.valueOf(population.getRank()));
        Glide.with(context).load(population.getFlag()).into(holder.flag);
        holder.flag.setTransitionName(getImageTransitionName(holder.flag.getContext(),position));

    }

    @Override
    public int getItemCount() {
        return worldpopulations.size();
    }



    public String getImageTransitionName(Context context, int position) {
        return context.getString(R.string.transition_string) + position;
    }

    class PopulationView extends RecyclerView.ViewHolder{

        ImageView flag;
        TextView name, population, rank;

        public PopulationView(View itemView) {
            super(itemView);
            flag = (ImageView) itemView.findViewById(R.id.thumbnail);
            name = (TextView) itemView.findViewById(R.id.title);
            population = (TextView) itemView.findViewById(R.id.count);
            rank = (TextView) itemView.findViewById(R.id.rank);



            flag.setOnClickListener(v -> {
                if (mItemClickListener != null) {

                    mItemClickListener.onItemClick(
                            flag,
                            getAdapterPosition(),
                            getImageTransitionName(flag.getContext(), getLayoutPosition())
                    );
                }
            });
        }
    }
}
