package com.mobintum.movieexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobintum.movieexample.Models.Movie;
import com.mobintum.movieexample.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 *
 * Created by Rick on 11/05/15.
 *
 */
public class ListMovieAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Movie> movies;
    private LayoutInflater mLayoutInflater;


    public ListMovieAdapter(Context context, int resource, ArrayList<Movie> movies) {
        super(context, resource, movies);
        this.context = context;
        this.movies = movies;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder;

        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.item_list_movie, parent, false);
            holder = new ViewHolder();
            holder.imgThumb = (ImageView) convertView.findViewById(R.id.imgThumb);
            holder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
            holder.textYear = (TextView) convertView.findViewById(R.id.textYear);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Movie movie = movies.get(position);
        holder.textTitle.setText(movie.getTitle());
        holder.textYear.setText(""+movie.getYear());
        Picasso.with(context).load(movie.getUrlThumb()).into(holder.imgThumb);

        return convertView;
    }


    static class ViewHolder{

        ImageView imgThumb;
        TextView textTitle;
        TextView textYear;

    }

}
