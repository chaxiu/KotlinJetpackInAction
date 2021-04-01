package com.boycoder.kotlinjetpackinaction.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.boycoder.kotlinjetpackinaction.Constant;
import com.boycoder.kotlinjetpackinaction.R;
import com.bumptech.glide.Glide;

public class ImagePreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        Intent intent = getIntent();
        String url = intent.getStringExtra(Constant.EXTRA_PHOTO);
        if (!TextUtils.isEmpty(url)) {
            ImageView imageView = findViewById(R.id.imagePreview);
            Glide.with(this).load(url).into(imageView);
        }
    }
}