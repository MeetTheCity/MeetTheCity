package com.example.user.meetthecity.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.user.meetthecity.R;
import com.example.user.meetthecity.data.responses.GetConvertedCurrencyResponse;
import com.example.user.meetthecity.data.responses.GetCurrencyCodeResponse;
import com.example.user.meetthecity.data.rest.RetrofitClient;
import com.example.user.meetthecity.data.rest.requests.CurrencyCodeRequest;
import com.example.user.meetthecity.data.rest.requests.CurrencyRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_COIN = "EUR";

    private CurrencyCodeRequest getCurrencyCodeRequest;
    private CurrencyRequest getCurrencyRequest;
    private TextView baseCoin;
    private TextView convertedCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getCurrencyCodeRequest = RetrofitClient.getClient(CurrencyCodeRequest.BASE_URL).create(CurrencyCodeRequest.class);
        getCurrencyCode();

        baseCoin = findViewById(R.id.base_coin);
        convertedCoin = findViewById(R.id.converted_coin);

    }

    private void getCurrencyCode() {
        String countryCode = this.getResources().getConfiguration().locale.getCountry();
        getCurrencyCodeRequest.getResponse(countryCode).enqueue(new Callback<List<GetCurrencyCodeResponse>>() {
            @Override
            public void onResponse(Call<List<GetCurrencyCodeResponse>> call, Response<List<GetCurrencyCodeResponse>> response) {
                sendCurrencyRequest(response);
            }

            @Override
            public void onFailure(Call<List<GetCurrencyCodeResponse>> call, Throwable t) {
                Log.e(MainActivity.class.getName(), t.getMessage());
            }
        });
    }

    private void sendCurrencyRequest(Response<List<GetCurrencyCodeResponse>> response) {
        getCurrencyRequest = RetrofitClient.getClient(CurrencyRequest.BASE_URL).create(CurrencyRequest.class);
        getConvertedCurrency(response.body().get(0).getCurrencies().get(0));
    }

    public void getConvertedCurrency(final GetCurrencyCodeResponse.CurrenciesBean currency) {
        getCurrencyRequest.getResponse(BASE_COIN).enqueue(new Callback<GetConvertedCurrencyResponse>() {
            @Override
            public void onResponse(Call<GetConvertedCurrencyResponse> call, Response<GetConvertedCurrencyResponse> response) {

                if (response.isSuccessful()) {
                    baseCoin.setText("1" + currency.getCode());
                    convertedCoin.setText(String.valueOf(response.body().getRates().getAUD()) + "AUD");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<GetConvertedCurrencyResponse> call, Throwable t) {
//                showErrorMessage();
                Log.d("MainActivity", "error loading from API");

            }
        });
    }
}