package com.example.nusanty_capstoneproject.ui.activity.main.ui.akun

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nusanty_capstoneproject.databinding.FragmentAkunBinding
import com.example.nusanty_capstoneproject.helper.UserPreference
import com.example.nusanty_capstoneproject.ui.activity.login.LoginActivity

class AkunFragment : Fragment() {

    private var _binding: FragmentAkunBinding? = null

    private val binding get() = _binding!!

    private lateinit var userPreference: UserPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val akunViewModel =
            ViewModelProvider(this)[AkunViewModel::class.java]

        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAkun
        akunViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreference = UserPreference(requireActivity())

        btnAction()
    }

    private fun btnAction(){
        binding.btnLogout.setOnClickListener {
            userPreference.logout()
            requireActivity().run {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}