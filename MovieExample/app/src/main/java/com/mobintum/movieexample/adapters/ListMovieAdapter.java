package com.mobintum.movieexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mobintum.movieexample.Models.Movie;
import com.mobintum.movieexample.R;

import java.util.ArrayList;

/**
 * Created by Rick on 11/05/15.
 */
public class ListMovieAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private ArrayList<Movie> movies;

    public ListMovieAdapter(Context context, int resource, ArrayList<Movie> movies) {
        super(context, resource, movies);
        this.context = context;
        this.resource = resource;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = inflater.inflate(R.layout.item_list_movie,null);


        return viewRow;
    }
}
