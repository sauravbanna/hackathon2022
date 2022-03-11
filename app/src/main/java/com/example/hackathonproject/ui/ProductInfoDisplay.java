package com.example.hackathonproject.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hackathonproject.R;
import com.example.hackathonproject.model.Product;

public abstract class ProductInfoDisplay extends AppCompatActivity {

    public static final double LABEL_SIZE = 0.03;
    public static final double TEXT_SIZE = 0.05;

    protected TextView priceLabel;
    protected TextView batteryLabel;
    protected TextView processingLabel;
    protected TextView resolutionLabel;
    protected TextView gpuLabel;
    protected TextView ramLabel;
    protected TextView hardDriveLabel;
    protected ConstraintLayout productInfo;
    protected Button exit;
    protected Button buy;
    protected ImageView darkBackground;
    protected TextView descriptionHeading;
    protected TextView description;
    protected TextView productName;

    public static Product currentProduct;

    public void initImageViews() {
        descriptionHeading = findViewById(R.id.descriptionHeading);
        description = findViewById(R.id.description);
        productName = findViewById(R.id.productName);
        description.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * TEXT_SIZE));
        descriptionHeading.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * TEXT_SIZE));
        productName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * TEXT_SIZE));

        darkBackground = findViewById(R.id.darkBackground);
        priceLabel = findViewById(R.id.priceLabel);
        batteryLabel = findViewById(R.id.batteryLabel);
        processingLabel = findViewById(R.id.processingLabel);
        resolutionLabel = findViewById(R.id.resolutionLabel);
        gpuLabel = findViewById(R.id.gpuLabel);
        ramLabel = findViewById(R.id.ramLabel);
        hardDriveLabel = findViewById(R.id.hardDriveLabel);
        priceLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * LABEL_SIZE));
        batteryLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * LABEL_SIZE));
        resolutionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * LABEL_SIZE));
        hardDriveLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * LABEL_SIZE));
        ramLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * LABEL_SIZE));
        gpuLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * LABEL_SIZE));
        processingLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, (int)(HomePage.height * LABEL_SIZE));

        exit = findViewById(R.id.exitButton);
        buy = findViewById(R.id.buy);


        productInfo = findViewById(R.id.productInfo);
        exitInfo();
    }

    public void setRatings(Product p) {
        currentProduct = p;
        productName.setText(p.getName());
        description.setText(p.getDescription());
        priceLabel.setText("Price: " + String.valueOf(p.getScore("price")) + " / 10" );
        batteryLabel.setText("Battery: "+ String.valueOf(p.getScore("battery")) + " / 10" );
        processingLabel.setText("Processing: " + String.valueOf(p.getScore("processing power")) + " / 10" );
        resolutionLabel.setText("Resolution: " + String.valueOf(p.getScore("resolution")) + " / 10" );
        gpuLabel.setText("GPU: " + String.valueOf(p.getScore("gpu")) + " / 10" );
        ramLabel.setText("RAM: " + String.valueOf(p.getScore("ram")) + " / 10" );
        hardDriveLabel.setText("Hard Drive: " + String.valueOf(p.getScore("hard drive")) + " / 10" );

        fadeInfoIn();
    }

    public void fadeInfoIn() {
        productInfo.setVisibility(View.VISIBLE);
        darkBackground.setVisibility(View.VISIBLE);
        darkBackground.animate()
                .alpha(1f)
                .setDuration(RecommendProduct.FADE_LENGTH)
                .setListener(null);
        productInfo.animate()
                .alpha(1f)
                .setDuration(RecommendProduct.FADE_LENGTH)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                exitInfo();
                            }
                        });

                        buy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                buyProduct();
                            }
                        });
                    }
                });
    }

    public void exitInfo() {
        productInfo.setVisibility(View.GONE);
        productInfo.setAlpha(0f);
        darkBackground.setVisibility(View.GONE);
        darkBackground.setAlpha(0f);
    }

    public void buyProduct() {
        Intent intent = new Intent(this, BuyScreen.class);
        startActivity(intent);
        finish();
    }


}
