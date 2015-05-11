package com.mobintum.movieexample.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobintum.movieexample.Models.Movie;
import com.mobintum.movieexample.R;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rick on 11/05/15.
 */
public class ListMovieAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private ArrayList<Movie> movies;
    ArrayList<Bitmap> posters ;

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
        ViewHolder holder = new ViewHolder();

        holder.imgThumb = (ImageView) viewRow.findViewById(R.id.imgThumb);
        holder.textTitle = (TextView) viewRow.findViewById(R.id.textTitle);
        holder.textYear = (TextView) viewRow.findViewById(R.id.textYear);
        Movie movie = movies.get(position);

        holder.textTitle.setText(movie.getTitle());
        holder.textYear.setText(""+movie.getYear());

        if(posters==null)
            new PostersAsyncTask().execute(movies);
        else{
            holder.imgThumb.setImageBitmap(posters.get(position));
        }

       /* try{
            if(posters.get(position)!=null) {
                holder.imgThumb.setImageBitmap(posters.get(position));
            }
        }catch (Exception e) {

                new DownloadAsyncTask(position).execute(holder);

        }
*/
        return viewRow;
    }


    private class ViewHolder{

        ImageView imgThumb;
        TextView textTitle;
        TextView textYear;;
        Bitmap bitmap;
    }

    private class DownloadAsyncTask extends AsyncTask<ViewHolder, Void, ViewHolder> {
        private int position;
        public DownloadAsyncTask(int position) {
            this.position= position;
        }
        @Override
        protected ViewHolder doInBackground(ViewHolder... params) {

            ViewHolder viewHolder = params[0];
            try {
                URL imageURL = new URL(movies.get(position).getUrlThumb());
                viewHolder.bitmap = BitmapFactory.decodeStream(imageURL.openStream());
            } catch (IOException e) {

                viewHolder.bitmap = null;
            }
            return viewHolder;
        }
        @Override
        protected void onPostExecute(ViewHolder result) {
            if (result.bitmap != null ) {
                posters.add(position,result.bitmap);
                //result.imgThumb.setImageBitmap(result.bitmap);
            }
        }


    }

    private class PostersAsyncTask extends AsyncTask<ArrayList<Movie>, Void, ArrayList<Bitmap>> {


        @Override
        protected ArrayList<Bitmap> doInBackground(ArrayList<Movie>... params) {

            ArrayList<Movie> movies = params[0];
            ArrayList<Bitmap> postersAsync = new ArrayList<Bitmap>();

            try {
                for (int i=0; i<movies.size();i++){

                    URL imageURL = new URL(movies.get(i).getUrlThumb());
                    Bitmap localBitmap = BitmapFactory.decodeStream(imageURL.openStream());
                    postersAsync.add(localBitmap);

                }

            } catch (IOException e) {

                postersAsync = null;
            }
            return postersAsync;
        }
        @Override
        protected void onPostExecute(ArrayList<Bitmap> postersAsync) {

            if(postersAsync!=null){

                posters = postersAsync;
            }
        }

    }
}
