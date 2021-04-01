package com.boycoder.kotlinjetpackinaction

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.boycoder.kotlinjetpackinaction.chapter.c04.WebActivity
import com.boycoder.kotlinjetpackinaction.chapter.c06.*
import com.boycoder.kotlinjetpackinaction.chapter.c07.PreDelegate
import com.boycoder.kotlinjetpackinaction.databinding.ActivityMainBinding
import com.boycoder.kotlinjetpackinaction.entity.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    private val TAG = "Main"

    private val binding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) { ActivityMainBinding.inflate(layoutInflater) }

    private val requestQueue: RequestQueue by lazy(LazyThreadSafetyMode.NONE) {
        Volley.newRequestQueue(this)
    }

    private var spResponse: String by PreDelegate(SP_KEY_RESPONSE, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        display(spResponse?:"")
        requestOnlineInfo()
    }

    private fun requestOnlineInfo() {
        val url = "https://api.github.com/users/JakeWharton"
        val stringRequest = StringRequest(Request.Method.GET,
                url,
                { response ->
                    spResponse = response
                    display(response)
                },
                { error ->
                    Toast.makeText(this@MainActivity, error?.message, Toast.LENGTH_SHORT).show()
                })
        stringRequest.tag = TAG
        requestQueue.add(stringRequest)
    }

    private fun display(response: String?) {
        if (response.isNullOrBlank()) {
            return
        }

        val gson = Gson()
        val user = gson.fromJson(response, User::class.java)
        user?.apply {
            Glide.with(this@MainActivity).load("file:///android_asset/bless.gif").into(binding.gif)
            Glide.with(this@MainActivity).load(user.avatar_url).apply(RequestOptions.circleCropTransform()).into(binding.image)
            binding.image.setOnClickListener { gotoImagePreviewActivity(this) }
            binding.username.setOnClickListener { gotoWebActivity() }

            binding.username.text = ktxSpan {
                name!!.bold().italic().size(1.3F).background(Color.YELLOW)
                        .append("\n")
                        .append("\n")
                        .append("Google".strike().italic().size(0.8F).color(Color.GRAY))
                        .append("\n")
                        .append(company!!.color(Color.BLUE).underline())
                        .append("\n")
                        .append("\n")
                        .append(url(blog!!, blog))
            }

            binding.username.movementMethod = LinkMovementMethod.getInstance()
        }

    }

    private fun gotoImagePreviewActivity(user: User) {
        val intent = Intent(this, ImagePreviewActivity::class.java)
        intent.putExtra(EXTRA_PHOTO, user.avatar_url)
        startActivity(intent)
    }

    private fun gotoWebActivity() {
        val intent = Intent(this, WebActivity::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        requestQueue.cancelAll(TAG)
    }
}