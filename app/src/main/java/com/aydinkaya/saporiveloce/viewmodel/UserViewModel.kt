package com.aydinkaya.saporiveloce.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {
    private val preferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _loggedInUser = MutableLiveData<User?>()
    val loggedInUser: LiveData<User?> get() = _loggedInUser

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _registrationSuccess = MutableStateFlow(false)
    val registrationSuccess: StateFlow<Boolean> get() = _registrationSuccess

    fun saveLoginData(email: String) {
        preferences.edit()
            .putString("user_email", email)
            .apply()
    }

    fun getEmail(): String? {
        return preferences.getString("user_email", null)
    }

    fun setEmail(email: String) {
        saveLoginData(email)
    }

    fun clearData() {
        preferences.edit().clear().apply()
        _loggedInUser.value = null
    }

    fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        if (email == "aydin@example.com" && password == "password") {
            val user = User(email)
            _loggedInUser.value = user
            _loginSuccess.value = true
            setEmail(email)
            onResult(true, null)
        } else {
            _loginSuccess.value = false
            onResult(false, "Invalid email or password")
        }
    }

    fun registerUser(name: String, email: String, password: String) {
        _registrationSuccess.update { true }
    }

    fun resetRegistrationState() {
        _registrationSuccess.update { false }
    }

    fun logout() {
        clearData()
        _loggedInUser.value = null
    }
}

data class User(val email: String)
