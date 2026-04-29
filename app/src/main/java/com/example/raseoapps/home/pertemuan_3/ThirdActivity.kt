package com.example.raseoapps.home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.raseoapps.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            val name = binding.etName.text.toString()
            val score = binding.etScore.text.toString()

            val intent = Intent(this, ThirdResultActivity::class.java)
            intent.putExtra("EXTRA_NAME", name)
            intent.putExtra("EXTRA_SCORE", score)
            startActivity(intent)
        }
    }
}
