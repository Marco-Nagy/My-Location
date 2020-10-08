package com.example.mylocation;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallableInt {
@GET("/json")
    Call<IpModel> getData();
}
