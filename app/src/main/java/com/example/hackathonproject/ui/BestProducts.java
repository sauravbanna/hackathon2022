package com.example.hackathonproject.ui;

import static com.example.hackathonproject.ui.HomePage.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hackathonproject.R;

public class BestProducts extends ProductInfoDisplay {

    public static final double CHOSEN_PRODUCTS_HEADING_SIZE = 0.08;
    public static final double CHOSEN_PRODUCT_SIZE = 0.05;

    private TextView heading;
    private TextView product1;
    private TextView product2;
    private TextView product3;
    private Button info1;
    private Button info2;
    private Button info3;
    public static int headingSize;
    public static int productSize;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_products);
        initActivity();
    }

    public void initActivity() {
        initImageViews();
        headingSize = (int)(HomePage.height * CHOSEN_PRODUCTS_HEADING_SIZE);
        productSize = (int)(HomePage.height * CHOSEN_PRODUCT_SIZE);

        heading = findViewById(R.id.chosenProductHeading);
        heading.setTextSize(TypedValue.COMPLEX_UNIT_DIP, headingSize);
        product1 = findViewById(R.id.chosenProduct1);
        product2 = findViewById(R.id.chosenProduct2);
        product3 = findViewById(R.id.chosenProduct3);
        product1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, productSize);
        product2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, productSize);
        product3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, productSize);
        info1 = findViewById(R.id.chosenMoreInfo1);
        info2 = findViewById(R.id.chosenMoreInfo2);
        info3 = findViewById(R.id.chosenMoreInfo3);

        refreshChosenProducts();

        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRatings(store.getProductScores().get(0).getProduct());

            }
        });

        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRatings(store.getProductScores().get(1).getProduct());
            }
        });

        info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRatings(store.getProductScores().get(2).getProduct());
            }
        });

        product1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, productSize);
        product2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, productSize);
        product3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, productSize);


    }

    public void refreshChosenProducts() {
        product1.setText(store.getProductScores().get(0).toString());
        product2.setText(store.getProductScores().get(1).toString());
        product3.setText(store.getProductScores().get(2).toString());
    }


}