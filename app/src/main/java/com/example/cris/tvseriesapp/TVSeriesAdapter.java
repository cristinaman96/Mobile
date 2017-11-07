package com.example.cris.tvseriesapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cris.tvseriesapp.model.TVSerie;

import java.util.List;

/**
 * Created by Cris on 11/6/2017.
 */

public class TVSeriesAdapter extends ArrayAdapter<TVSerie> {

    private Activity activity;
    private List<TVSerie> tvSeries;
    private static LayoutInflater inflater = null;

    public TVSeriesAdapter(Activity activity, int resource, List<TVSerie> tvSerieList){
        super(activity, resource, tvSerieList);
        try {
            this.activity = activity;
            this.tvSeries = tvSerieList;

            inflater = activity.getLayoutInflater();
        }catch (Exception e){}
    }

    @Override
    public int getCount(){
        return tvSeries.size();
    }

    public TVSerie getItem(TVSerie position){
        return position;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public static class ViewHolder{
        public TextView display_name;
        public ImageView display_image;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        final ViewHolder holder;

        try{
            if (convertView == null){
                view = inflater.inflate(R.layout.list_item, null);

                holder = new ViewHolder();

                holder.display_name = (TextView) view.findViewById(R.id.tvseriestitle);
                holder.display_image = (ImageView) view.findViewById(R.id.tvseriesimage);

                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }

            holder.display_name.setText(tvSeries.get(position).getTitle());
            holder.display_image.setImageResource(tvSeries.get(position).getImage());
        }catch (Exception e){}

        return view;
    }


}
