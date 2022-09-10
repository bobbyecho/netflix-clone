package com.example.shared.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.base.BaseModules
import com.example.shared.data.local.datastore.UserPreferenceDataSource
import com.example.shared.data.local.datastore.UserPreferenceDataSourceImpl
import com.example.shared.data.local.datastore.UserPreferenceFactory
import com.example.shared.data.remote.NetworkClient
import com.example.shared.data.remote.datasource.SharedFeatureApiDataSource
import com.example.shared.data.remote.datasource.SharedFeatureApiDataSourceImpl
import com.example.shared.data.remote.services.SharedFeatureApi
import com.example.shared.domain.GetUserTokenUseCase
import com.example.shared.data.repository.SharedApiRepository
import com.example.shared.data.repository.SharedApiRepositoryImpl
import com.example.shared.data.repository.UserPreferenceRepositoryImpl
import com.example.shared.data.repository.UserPreferenceRepository
import com.example.shared.domain.SaveAuthDataUseCase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object SharedModules: BaseModules {
    override fun getModules(): List<Module> = listOf(
        remote,
        local,
        dataSource,
        repository,
        sharedUseCase,
        common
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
        single<UserPreferenceRepository> { UserPreferenceRepositoryImpl(get()) }
        single<SharedApiRepository> { SharedApiRepositoryImpl(get()) }
    }

    private val sharedUseCase = module {
        single { GetUserTokenUseCase(get(), Dispatchers.IO) }
        single { SaveAuthDataUseCase(get(), Dispatchers.IO) }
    }

    private val common = module {
        single { Gson() }
    }
}