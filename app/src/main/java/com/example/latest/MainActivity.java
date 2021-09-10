package com.example.latest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView; //container來存放所有子view

    //butter knife


    @Override //複寫註解
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //呼叫母類別(AppCompatActivity)的onCreate function
        setContentView(R.layout.activity_main); //把activity_main這個layout設為MainActivity的畫面


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_competitions);
        //recyclerview_competitions是container來存放view


        new FirebaseDatabaseHelper().readCompetition(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsloaded(List<Competition> competitions, List<String> keys) {
                new RecyclerView_config().setConfig(mRecyclerView, MainActivity.this, competitions, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });



    }
}