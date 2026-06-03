package com.example.raseoapps.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.raseoapps.AuthActivity
import com.example.raseoapps.data.api.PhotoApiClient
import com.example.raseoapps.databinding.FragmentHomeBinding
import com.example.raseoapps.home.pertemuan_2.SecondActivity
import com.example.raseoapps.home.pertemuan_4.FourthActivity
import com.example.raseoapps.pertemuan_6.SixthActivity
import com.example.raseoapps.home.pertemuan_9.NinthActivity
import com.example.raseoapps.home.pertemuan_10.TenthActivity
import com.example.raseoapps.home.photo.PhotoAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        binding.btnToFifth.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Info")
                .setMessage("Modul Pertemuan 5 sedang dalam pengembangan.")
                .setPositiveButton("OK", null)
                .show()
        }

        binding.btnToSixth.setOnClickListener {
            startActivity(Intent(requireContext(), SixthActivity::class.java))
        }

        binding.btnToNinth.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        binding.btnToTenth.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        // Logic Logout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }

        binding.btnRefresh.setOnClickListener {
            loadPhoto()
        }

        loadPhoto()
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            // Tampilkan Loading Overlay Rizky Ganteng
            binding.loadingOverlay.visibility = View.VISIBLE
            
            try {
                // Beri jeda 1.5 detik agar tampilan "Rizky Ganteng" terlihat keren
                delay(1500)
                
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter
                
                // List Tampil Vertical
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())
                
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                // Sembunyikan Loading Overlay
                binding.loadingOverlay.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
