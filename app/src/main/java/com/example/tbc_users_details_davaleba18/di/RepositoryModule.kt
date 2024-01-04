package com.example.tbc_users_details_davaleba18.di

import com.example.tbc_users_details_davaleba18.data.common.HandleResponse
import com.example.tbc_users_details_davaleba18.data.repository.GetUserRepositoryImpl
import com.example.tbc_users_details_davaleba18.data.repository.GetUsersRepositoryImpl
import com.example.tbc_users_details_davaleba18.data.service.IGetUserService
import com.example.tbc_users_details_davaleba18.data.service.IGetUsersService
import com.example.tbc_users_details_davaleba18.domain.repository.IGetUserRepository
import com.example.tbc_users_details_davaleba18.domain.repository.IGetUsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGetUsersRepository(
        getService: IGetUsersService,
        handler: HandleResponse
    ): IGetUsersRepository {
        return GetUsersRepositoryImpl(getService, handler)
    }

    @Singleton
    @Provides
    fun provideGetUserRepository(
        getService: IGetUserService,
        handler: HandleResponse
    ): IGetUserRepository {
        return GetUserRepositoryImpl(getService, handler)
    }

    @Singleton
    @Provides
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }
}