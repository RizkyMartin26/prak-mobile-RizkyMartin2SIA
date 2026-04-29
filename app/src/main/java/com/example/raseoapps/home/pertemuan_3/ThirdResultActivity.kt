package com.example.raseoapps.home.pertemuan_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.raseoapps.databinding.ActivityThirdResultBinding

class ThirdResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME")
        val score = intent.getStringExtra("EXTRA_SCORE")

        binding.tvResultName.text = "Nama: $name"
        binding.tvResultScore.text = "Nilai: $score"

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
