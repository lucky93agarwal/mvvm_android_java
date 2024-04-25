package com.msl.mymvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.msl.mymvvm.adapter.UserAdapter;
import com.msl.mymvvm.model.User;
import com.msl.mymvvm.repository.UserRepository;
import com.msl.mymvvm.repository.UserRepositoryImpl;
import com.msl.mymvvm.utils.Utilities;
import com.msl.mymvvm.viewModel.UserViewModel;
import com.msl.mymvvm.viewModel.UserViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ProgressBar progressBar;
    private View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();



        if (Utilities.isNetworkAvailable(this)){
            userViewModel.getUsers().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    userAdapter.setUsers(users);
                }
            });
            userViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isLoading) {
                    progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                }
            });
        }
        else{
            progressBar.setVisibility(View.GONE);
            Utilities.showSnackBar(rootView,getString(R.string.no_internet));
        }




    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter();
        recyclerView.setAdapter(userAdapter);
        rootView = findViewById(android.R.id.content);


        // repository
        UserRepository userRepository = new UserRepositoryImpl(this);
        userViewModel = new ViewModelProvider(this, new UserViewModelFactory(userRepository,this)).get(UserViewModel.class);
    }
}