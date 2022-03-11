package com.example.hackathonproject.ui;

import static com.example.hackathonproject.ui.HomePage.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hackathonproject.R;

public class BuyScreen extends AppCompatActivity {

    public static final double PRODUCT_NAME_SIZE = 0.08;
    public static final double HEADING_SIZE = 0.06;
    public static final double AISLE_SIZE = 0.05;


    private TextView heading;
    private TextView productName;
    private TextView aisle;
    private Button continueShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_screen);
        initActivity();
    }

    public void initActivity() {
        heading = findViewById(R.id.heading);
        productName = findViewById(R.id.productTitle);
        aisle = findViewById(R.id.aisle);

        heading.setText("The product you chose to buy is: ");
        productName.setText(store.getProductScores().get(0).toString());
        aisle.setText("You can find this product at Aisle " + store.getProductScores().get(0).getProduct().getAisle() + "!");


        heading.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * HEADING_SIZE));
        productName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * PRODUCT_NAME_SIZE));
        aisle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * AISLE_SIZE));

        continueShopping = findViewById(R.id.continueShopping);
        continueShopping.setTextSize(TypedValue.COMPLEX_UNIT_DIP, HomePage.buttonTextSize);
        continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomePage.class);
                startActivity(intent);
                finish();
            }
        });


    }
}