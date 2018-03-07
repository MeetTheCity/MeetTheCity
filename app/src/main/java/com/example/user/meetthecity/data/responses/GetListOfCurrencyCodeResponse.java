package com.example.user.meetthecity.data.responses;

/**
 * Created by Shahar.Wienrib on 05/03/2018.
 */

public class GetListOfCurrencyCodeResponse {
   GetCurrencyCodeResponse[] codeResponses ;

    public GetCurrencyCodeResponse[] getCodeResponses() {
        return codeResponses;
    }

    public void setCodeResponses(GetCurrencyCodeResponse[] codeResponses) {
        this.codeResponses = codeResponses;
    }
}
