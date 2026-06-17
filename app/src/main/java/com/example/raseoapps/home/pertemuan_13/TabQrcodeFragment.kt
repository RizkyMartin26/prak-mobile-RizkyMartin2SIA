package com.example.raseoapps.home.pertemuan_13

import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.raseoapps.databinding.FragmentTabQrcodeBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

class TabQrcodeFragment : Fragment() {
    private var _binding: FragmentTabQrcodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTabQrcodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Fitur 1: Auto-Generate saat mengetik atau paste
        binding.edtQrInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s.toString().trim()
                if (text.isNotEmpty()) {
                    try {
                        binding.ivQrCode.setImageBitmap(createQR(text))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    // Jika kosong, hapus gambar QR
                    binding.ivQrCode.setImageBitmap(null)
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Fitur 2: Tombol Paste dari Clipboard
        binding.btnPaste.setOnClickListener {
            val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val item = clipboard.primaryClip?.getItemAt(0)
            val pasteData = item?.text?.toString()

            if (!pasteData.isNullOrEmpty()) {
                binding.edtQrInput.setText(pasteData)
                Toast.makeText(requireContext(), "Link berhasil ditempel!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Clipboard kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createQR(text: String): Bitmap {
        val writer = QRCodeWriter()
        val hints = HashMap<EncodeHintType, Any>()
        hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
        
        val matrix = writer.encode(
            text,
            BarcodeFormat.QR_CODE,
            500,
            500,
            hints
        )
        
        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565)
        for (x in 0 until 500) {
            for (y in 0 until 500) {
                bitmap.setPixel(x, y, if (matrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        return bitmap
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
