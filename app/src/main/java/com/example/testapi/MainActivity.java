package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

     private  static  String TAG = "MainActivity";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        button = findViewById(R.id.getbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<ModelClass>call = methods.getAllData();

                call.enqueue(new Callback<ModelClass>() {
                    @Override
                    public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                        Log.e(TAG, "Response Code :"+response.code());
                       /* ArrayList<ModelClass.data>data = response.body().getData;*/
                        ArrayList<ModelClass.data> data = response.body().getData();

                        for (ModelClass.data data1 : data){
                            Log.e(TAG, "Response Code :"+data1.getFirst_name());
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelClass> call, Throwable t) {

                    }
                });
            }
        });
    }
}