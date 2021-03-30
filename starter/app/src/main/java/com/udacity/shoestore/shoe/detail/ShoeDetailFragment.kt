package com.udacity.shoestore.shoe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.udacity.shoestore.models.Shoe
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
        binding.shoe = newShoe()

        viewModel.eventSaveAndGoBackToShoeList.observe(viewLifecycleOwner, Observer { isSaving ->
            if (isSaving) {
                if(checkNotEmptyFields()) {
                    addShoeOnList()
                    goBackToShoeList()
                    viewModel.onSaveAndGoBackToShoeListCompleted()
                } else {
                    Toast.makeText(activity, getString(R.string.fill_before_saving),
                            Toast.LENGTH_LONG).show()
                }
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

    private fun newShoe(): Shoe? {
        return Shoe("", 0.00, "", "")
    }

    fun goBackToShoeList() {
        findNavController(this).navigateUp()
    }

    fun addShoeOnList(){
        shoeListViewModel.addShoe(
                binding.shoe!!.name,
                binding.shoe!!.company,
                binding.shoe!!.size,
                binding.shoe!!.description
        )
    }

    private fun checkNotEmptyFields(): Boolean {
        return binding.shoe!!.name.isNotEmpty() &&
                binding.shoe!!.company.isNotEmpty() &&
                (binding.shoe!!.size.toString().isNotEmpty() && !binding.shoe!!.size.isNaN()) &&
                binding.shoe!!.description.isNotEmpty()
    }
}