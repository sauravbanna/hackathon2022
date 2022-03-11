package com.example.hackathonproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hackathonproject.R;

public class HomePage extends AppCompatActivity {

    // CONSTANTS
    public static final double HEADING_TEXT_SCALE = 0.1;
    public static final double BUTTON_TEXT_SCALE = 0.06;
    public static final double OWNER_LOGIN_TEXT_SCALE = 0.04;


    // FIELDS
    private Button suggestButton;
    private Button searchButton;
    private Button ownerLoginButton;
    private TextView welcomeView;
    private DisplayMetrics displayMetrics;
    public static int width;
    public static int height;
    public static int buttonTextSize;

    // METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialise();

    }

    public void initialise() {
        initDimensions();
        initImageViews();

    }

    public void initDimensions() {
        displayMetrics = this.getResources().getDisplayMetrics();
        height = pixelToDp(displayMetrics.heightPixels);
        width = pixelToDp(displayMetrics.widthPixels);
        buttonTextSize = (int)(height * OWNER_LOGIN_TEXT_SCALE);
    }

    public int pixelToDp(int px) {
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public void initImageViews() {
        suggestButton = findViewById(R.id.suggestButton);
        searchButton = findViewById(R.id.searchButton);
        ownerLoginButton = findViewById(R.id.ownerLoginButton);
        suggestButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, buttonTextSize);
        searchButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, buttonTextSize);
        ownerLoginButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, buttonTextSize);

        welcomeView = findViewById(R.id.welcomeText);
        welcomeView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(height * HEADING_TEXT_SCALE));

        suggestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSuggestPage();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchPage();
            }
        });
    }

    public void openSuggestPage() {
        Intent intent = new Intent(this, RecommendProduct.class);
        startActivity(intent);
        finish();
    }

    public void openSearchPage() {
        Intent intent = new Intent(this, SearchProduct.class);
        startActivity(intent);
        finish();
    }
}