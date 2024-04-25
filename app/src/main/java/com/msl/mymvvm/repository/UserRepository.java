package com.msl.mymvvm.repository;

import androidx.lifecycle.LiveData;

import com.msl.mymvvm.model.User;

import java.util.List;

public interface UserRepository {
    interface Callback {
        void onSuccess(List<User> userList);
        void onError(String errorMessage);

        void onDataBase(LiveData<List<User>> userList);
    }

    void getUsers(Callback callback);
}
