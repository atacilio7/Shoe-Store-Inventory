package com.udacity.shoestore.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeFragmentBinding
import timber.log.Timber

class WelcomeFragment: Fragment() {

    lateinit var viewModel: WelcomeViewModel
    lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        Timber.i("WelcomeFragment initialized")
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.welcome_fragment,
            container,
            false
        )
        (activity as MainActivity).toolbarTitle.postValue(getString(R.string.welcome))

        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.setLifecycleOwner(this)

        binding.welcomeViewModel = viewModel

        viewModel.eventCallInstructions.observe(viewLifecycleOwner, Observer { instructionsCalled ->
            if (instructionsCalled) {
                val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment()
                findNavController(this).navigate(action)
                viewModel.onCallInstructionsFinished()
            }
        })

        return binding.root
    }
}