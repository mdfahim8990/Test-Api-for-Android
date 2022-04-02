package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;

     private  static  String TAG = "MainActivity";
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        button = findViewById(R.id.getbutton);
        textView = findViewById(R.id.TextView);

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

                        if (!response.isSuccessful()){
                            textView.setText(""+response.code());
                            return;
                        }

                        ArrayList<ModelClass.data> data = response.body().getData();

                        for (ModelClass.data data1 : data){
                            //Log.e(TAG, "Response Name :"+data1.getFirst_name());
                            String content="";
                            content += " " + data1.getFirst_name() + "\n";
                            content += " " + data1.getLast_name() + "\n";
                            content += " " + data1.getEmail() + "\n\n";
                            textView.append(content);


                        }


                    }

                    @Override
                    public void onFailure(Call<ModelClass> call, Throwable t) {

                        textView.setText(t.getMessage());

                    }
                });
            }
        });

    }
}