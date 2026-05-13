package com.example.raseoapps

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.raseoapps.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val nama = binding.etRegNama.text.toString()
            val email = binding.etRegEmail.text.toString()
            val phone = binding.etRegPhone.text.toString()
            val username = binding.etRegUsername.text.toString()
            val password = binding.etRegPassword.text.toString()

            if (nama.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sharedPref.edit().apply {
                    putString("registered_user", username)
                    putString("registered_pass", password)
                    putString("nama", nama)
                    putString("email", email)
                    putString("phone", phone)
                    apply()
                }
                Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
