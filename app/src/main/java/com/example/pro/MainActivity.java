package com.example.pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    SqliteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new SqliteDatabase(getApplicationContext());
        context=getApplicationContext();

//        database.dropdatabase();


    }
    public void clickbutton(View view) {
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        Button type=( Button ) view;
        intent.putExtra("type",type.getText().toString());
        startActivity(intent);

    }
}