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
import com.example.hackathonproject.model.Product;
import com.example.hackathonproject.model.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.acl.Owner;
import java.util.HashMap;

public class HomePage extends AppCompatActivity {

    // CONSTANTS
    public static final double HEADING_TEXT_SCALE = 0.1;
    public static final double BUTTON_TEXT_SCALE = 0.06;
    public static final double OWNER_LOGIN_TEXT_SCALE = 0.04;
    public static final String SAVE_FILE = "products.json";


    // FIELDS
    private Button suggestButton;
    private TextView welcomeView;
    private DisplayMetrics displayMetrics;
    public static int width;
    public static int height;
    public static int buttonTextSize;
    public static int headingTextSize;
    public static Store store;

    // METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        store = new Store();
        initialise();

    }



    public void initialise() {
        initStore();
        initDimensions();
        initImageViews();

    }

    public void initStore() {
        String json = null;
        try {
            InputStream inputStream = this.getAssets().open(SAVE_FILE);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

            store.setProducts(jsonToProducts(new JSONObject(json.toString())));
        } catch (IOException ioe) {
            ioe.getStackTrace();
        } catch (JSONException je) {
            je.getStackTrace();
        }
    }

    private HashMap<Integer, Product> jsonToProducts(JSONObject json) {
        HashMap<Integer, Product> products = new HashMap<>();
        try {
            JSONArray productsJson = json.getJSONArray("products");
            for (int i = 0; i < productsJson.length(); i++) {
                JSONObject productJson = productsJson.getJSONObject(i);
                Product newProduct = new Product(productJson.getInt("id"),
                        productJson.getString("name"),
                        productJson.getString("description"),
                        productJson.getInt("aisle"));
                for (String s : Store.ATTRIBUTES) {
                    newProduct.addAttribute(s, productJson.getInt(s));
                }
                products.put(productJson.getInt("id"), newProduct);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return products;
    }

    public void initDimensions() {
        displayMetrics = this.getResources().getDisplayMetrics();
        height = pixelToDp(displayMetrics.heightPixels);
        width = pixelToDp(displayMetrics.widthPixels);
        buttonTextSize = (int)(height * OWNER_LOGIN_TEXT_SCALE);
        headingTextSize = (int)(height * HEADING_TEXT_SCALE);
    }

    public int pixelToDp(int px) {
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public void initImageViews() {
        suggestButton = findViewById(R.id.suggestButton);
        suggestButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, buttonTextSize);

        welcomeView = findViewById(R.id.welcomeText);
        welcomeView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, headingTextSize);

        suggestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSuggestPage();
            }
        });

    }

    public void openSuggestPage() {
        Intent intent = new Intent(this, RecommendProduct.class);
        startActivity(intent);
        finish();
    }


}