package com.mobintum.musicplayer.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobintum.musicplayer.R;
import com.mobintum.musicplayer.models.SongOld;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayerFragment extends Fragment implements View.OnClickListener, Runnable {

    private static final String ARG_PARAM_POSITION = "paramPosition";

    private int mPosition;
    private ImageButton btnPlayF, btnForwardF, btnBackwardF;
    private ImageView imgThumbDetailF;
    private TextView textDetailSongF, textDetailArtistF, textDetailAlbumF, textDetailTimeF;
    private ProgressBar progressBarF;
    private MediaPlayer mPlayer;
    private SongOld song;
    private int flag=0;
    private Thread thread;
    private ArrayList<SongOld> songs;

    public static PlayerFragment newInstance(int mPosition) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_POSITION, mPosition);

        fragment.setArguments(args);
        return fragment;
    }

    public PlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_PARAM_POSITION);
            songs = SongOld.getSongs(getActivity());
            song = songs.get(mPosition);

        }
        thread= new Thread(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_player, container, false);

        btnPlayF = (ImageButton) viewRoot.findViewById(R.id.btnPlayF);
        btnBackwardF = (ImageButton) viewRoot.findViewById(R.id.btnBackwardF);
        btnForwardF = (ImageButton) viewRoot.findViewById(R.id.btnForwardF);

        imgThumbDetailF = (ImageView) viewRoot.findViewById(R.id.imgThumbDetailF);
        textDetailSongF = (TextView) viewRoot.findViewById(R.id.textDetailSongF);
        textDetailArtistF = (TextView) viewRoot.findViewById(R.id.textDetailArtistF);
        textDetailAlbumF = (TextView) viewRoot.findViewById(R.id.textDetailAlbumF);
        textDetailTimeF = (TextView) viewRoot.findViewById(R.id.textDetailTimeF);
        progressBarF = (ProgressBar) viewRoot.findViewById(R.id.progressBarF);

        btnPlayF.setOnClickListener(this);
        btnBackwardF.setOnClickListener(this);
        btnForwardF.setOnClickListener(this);

        loadData(song);
        return viewRoot;
    }

    private void loadData(SongOld song){
        //imgThumbDetailF.setImageDrawable(song.getAlbumImage());
        Picasso.with(getActivity()).load(song.getAlbumImage()).into(imgThumbDetailF);
        textDetailSongF.setText(song.getTitle());
        textDetailArtistF.setText(song.getArtist());
        textDetailAlbumF.setText(song.getAlbum());
        mPlayer = MediaPlayer.create(getActivity(),  getResources().getIdentifier("raw/"+song.getUrlSong(),
                "raw", getActivity().getPackageName()));


        int seconds = (int) (mPlayer.getDuration() / 1000) % 60 ;
        int minutes = (int) ((mPlayer.getDuration() / (1000*60)) % 60);
        int hours   = (int) ((mPlayer.getDuration() / (1000*60*60)) % 24);
        if(hours>0) {
            textDetailTimeF.setText("" + hours + ":" + minutes + ":" + seconds);
        }else{
            textDetailTimeF.setText(""+minutes + ":" + seconds);
        }
        progressBarF.setVisibility(ProgressBar.VISIBLE);
        progressBarF.setProgress(0);
        progressBarF.setMax(mPlayer.getDuration());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnPlayF:

                if(flag==0){

                    mPlayer.start();
                    flag=1;
                    btnPlayF.setImageResource(R.mipmap.btn_pause);


                    if(thread.getState()!= Thread.State.TIMED_WAITING)
                        thread.start();

                }else{
                    mPlayer.pause();
                    btnPlayF.setImageResource(R.mipmap.btn_play);
                    flag=0;

                }

                break;

            case R.id.btnBackwardF:
                mPlayer.stop();

                mPosition = ((mPosition-1) >= 0)?mPosition -1: mPosition;

                loadData(songs.get(mPosition));

                mPlayer.start();
                if(thread.getState()!= Thread.State.TIMED_WAITING)
                    thread.start();

                break;
            case R.id.btnForwardF:
                mPlayer.stop();
                mPosition=(mPosition<songs.size()-1)?mPosition+1:mPosition;
                loadData(songs.get(mPosition));
                mPlayer.start();
                if(thread.getState()!= Thread.State.TIMED_WAITING)
                    thread.start();
                break;
        }
    }

    @Override
    public void run() {
        int currentPosition= 0;
        int total = mPlayer.getDuration();
        while (mPlayer!=null && currentPosition<total) {
            try {
                Thread.sleep(1000);
                currentPosition= mPlayer.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            progressBarF.setProgress(currentPosition);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        if(mPlayer!=null)
        mPlayer.stop();
    }
}
