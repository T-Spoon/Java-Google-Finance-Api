package com.tspoon.googlefinance.utils;

import retrofit.RequestInterceptor;

public class AuthInterceptor implements RequestInterceptor {

    private final String mQuandlToken;

    public AuthInterceptor(String quandlToken) {
        mQuandlToken = quandlToken;
    }

    @Override
    public void intercept(RequestFacade request) {
        if (mQuandlToken != null && mQuandlToken.length() > 0) {
            request.addQueryParam("auth_token", mQuandlToken);
        }
    }
}