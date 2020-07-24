package com.boycoder.kotlinjetpackinaction.chapter.c04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.boycoder.kotlinjetpackinaction.R
import com.boycoder.kotlinjetpackinaction.chapter.html

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.loadDataWithBaseURL(null, getHtmlStr(), "text/html", "UTF-8", null);
    }

    private fun getHtmlStr(): String {
        return html {
            head {
                title { "Kotlin Jetpack In Action" }
            }
            body {
                h1 { "Kotlin Jetpack In Action"}
                p { "-----------------------------------------" }
                p { "A super-simple project demonstrating how to use Kotlin and Jetpack step by step." }
                p { "-----------------------------------------" }
                p { "I made this project as simple as possible," +
                        " so that we can focus on how to use Kotlin and Jetpack" +
                        " rather than understanding business logic." }
                p {"We will rewrite it from \"Java + MVC\" to" +
                        " \"Kotlin + Coroutines + Jetpack + Clean MVVM\"," +
                        " line by line, commit by commit."}
                p { "-----------------------------------------" }
                p { "ScreenShot:" }
                img(src = "https://user-gold-cdn.xitu.io/2020/6/15/172b55ce7bf25419?imageslim", alt = "Kotlin Jetpack In Action")
            }
        }.toString()
    }
}