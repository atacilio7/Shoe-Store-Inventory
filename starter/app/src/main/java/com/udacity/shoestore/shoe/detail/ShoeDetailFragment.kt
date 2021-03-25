package com.udacity.shoestore.shoe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.shoe.list.ShoeListViewModel


class ShoeDetailFragment : Fragment() {

    lateinit var binding: ShoeDetailFragmentBinding
    lateinit var viewModel: ShoeDetailViewModel
    private val shoeListViewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.shoe_detail_fragment,
                container,
                false
        )

        (activity as MainActivity).toolbarTitle.postValue(getString(R.string.shoe_detail))

        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)
        binding.setLifecycleOwner(this)

        binding.shoeDetailViewModel = viewModel

        viewModel.eventSaveAndGoBackToShoeList.observe(viewLifecycleOwner, Observer { isSaving ->
            if (isSaving) {
                addShoeOnList()
                goBackToShoeList()
                viewModel.onSaveAndGoBackToShoeListCompleted()
            }
        })
        viewModel.eventCancelAndGoBackToShoeList.observe(viewLifecycleOwner, Observer { isCancelling ->
            if (isCancelling) {
                goBackToShoeList()
                viewModel.onCancelAndGoBackToShoeListCompleted()
            }
        })

        setHasOptionsMenu(false)

        return binding.root
    }

    fun goBackToShoeList() {
        findNavController(this).navigateUp()
    }

    fun addShoeOnList(){
        shoeListViewModel.addShoe(
            binding.shoeNameEdittext.text.toString(),
            binding.companyEdittext.text.toString(),
            binding.shoeSizeEdittext.text.toString().toDouble(),
            binding.descriptionEdittext.text.toString()
        )
    }
}