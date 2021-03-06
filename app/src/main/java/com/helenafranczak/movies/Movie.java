package com.helenafranczak.movies;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by helenafranczak on 3/11/17.
 */
//public class Movie implements Comparable<Movie> {

public class Movie implements Parcelable{

    public String title;


    public  Double rating;


    public  String poster;

    public String date;

    public  String plot;


    public Movie(String movieTitle, Double movieRating, String moviePoster, String moviePlot, String movieDate) {
        title = movieTitle;
        rating = movieRating;
        this.poster = moviePoster;

        date = movieDate;
        plot = moviePlot;

    }

    protected Movie(Parcel in) {
        title = in.readString();

       rating = in.readDouble();

        poster = in.readString();
        plot = in.readString();
        date = in.readString();

    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
       return title;

    }

    public void setRating(Double rating){
        this.rating = rating;
    }
    public  Double getRating(){
        return rating;

    }

    public void setPoster(String poster){
        this.poster = poster;
    }
    public  String getPoster(){
        return poster;

    }

    public void setDate( String date){this.date=date;}
    public String getDate(){
        return date;
    }

    public void setPlot( String plot){this.plot = plot;}
    public String getPlot(){return plot;}



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {

        out.writeString(title);
        out.writeDouble(rating);
        out.writeString(poster);
        out.writeString(plot);
        out.writeString(date);

    }



}







