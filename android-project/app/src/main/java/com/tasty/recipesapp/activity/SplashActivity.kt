package com.tasty.recipesapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SplashActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Log.d(TAG, "onCreate() called in $TAG")
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handlerThread = HandlerThread("SplashHandlerThread", -10)
        handlerThread.start() // Create a Handler on the new HandlerThread
        val handler = Handler(handlerThread.looper)
        val SPLASH_TIME_OUT = 3000
        handler.postDelayed(
            {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            SPLASH_TIME_OUT.toLong()
        )
//        binding.startButton.setOnClickListener{
//            Log.d(MainActivity.TAG, "${MainActivity.TAG} Input text is" + binding.inputText)
//
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("message", binding.inputText.text.toString())
//            startActivity(intent)
//
//        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called in $TAG")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called in $TAG")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called in $TAG")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called in $TAG")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called in $TAG")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called in $TAG")
    }
}