package com.makancompany.project44;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;

    private LinearLayout btnCamera, btnMusic, btnGallery, btnContact, btnBasket, btnReport, btnLocation, btnCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewList = findViewById(R.id.view);
        btnCamera = findViewById(R.id.btnCamera);
        btnMusic = findViewById(R.id.btnMusic);
        btnGallery = findViewById(R.id.btnGallery);
        btnContact = findViewById(R.id.btnContact);
        btnBasket = findViewById(R.id.btnBasket);
        btnReport = findViewById(R.id.btnReport);
        btnLocation = findViewById(R.id.btnLocation);
        btnCalendar = findViewById(R.id.btnCalendar);

        Context ctx = getApplicationContext();

        btnCamera.setOnClickListener(View -> {
            startNewActivity(getApplicationContext(), "com.android.camera");
        });

        btnMusic.setOnClickListener(View -> {
            startNewActivity(getApplicationContext(), "com.spotify.music");
        });

        btnGallery.setOnClickListener(View -> {
            startNewActivity(getApplicationContext(), "com.google.android.apps.photos");
        });

        btnContact.setOnClickListener(View -> {
            startNewActivity(getApplicationContext(), "com.android.contacts");
        });

        btnBasket.setOnClickListener(View -> {
            startNewActivity(getApplicationContext(), "com.shopee.id");
        });

        btnReport.setOnClickListener(View -> {
            startNewActivity(getApplicationContext(), "com.android.stk");
        });

        btnLocation.setOnClickListener(View -> {
            startNewActivity(getApplicationContext(), "com.google.android.apps.maps");
        });

        btnCalendar.setOnClickListener(View -> {
            startNewActivity(getApplicationContext(), "com.google.android.calendar");
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        ArrayList<ListDomain> news = new ArrayList<>();
        news.add(new ListDomain("Browsing Bruges in Belgium", "pic1"));
        news.add(new ListDomain("Covid-19 in the Airport", "pic2"));
        news.add(new ListDomain("US-China War", "pic3"));
        news.add(new ListDomain("Browsing Bruges in Belgium", "pic1"));

        adapter = new NewsAdapter(news);
        recyclerViewList.setAdapter(adapter);
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }



    public void startNewActivity(Context context, String packageName) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            if (isPackageInstalled(packageName, getPackageManager())) {
                System.out.println("App " + packageName + " Already installed");
            } else {
                // Bring user to the market or let them choose an app?
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + packageName));
                startActivity(intent);
            }
        }
    }
}