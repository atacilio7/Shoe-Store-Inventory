package com.udacity.shoestore.instructions

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
import com.udacity.shoestore.databinding.InstructionsFragmentBinding
import timber.log.Timber

class InstructionsFragment: Fragment() {

    lateinit var viewModel: InstructionsViewModel
    lateinit var binding: InstructionsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        Timber.i("Instructions initialized")
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.instructions_fragment,
            container,
            false
        )
        (activity as MainActivity).toolbarTitle.postValue(getString(R.string.instructions))

        viewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)
        binding.setLifecycleOwner(this)

        binding.instructionsViewModel = viewModel

        viewModel.eventCallShoeList.observe(viewLifecycleOwner, Observer { shoeListCalled ->
            if (shoeListCalled) {
                val action = InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment()
                findNavController(this).navigate(action)
                viewModel.onCallShoeListFinished()
            }
        })

        return binding.root
    }
}