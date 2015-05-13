package com.mobintum.inceptionmap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailLocationFragment extends Fragment {

    private static final String ARG_PARAM_POSITION = "position";

    private int position;
    private GoogleMap gMap;
    private SupportMapFragment supportMapFragment;
    private ArrayList<City> cities;
    private City city;


    public static DetailLocationFragment newInstance(int position) {
        DetailLocationFragment fragment = new DetailLocationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_POSITION,position);

        fragment.setArguments(args);
        return fragment;
    }

    public DetailLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.position = getArguments().getInt(ARG_PARAM_POSITION);
            cities = City.getData();
            city = cities.get(position);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_detail_location, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();
        supportMapFragment = ((SupportMapFragment) fragmentManager.findFragmentById(R.id.map));
        gMap = supportMapFragment.getMap();
        setLocation();
        TextView textCity = (TextView) viewRoot.findViewById(R.id.textCity);
        textCity.setText(city.getNameCity());

        ImageView imgPicture = (ImageView) viewRoot.findViewById(R.id.imgPhoto);
        Picasso.with(getActivity()).load(city.getPicture()).into(imgPicture);
        return viewRoot;
    }


    private void setLocation(){
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(city.getLat(),city.getLon()), 10));
        gMap.addMarker(new MarkerOptions().position(new LatLng(city.getLat(),city.getLon())).title(city.getNameCity()));
    }


}
