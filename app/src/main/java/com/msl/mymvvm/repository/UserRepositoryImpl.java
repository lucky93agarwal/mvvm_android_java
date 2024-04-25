package com.msl.mymvvm.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.msl.mymvvm.api.ApiService;
import com.msl.mymvvm.api.RetrofitApiClient;
import com.msl.mymvvm.db.AppDatabase;
import com.msl.mymvvm.db.UserDao;
import com.msl.mymvvm.model.User;
import com.msl.mymvvm.utils.Logger;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {
    private UserDao userDao;
    private ApiService apiService;

    public UserRepositoryImpl(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        userDao = database.userDao();
        apiService = RetrofitApiClient.getClient().create(ApiService.class);
    }

    @Override
    public void getUsers(final Callback callback) {

        Executors.newSingleThreadExecutor().execute(() -> {
            Logger.d("Dataapi","Count = "+String.valueOf(userDao.getUserCount() ));
            if (userDao.getUserCount() == 0) {
                Call<List<User>> call = apiService.getUsers();
                call.enqueue(new retrofit2.Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccessful()) {
                            List<User> users = response.body();
                            new InsertUsersTask(userDao).execute(users.toArray(new User[0]));
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
            else {
                Logger.d("Dataapi","userDao.getAllUsers() = "+String.valueOf(userDao.getAllUsers()));

                LiveData<List<User>> usersLiveData = userDao.getAllUsers();


                // Pass the LiveData to the callback on the main thread
                new Handler(Looper.getMainLooper()).post(() -> {
                    callback.onDataBase(usersLiveData);
                });
//                LiveData<List<User>> usersLiveData = userDao.getAllUsers();
//                usersLiveData.observeForever(users -> {
//                    // Post the value to MutableLiveData on the main thread
//
//                });
//                callback.onDataBase(userDao.getAllUsers());
            }
        });


    }
//    @Override
//    public LiveData<List<User>> getUsers() {
//        refreshDataFromApi();
//        return userDao.getAllUsers();
//    }

//    private void refreshDataFromApi() {
//        // Check if data is available in Room Database
//        if (userDao.getUserCount() == 0) {
//            // Fetch data from API and save to Room Database
//            Call<List<User>> call = apiService.getUsers();
//
//            call.enqueue(new retrofit2.Callback<List<User>>() {
//                @Override
//                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                    if (response.isSuccessful()) {
//                        List<User> users = response.body();
//                        if (users != null && !users.isEmpty()) {
//                            Logger.d("api data check",users.toString());
//                         //   userDao.insertUsers(users.toArray(new User[0]));
//                        //   new InsertUsersTask(userDao).execute(users.toArray(new User[0]));
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<User>> call, Throwable t) {
//                    // Handle failure
//                }
//            });
//        }
//    }
//
    private static class InsertUsersTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        InsertUsersTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUsers(users);
            return null;
        }
    }
}