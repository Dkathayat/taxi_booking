package com.kathayat.simplelogin

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kathayat.simplelogin.Extensions.toast
import com.kathayat.simplelogin.databinding.ActivityHomeBinding
import com.kathayat.simplelogin.databinding.ActivityNewUserBinding

class NewUser : AppCompatActivity() {

    private lateinit var binding: ActivityNewUserBinding
    private lateinit var auth: FirebaseAuth

    val MIN_PASSWORD_LENGTH = 6


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        binding.register.setOnClickListener {

            if (validateInput())

            signUp(binding.inputEmail.text.toString(), binding.gotoRegisterpass.text.toString())

        }

    }

    fun validateInput(): Boolean {
        if (binding.firstName.text.toString().equals("")) {
            binding.firstName.setError("Enter First Name")
            return false
        }
        if (binding.lastName.text.toString().equals("")) {
            binding.lastName.setError("Enter Last Name")
            return false
        }
        if (binding.inputEmail.text.toString().equals("")) {
            binding.inputEmail.setError("Enter Email")
            return false
        }
        if (binding.gotoRegisterpass.text.toString().equals("")) {
            binding.gotoRegisterpass.setError("Enter Password")
            return false
        }
        if (binding.gotoRegisterpass2.text.toString().equals("")) {
            binding.gotoRegisterpass2.setError("Confirm Password")
            return false
        }

        // checking the proper email format
        if (!isEmailValid(binding.inputEmail.text.toString())) {
            binding.inputEmail.setError("Enter Valid Email")
            return false
        }

        // checking minimum password Length

        // Checking if repeat password is same
        if (!binding.gotoRegisterpass.text.toString()
                .equals(binding.gotoRegisterpass2.text.toString())
        ) {
            binding.gotoRegisterpass2.setError("Password does not match")
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun signUp(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful && validateInput()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(
                        baseContext, "Account created successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                        val user = auth.currentUser
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                        finish()
                }

                else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}