package com.example.mvvmexpensetrackerapp.di

import com.example.mvvmexpensetrackerapp.expense_tracker_features.data.repository.AuthRepositoryImpl
import com.example.mvvmexpensetrackerapp.expense_tracker_features.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): AuthRepository{
        return AuthRepositoryImpl(auth)
    }


}