package com.example.loginform;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton buttonDrawerToggle;

    private TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        View headerView = navigationView.getHeaderView(0);
        textViewTitle = headerView.findViewById(R.id.textViewTitle);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        textViewTitle.setText(email);


        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        // Set up the navigation item selection listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navCreate) {
                    Intent createIntent = new Intent(MainActivity2.this, CreateActivity.class);
                    startActivity(createIntent);
                }
                if ( id == R.id.navPCL){
                    Intent createIntent = new Intent(MainActivity2.this, PclActivity.class );
                    startActivity(createIntent);
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
