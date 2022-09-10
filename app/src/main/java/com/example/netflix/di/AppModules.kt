package com.example.netflix.di

import com.example.core.base.BaseModules
import com.example.netflix.router.ActivityRouterImpl
import com.example.shared.router.ActivityRouter
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules: BaseModules {
    override fun getModules(): List<Module> {
        return listOf(routers)
    }

    private val routers: Module = module {
        single<ActivityRouter> { ActivityRouterImpl() }
    }
}