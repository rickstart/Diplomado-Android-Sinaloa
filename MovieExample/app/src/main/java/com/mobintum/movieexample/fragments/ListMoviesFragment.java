package com.mobintum.movieexample.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mobintum.movieexample.Models.Movie;
import com.mobintum.movieexample.R;
import com.mobintum.movieexample.adapters.ListMovieAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListMoviesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListMoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListMoviesFragment extends Fragment {


    private static final String ARG_PARAM_QUERY = "paramQuery";
    private String paramQuery;
    private OnFragmentInteractionListener mListener;
    private ListView listMovies;
    private ListMovieAdapter adapter;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private final static String API_KEY = "35hg37n2zaybbwf7wncj9vgw";

    public static ListMoviesFragment newInstance(String paramQuery) {
        ListMoviesFragment fragment = new ListMoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_QUERY, paramQuery);
        fragment.setArguments(args);
        return fragment;
    }

    public ListMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.paramQuery = getArguments().getString(ARG_PARAM_QUERY);

            new RottenSearchTask().execute("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + paramQuery + "&page_limit=10");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_list_movies, container, false);
        listMovies = (ListView) viewRoot.findViewById(R.id.listMovies);
        adapter = new ListMovieAdapter(getActivity(),R.layout.item_list_movie,movies);
        listMovies.setAdapter(adapter);

        listMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mListener!=null){
                    mListener.onMovieSelected(position);
                }
            }
        });


        return viewRoot;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        public void onMovieSelected(int position);
    }

    private void refreshMoviesList(ArrayList<Movie> movies)
    {

        if(movies!=null) {

            if(movies.size()==0)
                Toast.makeText(getActivity(), "EMPTY", Toast.LENGTH_SHORT).show();
            else {
                Log.d("Test", "Movies: "+movies.size());

                adapter = new ListMovieAdapter(getActivity(),R.layout.item_list_movie,movies);
                listMovies.setAdapter(adapter);
            }
        }


    }

    private class RottenSearchTask extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;

            try{
                response = httpClient.execute(new HttpGet(params[0]));
                StatusLine statusLine = response.getStatusLine();

                if(statusLine.getStatusCode() == HttpStatus.SC_OK){

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    responseString = out.toString();

                    return responseString;
                }else{
                    response.getEntity().getContent().close();
                    throw  new IOException(statusLine.getReasonPhrase());

                }


            }catch (Exception e){
                e.printStackTrace();
                return null;
            }



        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if(response!=null){


                movies = Movie.parseJSON(response);
                Log.i("ASYNCTASK", "movies size="+movies.size()+" response="+response);
                refreshMoviesList(movies);

            }
        }
    }

}
