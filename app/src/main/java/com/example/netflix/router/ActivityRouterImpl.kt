package com.example.netflix.router

import android.content.Context
import android.content.Intent
import com.example.home.presentation.ui.HomeActivity
import com.example.login.presentation.ui.LoginActivity
import com.example.shared.router.ActivityRouter

class ActivityRouterImpl: ActivityRouter {
    override fun loginActivity(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    override fun homeActivity(context: Context): Intent {
        return Intent(context, HomeActivity::class.java)
    }
}