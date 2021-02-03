package com.example.asdsadsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asdsadsa.Retrofit.ApiClient;
import com.example.asdsadsa.Retrofit.ApiInterface;
import com.example.asdsadsa.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView search;
    TextView sicaklikText, hissedilenText, nemText;
    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        sicaklikText = findViewById(R.id.sicaklikText);
        hissedilenText = findViewById(R.id.hissedilenText);
        nemText = findViewById(R.id.nemText);
        textField = findViewById(R.id.textField);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getWeatherData(textField.getText().toString().trim());
            }
        });
    }


    private void getWeatherData(String name){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                sicaklikText.setText("Sıcaklık"+" "+ response.body().getMain().getTemp()+"C");
                hissedilenText.setText("Hissedilen"+" " + response.body().getMain().getFeels_like());
                nemText.setText("Nem"+" " + response.body().getMain().getHumidity());
            }

            @Override
            public void onFailure(Call<Example>call, Throwable t){

            }

        });

    }





}