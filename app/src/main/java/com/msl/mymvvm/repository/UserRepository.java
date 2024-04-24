package com.msl.mymvvm.repository;

import com.msl.mymvvm.model.User;

import java.util.List;

public interface UserRepository {
    interface Callback {
        void onSuccess(List<User> userList);
        void onError(String errorMessage);
    }

    void getUsers(Callback callback);
}
