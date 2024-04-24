package com.msl.mymvvm.repository;

import com.msl.mymvvm.api.ApiService;
import com.msl.mymvvm.api.RetrofitApiClient;
import com.msl.mymvvm.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {
    private ApiService apiService;

    public UserRepositoryImpl() {
        apiService = RetrofitApiClient.getClient().create(ApiService.class);
    }

    @Override
    public void getUsers(final Callback callback) {
        Call<List<User>> call = apiService.getUsers();
        call.enqueue(new retrofit2.Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to fetch users");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
