package com.example.raseoapps.message

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.raseoapps.R
import com.example.raseoapps.databinding.FragmentMessageBinding
import com.example.raseoapps.message.tutorial.TutorialMessageActivity

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Message"
        }

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
