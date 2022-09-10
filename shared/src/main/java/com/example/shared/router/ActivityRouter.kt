package com.example.shared.router

import android.content.Context
import android.content.Intent

interface ActivityRouter {
    fun loginActivity(context: Context): Intent
    fun homeActivity(context: Context): Intent
}