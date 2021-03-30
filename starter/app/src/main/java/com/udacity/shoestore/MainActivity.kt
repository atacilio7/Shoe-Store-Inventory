package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val toolbarTitle = MutableLiveData<String>();
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        val navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration.Builder(
                        R.id.loginFragment,
                        R.id.shoeListFragment
                ).build()

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        /**
        The following logic was added because toolbar was not being updated in first fragment when
         it was being used the following code:
        (activity as AppCompatActivity).supportActionBar?.title = "Any string"
         */
        toolbarTitle.observe(this, Observer() { toolbarTitle ->
            binding.toolbar.title = toolbarTitle;
        });
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu?.findItem(R.id.loginFragment)
        item?.setVisible(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loginFragment -> {
                findNavController(R.id.navHostFragment).navigateUp()
                return NavigationUI.onNavDestinationSelected(item, findNavController(R.id.navHostFragment))
                        || super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return navController.navigateUp()
    }
}
