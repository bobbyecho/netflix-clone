package com.example.splashscreen.di

import com.example.core.base.FeatureModules
import com.example.shared.data.remote.NetworkClient
import com.example.splashscreen.data.network.datasource.SplashScreenDataSource
import com.example.splashscreen.data.network.datasource.SplashScreenDataSourceImpl
import com.example.splashscreen.data.network.service.SplashScreenFeatureAPI
import com.example.splashscreen.data.repository.SplashScreenRepository
import com.example.splashscreen.data.repository.SplashScreenRepositoryImpl
import com.example.splashscreen.domain.SyncUserUseCase
import com.example.splashscreen.presentation.SplashScreenViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object SplashScreenModules: FeatureModules {
    override fun getModules(): List<Module> = listOf(
        repositories, viewModels, dataSources, useCases, network
    )

    override val repositories: Module = module {
        single<SplashScreenRepository> { SplashScreenRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::SplashScreenViewModel)
    }

    override val dataSources: Module = module {
        single<SplashScreenDataSource> { SplashScreenDataSourceImpl(get()) }
    }
    override val useCases: Module = module {
        single {
            SyncUserUseCase(get(), get(), Dispatchers.IO)
        }
    }

    override val network: Module = module {
        single<SplashScreenFeatureAPI> { get<NetworkClient>().create() }
    }
}