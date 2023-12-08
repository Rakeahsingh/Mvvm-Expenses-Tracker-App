package com.example.mvvmexpensetrackerapp.expense_tracker_features.domain.repository

import com.example.mvvmexpensetrackerapp.core.utils.Resources
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

     val currentUser: FirebaseUser?

     suspend fun loginUser(email: String, password: String): Resources<FirebaseUser>

     suspend fun registerUser(name: String, email: String, password: String): Resources<FirebaseUser>

     suspend fun updateProfile(name: String, email: String, password: String): Resources<FirebaseUser>

      fun logOutUser()

}