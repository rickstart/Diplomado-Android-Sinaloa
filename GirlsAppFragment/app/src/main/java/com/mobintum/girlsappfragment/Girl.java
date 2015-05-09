package com.mobintum.girlsappfragment;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by Rick on 09/05/15.
 */
public class Girl {

    private String name;
    private Drawable picture;
    private String link;

    public Girl(String name, Drawable picture, String link) {
        this.name = name;
        this.picture = picture;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getPicture() {
        return picture;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public static ArrayList<Girl> getData(Context context){
        ArrayList<Girl> girls = new ArrayList<Girl>();

        girls.add(new Girl("Emma Watson", context.getResources().getDrawable(R.mipmap.emma_watson), "https://www.facebook.com/emmawatson"));
        girls.add(new Girl("Alexis Texas", context.getResources().getDrawable(R.mipmap.alexis_texas), "http://teamtexass.com/"));
        girls.add(new Girl("Jennifer Lawrence", context.getResources().getDrawable(R.mipmap.jennifer_lawrence), "https://www.facebook.com/JenniferLawrence/timeline"));
        girls.add(new Girl("Jennifer Love Hewitt", context.getResources().getDrawable(R.mipmap.jennifer_love_hewitt), "https://www.facebook.com/JLHOfficial?fref=ts"));
        girls.add(new Girl("Lisa Edelstein", context.getResources().getDrawable(R.mipmap.cuddy), "https://www.facebook.com/LisaEdelstein?fref=ts"));

        return girls;
    }

    public static ArrayList<String> getDataString(Context context){
        ArrayList<String> girlsNames = new ArrayList<String>();
        ArrayList<Girl> girls = getData(context);

        for (int i=0; i<girls.size(); i++){

            girlsNames.add(girls.get(i).getName());
        }

        return girlsNames;
    }

}
