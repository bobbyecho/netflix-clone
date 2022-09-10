package com.example.login.di

import com.example.core.base.FeatureModules
import com.example.login.presentation.ui.LoginViewModel
import com.example.login.data.network.datasource.LoginDataSource
import com.example.login.data.network.datasource.LoginDataSourceImpl
import com.example.login.data.network.repository.LoginRepository
import com.example.login.data.network.repository.LoginRepositoryImpl
import com.example.login.data.network.service.LoginFeatureApi
import com.example.login.domain.CheckLoginFieldUseCase
import com.example.login.domain.LoginUserUseCase
import com.example.shared.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginModules: FeatureModules {
    override fun getModules(): List<Module> = listOf(repositories, viewModels, dataSources, useCases, network)

    override val repositories: Module = module {
        single<LoginRepository> { LoginRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::LoginViewModel)
    }

    override val dataSources: Module = module {
        single<LoginDataSource> { LoginDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { CheckLoginFieldUseCase(Dispatchers.IO) }
        single { LoginUserUseCase(get(), get(), get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<LoginFeatureApi> { get<NetworkClient>().create() }
    }
}