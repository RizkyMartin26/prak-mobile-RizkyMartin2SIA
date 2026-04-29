package com.example.raseoapps.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.raseoapps.databinding.FragmentHomeBinding
import com.example.raseoapps.home.pertemuan_2.SecondActivity
import com.example.raseoapps.home.pertemuan_3.ThirdActivity
import com.example.raseoapps.home.pertemuan_4.FourthActivity
import com.example.raseoapps.AuthActivity
import com.example.raseoapps.pertemuan_6.SixthActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToSecond.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        binding.btnToThird.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        binding.btnToFifth.setOnClickListener {
            // Jika Pertemuan 5 belum dibuat, bisa diarahkan ke MainActivity atau Toast sementara
            AlertDialog.Builder(requireContext())
                .setTitle("Info")
                .setMessage("Modul Pertemuan 5 sedang dalam pengembangan.")
                .setPositiveButton("OK", null)
                .show()
        }

        binding.btnToSixth.setOnClickListener {
            startActivity(Intent(requireContext(), SixthActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->
                    val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
