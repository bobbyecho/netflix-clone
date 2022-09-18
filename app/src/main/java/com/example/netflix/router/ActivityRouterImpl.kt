package com.example.netflix.router

import android.content.Context
import android.content.Intent
import com.example.home.presentation.ui.home.HomeActivity
import com.example.login.presentation.ui.LoginActivity
import com.example.registration.presentation.ui.RegisterActivity
import com.example.shared.router.ActivityRouter

class ActivityRouterImpl: ActivityRouter {
    override fun loginActivity(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    override fun homeActivity(context: Context): Intent {
        return Intent(context, HomeActivity::class.java)
    }

    override fun registerActivity(context: Context): Intent {
        return Intent(context, RegisterActivity::class.java)
    }
}