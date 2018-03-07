package com.example.user.meetthecity.data.rest.requests;

import com.example.user.meetthecity.data.responses.GetCurrencyCodeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Shahar.Wienrib on 28/02/2018.
 */

public interface CurrencyCodeRequest {
        String BASE_URL = "https://restcountries.eu/rest/v2/";

    @GET("./alpha")
    Call<List<GetCurrencyCodeResponse>> getResponse(@Query("codes") String tags);
}
