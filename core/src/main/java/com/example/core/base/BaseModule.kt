package com.example.core.base

import org.koin.core.module.Module

interface BaseModules {
    fun getModules(): List<Module>
}

interface FeatureModules: BaseModules {
    val repositories: Module
    val viewModels: Module
    val dataSources: Module
    val useCases: Module
    val network: Module
}
