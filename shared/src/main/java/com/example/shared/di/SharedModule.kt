package com.example.shared.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.base.BaseModules
import com.example.shared.data.local.datastore.UserPreferenceDataImpl
import com.example.shared.data.local.datastore.UserPreferenceDataSource
import com.example.shared.data.local.datastore.UserPreferenceDataSourceImpl
import com.example.shared.data.local.datastore.UserPreferenceFactory
import com.example.shared.data.remote.NetworkClient
import com.example.shared.data.remote.datasource.SharedFeatureApiDataSource
import com.example.shared.data.remote.datasource.SharedFeatureApiDataSourceImpl
import com.example.shared.data.remote.services.SharedApiRepositoryImpl
import com.example.shared.data.remote.services.SharedFeatureApi
import com.example.shared.domain.GetUserTokenUseCase
import com.example.shared.repository.SharedApiRepository
import com.example.shared.repository.UserPreferenceRepositoryImpl
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object SharedModule: BaseModules {
    override fun getModules(): List<Module> = listOf(

    )

    private val remote = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { NetworkClient(get(), get()) }
        single<SharedFeatureApi> { get<NetworkClient>().create() }
    }

    private val local = module {
        single { UserPreferenceFactory(androidContext()).create() }
    }

    private val dataSource = module {
        single<UserPreferenceDataSource> {
            UserPreferenceDataSourceImpl(get(), get())
        }
        single<SharedFeatureApiDataSource> {
            SharedFeatureApiDataSourceImpl(get())
        }
    }

    private val repository = module {
        single<UserPreferenceFactory> { UserPreferenceRepositoryImpl(get()) }
        single<SharedApiRepository> { SharedApiRepositoryImpl(get) }
    }

    private val sharedUseCase = module {
        single { GetUserTokenUseCase(get(), Dispatchers.IO) }
    }

    private val common = module {
        single { Gson() }
    }
}