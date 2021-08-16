package com.example.azkary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.azkary.database.cnt;

import java.util.ArrayList;

public class ElectronicRosary extends AppCompatActivity {
    int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_rosary);
        cnt cdp = new cnt(this);
        //fun to show max num of cnt
        showmax();
        //show cnt
        c = onstart();

        TextView btn = findViewById(R.id.rosary);
        btn.setText(c+"");
        findViewById(R.id.btnrosary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c++;
                TextView t = findViewById(R.id.rosary);
                t.setText(c + "");
            }
        });

        //click on reset btn
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdp.insertnewnum(c);
                c = 0;
                TextView t = findViewById(R.id.rosary);
                t.setText(c + "");

                //add cnt to database
                showmax();
            }
        });
    }

    public void showmax() {
        int m = 0;
        cnt cdp = new cnt(this);
        ArrayList<Integer> arr = cdp.Getdata();
        for (int i = 0; i < arr.size(); i++) {
            if (m < arr.get(i)) {
                m = arr.get(i);
            }
        }
        TextView textView = findViewById(R.id.maxcnt);
        textView.setText(m + "");
    }

    public int onstart(){
        int counter;
        cnt cdp = new cnt(this);
        ArrayList<Integer> arr = cdp.Getdata();
        if(arr.size()==0){
            counter = 0;
        }else{
            counter = arr.get(arr.size()-1);
        }
        return counter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cnt cdp = new cnt(this);
        cdp.insertnewnum(c);
    }
}