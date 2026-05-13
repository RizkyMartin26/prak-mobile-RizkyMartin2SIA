package com.example.raseoapps.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.raseoapps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // Menggunakan Dicebear PNG - Sangat handal dan pasti muncul
    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://api.dicebear.com/9.x/pixel-art/png?seed=Alya"),
        MessageModel("Budi", "Sudah makan?", "https://api.dicebear.com/9.x/pixel-art/png?seed=Budi"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://api.dicebear.com/9.x/pixel-art/png?seed=Citra"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://api.dicebear.com/9.x/pixel-art/png?seed=Dika"),
        MessageModel("Eka", "Nice job kemarin!", "https://api.dicebear.com/9.x/pixel-art/png?seed=Eka"),
        MessageModel("Fajar", "Lagi ngapain?", "https://api.dicebear.com/9.x/pixel-art/png?seed=Fajar"),
        MessageModel("Gita", "Boleh minta tolong?", "https://api.dicebear.com/9.x/pixel-art/png?seed=Gita"),
        MessageModel("Hana", "Lihat email ya", "https://api.dicebear.com/9.x/pixel-art/png?seed=Hana"),
        MessageModel("Irfan", "Oke noted", "https://api.dicebear.com/9.x/pixel-art/png?seed=Irfan"),
        MessageModel("Joko", "Sampai jumpa besok", "https://api.dicebear.com/9.x/pixel-art/png?seed=Joko")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
