package com.example.home.di

import com.example.core.base.FeatureModules
import com.example.home.data.network.datasource.HomeDataSource
import com.example.home.data.network.datasource.HomeDataSourceImpl
import com.example.home.data.network.repository.HomeRepository
import com.example.home.data.network.repository.HomeRepositoryImpl
import com.example.home.data.network.services.HomeFeatureService
import com.example.home.domain.GetHomeFeedsUseCase
import com.example.home.domain.GetWatchListUseCase
import com.example.home.presentation.ui.home.HomeViewModel
import com.example.shared.data.remote.NetworkClient
import com.example.shared.domain.GetCurrentUserUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object HomeModules: FeatureModules {
    override fun getModules(): List<Module> = listOf(
        repositories, viewModels, dataSources, useCases, network
    )

    override val repositories: Module = module {
        single<HomeRepository> { HomeRepositoryImpl(get()) }
    }

    override val viewModels: Module = module {
        viewModelOf(::HomeViewModel)
    }

    override val dataSources: Module = module {
        single<HomeDataSource> { HomeDataSourceImpl(get()) }
    }

    override val useCases: Module = module {
        single { GetHomeFeedsUseCase(get(), Dispatchers.IO) }
        single { GetWatchListUseCase(get(), Dispatchers.IO) }
        single { GetCurrentUserUseCase(get(), Dispatchers.IO) }
    }

    override val network: Module = module {
        single<HomeFeatureService> { get<NetworkClient>().create() }
    }
}