package com.mobintum.movieexample.fragments;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

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
    private ArrayList<Movie> movies = new ArrayList<>();
    private final static String API_KEY = "35hg37n2zaybbwf7wncj9vgw";
    private Menu optionMenu;

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
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            this.paramQuery = getArguments().getString(ARG_PARAM_QUERY);

            new RottenSearchTask().execute("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + paramQuery + "&page_limit=50");
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
                    Movie movie = movies.get(position);
                    Log.e("SIO", movie.getSynopsis());
                    mListener.onMovieSelected(movie);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);

        this.optionMenu = menu;
        menu.clear();
        inflater.inflate(R.menu.menu_list_fragment,menu);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getActivity().getComponentName());
        searchView.setSearchableInfo(searchableInfo);
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.e("QUERY", query);
                String querySpace = query.trim().replaceAll(" +","%20");
                new RottenSearchTask().execute("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q="+querySpace);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void setRefreshActionButtonState(boolean refresh){
        if(optionMenu!=null){
            MenuItem refreshItem = optionMenu.findItem(R.id.menuRefresh);
            if(refreshItem!=null){
                if(refresh){
                    refreshItem.setActionView(R.layout.inderterminate_progress);
                }else{
                    refreshItem.setActionView(null);
                }
            }

        }

    }
    public interface OnFragmentInteractionListener {

        public void onMovieSelected(Movie movie);
    }



    private class RottenSearchTask extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setRefreshActionButtonState(true);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response;
            String responseString;

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

            setRefreshActionButtonState(false);
            if(response!=null){


                movies = Movie.parseJSON(response);

                Log.e("SYN",movies.get(4).getSynopsis());
                adapter.clear();
                adapter.addAll(movies);
                adapter.notifyDataSetChanged();



            }
        }
    }

}
