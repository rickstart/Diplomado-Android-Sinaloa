package com.mobintum.girlsappfragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class GirlDetailFragment extends Fragment {

    private static final String ARG_PARAM_POSITION = "param_position";
    private int mPosition;
    private ImageView imgPhoto;
    private TextView textName;
    private ImageButton btnLink;
    private ArrayList<Girl> girls;
    private Girl girl;


    public static GirlDetailFragment newInstance(int mPosition) {
        GirlDetailFragment fragment = new GirlDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_POSITION, mPosition);

        fragment.setArguments(args);
        return fragment;
    }

    public GirlDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mPosition = getArguments().getInt(ARG_PARAM_POSITION);

        }

        girls = Girl.getData(getActivity());
        girl = girls.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_girl_detail, container, false);

        imgPhoto = (ImageView) rootView.findViewById(R.id.imgPhoto);
        textName = (TextView) rootView.findViewById(R.id.textName);
        btnLink = (ImageButton) rootView.findViewById(R.id.btnLink);

        imgPhoto.setImageDrawable(girl.getPicture());
        textName.setText(girl.getName());

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(girl.getLink()));
                startActivity(intent);

            }
        });

        return rootView;
    }

}
