package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class ShowVisitRequestsActivity extends AppCompatActivity {
    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_visit_requests);
        RecyclerView recyclerView=findViewById(R.id.visitrequestsRecyclerView);
        SqliteDatabase sqliteDatabase=new SqliteDatabase(getApplicationContext());
        Intent intent=getIntent();
        ShowVisitRequestsActivity.username=intent.getStringExtra("username");
        List<VisitRequest> visitRequestList=sqliteDatabase.getVisitRequests(ShowVisitRequestsActivity.username);
        VisitRequestAdapter visitRequestAdapter=new VisitRequestAdapter(visitRequestList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(visitRequestAdapter);

    }
}