package com.rnsoft.newyorkapidemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rnsoft.newyorkapidemo.databinding.DetailFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: Fragment()  {

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: DetailFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DetailFragmentLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        setupUI()
        observeData()
        return view
    }

    private fun setupUI(){
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, backPressedCallback )
        binding.imageView2.setOnClickListener{
            findNavController().popBackStack(R.id.nav_to_main_fragment, false)
        }
    }

    private fun observeData(){
        viewModel.item.observe(viewLifecycleOwner) {
            binding.abstractTextView.text = it.abstractString
            binding.bylineTextView.text = it.byline
        }
    }

    private val backPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().popBackStack()
        }
    }


}