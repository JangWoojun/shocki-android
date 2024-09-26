package com.woojun.shocki.util

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.woojun.shocki.R

abstract class BaseActivity(private val isMain: Boolean = false) : AppCompatActivity() {

    private var backPressedOnce = false
    private val backPressHandler = Handler(Looper.getMainLooper())

    abstract fun getNavController(): NavController?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.anim_slide_in_from_right_fade_in, R.anim.anim_fade_out)

        if (isMain) {
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val navController = getNavController()
                    val navList = listOf(R.id.explore, R.id.store, R.id.funding, R.id.profile)

                    if (navController != null && navController.currentDestination?.id !in navList) {
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
    }

    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(R.anim.anim_slide_in_from_left_fade_in, R.anim.anim_fade_out)
        if (isMain) {
            backPressHandler.removeCallbacksAndMessages(null)
        }
    }
}

