package com.example.recyclerviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>
{
    private ArrayList<Country> countries ;
     private MyCountryListner listner;
    public CountryAdapter(ArrayList<Country> countries) {

        this.countries = countries;
    }

    public void setListner (MyCountryListner listner){
        this.listner = listner;
    }

    interface MyCountryListner{
        void onCountryClicked(int position,View view);
        void onCountryLongClicked (int position,View view);
        void onCountryDeleteClicked(int position,View view);
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_cell,parent,false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.nameTv.setText(country.getName());
        holder.populationTv.setText(country.getPopulation() + "");
        holder.flagTv.setImageResource(country.getFlagResId());
        holder.goodCb.setChecked(country.isGood());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public  class  CountryViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv;
        TextView populationTv;
        ImageView flagTv;
        CheckBox goodCb;
        ImageView deleteTv;

        public  CountryViewHolder( View itemView) {
            super(itemView);
             nameTv = itemView.findViewById(R.id.country_name);
             populationTv = itemView.findViewById(R.id.country_ppulation);
             flagTv = itemView.findViewById(R.id.country_flag);
             goodCb = itemView.findViewById(R.id.country_checkBox);
             deleteTv = itemView.findViewById(R.id.country_delete);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if (listner!=null ){
                         listner.onCountryClicked(getAdapterPosition(),view);
                     }
                 }
             });

             itemView.setOnLongClickListener(new View.OnLongClickListener() {
                 @Override
                 public boolean onLongClick(View view) {
                     if (listner!=null ){
                         listner.onCountryLongClicked(getAdapterPosition(),view);
                     }
                     return false;
                 }
             });

             deleteTv.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if (listner!=null ){
                         listner.onCountryDeleteClicked(getAdapterPosition(),view);
                     }
                 }
             });
        }
    }
}
