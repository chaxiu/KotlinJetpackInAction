package com.boycoder.kotlinjetpackinaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.boycoder.kotlinjetpackinaction.entity.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Main";
    public static final String EXTRA_PHOTO = "photo";

    StringRequest stringRequest;
    RequestQueue requestQueue;

    private ImageView image;
    private ImageView gif;
    private TextView username;
    private TextView company;
    private TextView website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        image = findViewById(R.id.image);
        gif = findViewById(R.id.gif);
        username = findViewById(R.id.username);
        company = findViewById(R.id.company);
        website = findViewById(R.id.website);

        display(User.CACHE_RESPONSE);
        requestOnlineInfo();
    }

    private void requestOnlineInfo() {
        requestQueue = Volley.newRequestQueue(this);
        String url ="https://api.github.com/users/JakeWharton";
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        display(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setTag(TAG);
        requestQueue.add(stringRequest);
    }

    private void display(@Nullable String response) {
        if (TextUtils.isEmpty(response)) { return; }

        Gson gson = new Gson();
        final User user = gson.fromJson(response, User.class);
        if (user != null){
            Glide.with(this).load("file:///android_asset/bless.gif").into(gif);
            Glide.with(this).load(user.getAvatar_url()).apply(RequestOptions.circleCropTransform()).into(image);
            this.username.setText(user.getName());
            this.company.setText(user.getCompany());
            this.website.setText(user.getBlog());

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoImagePreviewActivity(user);
                }
            });
        }
    }

    private void gotoImagePreviewActivity(User user) {
        Intent intent = new Intent(this, ImagePreviewActivity.class);
        intent.putExtra(EXTRA_PHOTO, user.getAvatar_url());
        startActivity(intent);
    }

    @Override
    protected void onStop () {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }
}