package com.example.pixelsearch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;

import com.example.pixelsearch.R;
import com.example.pixelsearch.databinding.ActivityLoginBinding;
import com.example.pixelsearch.model.User;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding activityLoginBinding;
    private String username ="abc@example.com";
    private String pass ="qwerty123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Create instance for data binding auto generated class file
         */
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        activityLoginBinding.loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validate())
                {
                    startActivity(new Intent(LoginActivity.this,StaggeredImagesAcitivity.class));
                }
            }
        });
    }
    public boolean validate()
    {
        String email = activityLoginBinding.userText.getText().toString();
        String password = activityLoginBinding.passText.getText().toString();
        if (email.isEmpty() || !email.equals(username)) {
            activityLoginBinding.userText.setError("Invalid Username");
            return false;
        }
        if (password.isEmpty() || !password.equals(pass)) {
            activityLoginBinding.passText.setError("Invalid Password");
            return false;
        }
        return true;
    }
}
