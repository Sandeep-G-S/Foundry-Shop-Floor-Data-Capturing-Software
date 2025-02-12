package com.example.loginform;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewLoginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        sessionManager = new SessionManager(this);

        // Initialize UI components
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        Button buttonLogin = findViewById(R.id.login_button);
        textViewLoginStatus = findViewById(R.id.text_view_login_status);

        // Set OnClickListener for login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validate inputs (optional)
                if (email.isEmpty() || password.isEmpty()) {
                    textViewLoginStatus.setText("Please enter email and password.");
                    return;
                }

                // Perform login with API
                login(email, password);
            }
        });
    }

    private void login(String email, String password) {
        Credentials credentials = new Credentials(email, password);

        ApiService apiService = RetrofitClient.getApiService();
        Call<AuthResponse> call = apiService.login(credentials);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    sessionManager.saveToken(response.body().getToken());
                    Log.d("MainActivity", "Token: " + token);
                    textViewLoginStatus.setText("Login successful");

                    // Example: Proceed to next activity (MainActivity2)
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    finish();
                    sessionManager.saveToken(token);
                    finish(); // Optionally finish current activity
                } else {
                    textViewLoginStatus.setText("Invalid credentials");
                    Log.e("MainActivity", "Login failed");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                textViewLoginStatus.setText("Login failed: " + t.getMessage());
                Log.e("MainActivity", "Login failed: " + t.getMessage(), t);
            }
        });
    }
}
