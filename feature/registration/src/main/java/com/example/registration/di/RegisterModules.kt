package com.example.registration.di

import com.example.core.base.FeatureModules
import com.example.registration.data.network.datasource.RegisterDataSource
import com.example.registration.data.network.datasource.RegisterDataSourceImpl
import com.example.registration.data.network.services.RegisterFeatureApi
import com.example.registration.data.repository.RegisterRepository
import com.example.registration.data.repository.RegisterRepositoryImpl
import com.example.registration.domain.CheckRegisterFieldUseCase
import com.example.registration.domain.RegisterUserUseCase
import com.example.registration.presentation.ui.RegisterViewModel
import com.example.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf

import org.koin.core.module.Module
import org.koin.dsl.module

object RegisterModules: FeatureModules {
    override fun getModules(): List<Module> = listOf(repositories, viewModels, dataSources, useCases, network)

    override val repositories: Module = module {
        single<RegisterRepository> { RegisterRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::RegisterViewModel)
    }

    override val dataSources: Module = module {
        single<RegisterDataSource> { RegisterDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { CheckRegisterFieldUseCase(Dispatchers.IO) }
        single { RegisterUserUseCase(get(), get(), get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<RegisterFeatureApi> { get<NetworkClient>().create() }
    }
}