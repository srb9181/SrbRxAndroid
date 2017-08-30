package com.srb9181.srbrxandroid.network;

import com.srb9181.srbrxandroid.util.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rudra on 8/30/2017.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient(){

        if(retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
