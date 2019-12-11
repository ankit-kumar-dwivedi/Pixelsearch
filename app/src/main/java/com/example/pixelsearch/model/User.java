package com.example.pixelsearch.model;

import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;

public class User extends BaseObservable{
    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }
    public User(){

    }

    /*Validating email*/
    public boolean isValidEmail()
    {
        if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return true;
        }

        return false;
    }

    /*Validating password*/
    public boolean isValidPassword()
    {
        if(this.password != null && this.password.length() >= 5)
        {
            return true;
        }

        return false;
    }
}
