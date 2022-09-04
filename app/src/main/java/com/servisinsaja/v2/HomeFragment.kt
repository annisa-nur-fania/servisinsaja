package com.servisinsaja.v2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.servisinsaja.v2.Form.FormTvActivity
import com.servisinsaja.v2.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.btnTv.setOnClickListener {
            val intent = Intent (this@HomeFragment.requireContext(), FormTvActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

}


