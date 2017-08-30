package com.srb9181.srbrxandroid.activities;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.srb9181.srbrxandroid.R;
import com.srb9181.srbrxandroid.adapter.CountryAdapter;
import com.srb9181.srbrxandroid.models.PopulationList;
import com.srb9181.srbrxandroid.network.RequestApi;
import com.srb9181.srbrxandroid.network.RetrofitClient;
import com.srb9181.srbrxandroid.pojo.Worldpopulation;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.srb9181.srbrxandroid.R.id.recycler;

public class MainActivity extends AppCompatActivity implements CountryAdapter.OnItemCLickListener{

    private RecyclerView recyclerView;
    private List<Worldpopulation> populationLists;
    private  RequestApi requestApi;
    private CompositeDisposable compositeDisposable;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populationLists = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setdata();


    }



    private void setdata() {
        requestApi = RetrofitClient.getClient().create(RequestApi.class);
        Observable<PopulationList> listObservable = requestApi.populationList();
        listObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,this::handleError);

    }

    private void handleError(Throwable throwable) {
    }

    private void handleResponse(PopulationList populationList) {
        populationLists = populationList.getWorldpopulation();
        countryAdapter = new CountryAdapter(populationLists,MainActivity.this);
        recyclerView.setAdapter(countryAdapter);
        countryAdapter.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(View imageView, int imageUri, String imageTransitionName) {

        Worldpopulation worldpopulation = populationLists.get(imageUri);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, imageView, getString(R.string.transition_string));
        Intent intent = new Intent(MainActivity.this,Flag.class);
        intent.putExtra("url",worldpopulation.getFlag());
        startActivity(intent,options.toBundle());
    }
}
