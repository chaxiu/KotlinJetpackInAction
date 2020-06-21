package com.boycoder.kotlinjetpackinaction

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.boycoder.kotlinjetpackinaction.MainActivity
import com.boycoder.kotlinjetpackinaction.entity.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "Main"
        val EXTRA_PHOTO = "photo"
    }

    var stringRequest: StringRequest? = null
    var requestQueue: RequestQueue? = null

    private var image: ImageView? = null
    private var gif: ImageView? = null
    private var username: TextView? = null
    private var company: TextView? = null
    private var website: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        image = findViewById(R.id.image)
        gif = findViewById(R.id.gif)
        username = findViewById(R.id.username)
        company = findViewById(R.id.company)
        website = findViewById(R.id.website)
        display(User.CACHE_RESPONSE)
        requestOnlineInfo()
    }

    private fun requestOnlineInfo() {
        requestQueue = Volley.newRequestQueue(this)
        val url = "https://api.github.com/users/JakeWharton"
        stringRequest = StringRequest(Request.Method.GET, url,
                object: Response.Listener<String> {
                    override fun onResponse(response: String?) {
                        display(response)
                    }
                }, object: Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(this@MainActivity, error?.message, Toast.LENGTH_SHORT).show()
            }
        })
        stringRequest!!.tag = TAG
        requestQueue!!.add(stringRequest)
    }

    private fun display(response: String?) {
        if (TextUtils.isEmpty(response)) {
            return
        }
        val gson = Gson()
        val user = gson.fromJson(response, User::class.java)
        if (user != null) {
            Glide.with(this).load("file:///android_asset/bless.gif").into(gif!!)
            Glide.with(this).load(user.getAvatar_url()).apply(RequestOptions.circleCropTransform()).into(image!!)
            username!!.text = user.getName()
            company!!.text = user.getCompany()
            website!!.text = user.getBlog()
            image!!.setOnClickListener(object: View.OnClickListener{
                override fun onClick(v: View?) {
                    gotoImagePreviewActivity(user)
                }
            })
        }
    }

    private fun gotoImagePreviewActivity(user: User) {
        val intent = Intent(this, ImagePreviewActivity::class.java)
        intent.putExtra(EXTRA_PHOTO, user.getAvatar_url())
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        if (requestQueue != null) {
            requestQueue!!.cancelAll(TAG)
        }
    }
}