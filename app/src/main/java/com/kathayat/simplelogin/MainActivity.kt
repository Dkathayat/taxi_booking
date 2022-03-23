package com.kathayat.simplelogin


import android.content.Intent
import android.os.Bundle
import android.util.Patterns

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.kathayat.simplelogin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()



        binding.btnLogin.setOnClickListener {
            val email = binding.loginEmail.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.loginEmail.setError("Invalid Email")
            } else if (password.isEmpty()) {
                binding.loginPassword.setError("Enter Password")
            } else {
                loginUser(email, password)
            }


        }

        binding.gotoRegister.setOnClickListener {
            Intent(this, NewUser::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, Home::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        baseContext, "User does not Exist",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
