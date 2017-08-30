package com.srb9181.srbrxandroid.network;

import com.srb9181.srbrxandroid.models.PopulationList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Rudra on 8/30/2017.
 */

public interface RequestApi {

    @GET("jsonparsetutorial.txt")
    Observable<PopulationList> populationList();
}
