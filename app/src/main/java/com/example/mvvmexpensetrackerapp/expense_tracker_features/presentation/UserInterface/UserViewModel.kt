package com.example.mvvmexpensetrackerapp.expense_tracker_features.presentation.UserInterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmexpensetrackerapp.core.utils.Resources
import com.example.mvvmexpensetrackerapp.expense_tracker_features.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val _loginFlow = MutableStateFlow<Resources<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resources<FirebaseUser>?> = _loginFlow

    private val _sinUpFlow = MutableStateFlow<Resources<FirebaseUser>?>(null)
    val sinUpFlow: StateFlow<Resources<FirebaseUser>?> = _sinUpFlow

    val currentUser : FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null){
            _loginFlow.value = Resources.Success(repository.currentUser!!)
        }
    }

    fun sinInUser(email: String, password: String) {
        viewModelScope.launch {
            _loginFlow.value = Resources.Loading(true)
            val result = repository.loginUser(email, password)
            _loginFlow.value = result
        }
    }

    fun sinUpUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            _sinUpFlow.value = Resources.Loading(true)
            val result = repository.registerUser(name, email, password)
            _sinUpFlow.value = result
        }
    }

     fun sinOut(){
        repository.logOutUser()
         _loginFlow.value = null
         _sinUpFlow.value = null
    }

}