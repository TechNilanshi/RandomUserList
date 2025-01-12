package com.example.a20240923_nilanshi_nycschool.di


import com.example.randomuserapp.data.repositoryImpl.RandomUserListRepositoryImpl
import com.example.randomuserapp.domain.repository.RandomUserListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RetrofitModule {

    @Binds
    @Singleton
    abstract fun bindSchoolRepository(impl : RandomUserListRepositoryImpl): RandomUserListRepository
}