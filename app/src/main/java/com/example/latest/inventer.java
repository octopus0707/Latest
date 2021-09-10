package com.example.latest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class inventer extends AppCompatActivity {

    private static final String  TAG = "inventActivity";
    String key; //位置
    TextView invent;
    private RecyclerView mRecyclerView; //container來存放所有子view

    private List<Join> join = new ArrayList<>();

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        getIncomingIntent();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("join_competition/"+key);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_join);

        reference.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot){

                join.clear();
                List<String> keys = new ArrayList<>();

                for(DataSnapshot child : dataSnapshot.getChildren()){

                    if(child.hasChild("user")){
                        keys.add(child.getKey()); //key(亂碼)
                        Join userr = child.getValue( Join.class );
                        join.add(userr);
//                        Toast.makeText(inventer.this, "text"+keys.get(0), Toast.LENGTH_SHORT).show();
                        new RecyclerView_config().setConfig2(mRecyclerView, inventer.this, join, keys);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getIncomingIntent(){
        Log.d(TAG,"getIncomingIntent:");
        if(getIntent().hasExtra("url")){
            Log.d(TAG,"Found");

            String url = getIntent().getStringExtra("url");
            setUrl(url);

        }
    }

    private void setUrl(String url){
        Log.d(TAG, "setUrl to widgets.");
        key = url;
    }
}
