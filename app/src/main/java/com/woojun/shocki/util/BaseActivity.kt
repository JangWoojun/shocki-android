package com.woojun.shocki.util

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController

abstract class BaseActivity : AppCompatActivity() {

    private var backPressedOnce = false
    private val backPressHandler = Handler(Looper.getMainLooper())

    abstract fun getNavController(): NavController?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val navController = getNavController()
                if (navController != null && navController.currentDestination?.id != navController.graph.startDestinationId) {
                    navController.popBackStack()
                } else {
                    if (backPressedOnce) {
                        finish()
                    } else {
                        backPressedOnce = true
                        Toast.makeText(this@BaseActivity, "한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()

                        backPressHandler.postDelayed({
                            backPressedOnce = false
                        }, 2000)
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        backPressHandler.removeCallbacksAndMessages(null)
    }
}
