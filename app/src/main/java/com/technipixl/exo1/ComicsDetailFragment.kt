package com.technipixl.exo1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.technipixl.exo1.databinding.FragmentComicsDetailBinding

class ComicsDetailFragment : Fragment() {

    private var binding: FragmentComicsDetailBinding? = null
    private val args: ComicsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComicsDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}