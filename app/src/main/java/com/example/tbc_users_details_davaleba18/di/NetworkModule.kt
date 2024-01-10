package com.example.tbc_users_details_davaleba18.di

import androidx.navigation.Navigator
import com.example.tbc_users_details_davaleba18.data.common.Constants.Companion.BASE_URL_ALL
import com.example.tbc_users_details_davaleba18.data.common.Constants.Companion.BASE_URL_DETAILS
import com.example.tbc_users_details_davaleba18.data.service.IDeleteUserService
import com.example.tbc_users_details_davaleba18.data.service.IGetUserService
import com.example.tbc_users_details_davaleba18.data.service.IGetUsersService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("AllUsers")
    fun provideUsersRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_ALL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()
    }

    @Singleton
    @Provides
    @Named("SingleUser")
    fun provideUserRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_DETAILS)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()
    }

    @Singleton
    @Provides
    fun provideUsersGetService(@Named("AllUsers") retrofit: Retrofit): IGetUsersService {
        return retrofit.create(IGetUsersService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserGetService(@Named("SingleUser") retrofit: Retrofit): IGetUserService {
        return retrofit.create(IGetUserService::class.java)
    }

    @Singleton
    @Provides
    fun provideDeleteUserService(@Named("SingleUser") retrofit: Retrofit): IDeleteUserService{
        return retrofit.create(IDeleteUserService::class.java)
    }
}