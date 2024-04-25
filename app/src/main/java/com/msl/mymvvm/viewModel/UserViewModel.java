package com.msl.mymvvm.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.msl.mymvvm.model.User;
import com.msl.mymvvm.repository.UserRepository;
import com.msl.mymvvm.repository.UserRepositoryImpl;

import java.util.List;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    private MutableLiveData<List<User>> users = new MutableLiveData<>();

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public UserViewModel(Context context) {
        userRepository = new UserRepositoryImpl(context);
        fetchUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    private void fetchUsers() {
        isLoading.setValue(true);
        userRepository.getUsers(new UserRepository.Callback() {
            @Override
            public void onSuccess(List<User> userList) {
                users.setValue(userList);
                isLoading.setValue(false);
            }

            @Override
            public void onError(String errorMessage) {
                // Handle error
                isLoading.setValue(false);
            }
            @Override
            public void onDataBase(LiveData<List<User>> userList) {
                // Handle error
                userList.observeForever(usersList -> {
                    // Handle error or other logic
                    users.setValue(usersList);
                });
                isLoading.setValue(false);
            }
        });
    }
}