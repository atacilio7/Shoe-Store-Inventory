package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import timber.log.Timber

class LoginFragment: Fragment() {

    lateinit var viewModel: LoginViewModel
    lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Timber.i("LoginFragment initialized")

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        (activity as MainActivity).toolbarTitle.postValue(getString(R.string.login))

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.setLifecycleOwner(this)

        binding.loginViewModel = viewModel

        viewModel.eventCallWelcome.observe(viewLifecycleOwner, Observer { welcomeCalled ->
            if (welcomeCalled) {
                val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                findNavController(this).navigate(action)
                viewModel.onCallWelcomeFinished()
            }
        })

        return binding.root
    }
}