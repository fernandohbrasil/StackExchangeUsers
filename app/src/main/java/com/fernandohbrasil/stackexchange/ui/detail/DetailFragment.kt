package com.fernandohbrasil.stackexchange.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fernandohbrasil.stackexchange.databinding.DetailFragmentBinding
import com.fernandohbrasil.stackexchange.di.Injectable
import javax.inject.Inject

class DetailFragment : Fragment(), Injectable {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DetailViewModel by viewModels {
        viewModelFactory
    }

    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel.signInState.observe(viewLifecycleOwner, signInStateObserver())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}