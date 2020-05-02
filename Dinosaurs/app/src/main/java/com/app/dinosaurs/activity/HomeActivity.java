
package com.app.dinosaurs.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.app.dinosaurs.R;
import com.app.dinosaurs.model.Dinosaur;
import com.app.dinosaurs.adapter.DinosaursAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class HomeActivity extends AppCompatActivity {
    private ArrayList<Dinosaur> dinosaursArrayList;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        changeStatusBarColor();
        findViewById();
        getDinosaurList();
    }

    private void findViewById() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


    private void setAdpater() {
        DinosaursAdapter dinosaursAdapter = new DinosaursAdapter(dinosaursArrayList, HomeActivity.this);
        recyclerView.setAdapter(dinosaursAdapter);

    }


    private void getDinosaurList() {
        try {
            String jsonResponse = loadJSONFromAsset("dinosaur");
            Dinosaur[] optionListJsonResponse = parseJSON(jsonResponse);
            dinosaursArrayList = new ArrayList(Arrays.asList(optionListJsonResponse));
            if (dinosaursArrayList == null) {
                dinosaursArrayList = new ArrayList<>();
            }
            setAdpater();
        } catch (Exception e) {
            Log.e("TAG", "getQuestionList: " + e);
        }
    }


    public Dinosaur[] parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(response, Dinosaur[].class);

    }

    private String loadJSONFromAsset(String catName) {
        String json = null;
        try {
            InputStream is = getAssets().open(catName + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int read = is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlack));
        }
    }
}
