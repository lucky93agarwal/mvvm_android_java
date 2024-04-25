package com.msl.mymvvm.viewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.msl.mymvvm.repository.UserRepository;

public class UserViewModelFactory implements ViewModelProvider.Factory {
    private UserRepository userRepository;
    private Context context;

    public UserViewModelFactory(UserRepository userRepository, Context mContext) {
        this.userRepository = userRepository;
        this.context = mContext;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(/*userRepository*/context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
