package com.udacity.shoestore.shoe.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListFragment: Fragment() {

    lateinit var viewModel: ShoeListViewModel
    lateinit var binding: ShoeListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        Timber.i("ShoeList initialized")

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_list_fragment,
            container,
            false
        )
        (activity as MainActivity).toolbarTitle.postValue(getString(R.string.shoe_list))

        viewModel = ViewModelProvider(activity as AppCompatActivity).get(ShoeListViewModel::class.java)
        binding.setLifecycleOwner(this)

        binding.shoeListViewModel = viewModel

        viewModel.eventAddShoe.observe(viewLifecycleOwner, Observer { addShowCalled ->
            if (addShowCalled) {
                Timber.i("Add shoe called")
                val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
                findNavController(this).navigate(action)
                viewModel.onAddShoeCompleted()
            }
        })

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->

            if (binding.noShoesText.visibility == View.VISIBLE) {
                binding.noShoesText.visibility = View.GONE
                binding.scrollView.visibility = View.VISIBLE
            }

            addShoes(shoeList)
        })

        return binding.root
    }

    private fun addShoes(shoes: List<Shoe>) {
        for (shoe in shoes) {

            val layoutInflater = layoutInflater
            val view = layoutInflater.inflate(R.layout.shoe_detailed, null)
            view.findViewById<TextView>(R.id.shoe_name_text_detailed).text = String.format(
                    getString(R.string.shoe_name_detailed),
                    shoe.name
            )
            view.findViewById<TextView>(R.id.company_text_detailed).text = String.format(
                    getString(R.string.company_detailed),
                    shoe.company
            )
            view.findViewById<TextView>(R.id.shoe_size_text_detailed).text = String.format(
                    getString(R.string.shoe_size_detailed),
                    shoe.size
            )
            view.findViewById<TextView>(R.id.description_text_detailed).text = String.format(
                    getString(R.string.description_detailed),
                    shoe.description
            )

            binding.shoesListLayout.addView(view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("ShoeList destroyed")
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.loginFragment)
        item.setVisible(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }
}