package com.example.mvvmexpensetrackerapp.expense_tracker_features.data.repository

import com.example.mvvmexpensetrackerapp.core.utils.Resources
import com.example.mvvmexpensetrackerapp.expense_tracker_features.domain.repository.AuthRepository
import com.example.mvvmexpensetrackerapp.expense_tracker_features.presentation.UserInterface.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun loginUser(email: String, password: String): Resources<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resources.Success(result.user!!)
        }catch (e: Exception){
            e.printStackTrace()
            Resources.Error(
                message = e.message.toString()
            )
        }

    }

    override suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): Resources<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            Resources.Success(result.user!!)
        }catch (e: Exception){
            e.printStackTrace()
            Resources.Error(
                message = e.message.toString()
            )
        }
    }

    override suspend fun updateProfile(
        name: String,
        email: String,
        password: String
    ): Resources<FirebaseUser> {
        TODO("Not yet implemented")
    }

    override fun logOutUser() {
        firebaseAuth.signOut()
    }
}