package com.mobintum.musicplayer;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListSongFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListSongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListSongFragment extends Fragment {


    private static final String ARG_PARAM_ARRAY = "paramArray";
    private ArrayList<Song> mArray;
    private ListView listSongsFragment;
    private ListSongAdapter adapter;


    private OnFragmentInteractionListener mListener;

    public static ListSongFragment newInstance(ArrayList<Song> mArray) {
        ListSongFragment fragment = new ListSongFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_ARRAY, mArray);

        fragment.setArguments(args);
        return fragment;
    }

    public ListSongFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mArray = (ArrayList<Song>) getArguments().getSerializable(ARG_PARAM_ARRAY);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_list_song, container, false);
        listSongsFragment = (ListView) viewRoot.findViewById(R.id.listSongsFragment);
        ArrayList<Song> songs = Song.getSongs(getActivity());
        adapter = new ListSongAdapter(getActivity(),R.layout.item_list_song,songs);

        listSongsFragment.setAdapter(adapter);
        listSongsFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(mListener != null){
                    mListener.onSongSelected(position);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        public void onSongSelected(int position);

    }

}
