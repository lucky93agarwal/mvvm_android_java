package com.msl.mymvvm.api;

import com.msl.mymvvm.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("comments")
    Call<List<User>> getUsers();
}
