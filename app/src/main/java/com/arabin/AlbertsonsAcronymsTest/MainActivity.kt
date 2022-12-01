package com.arabin.AlbertsonsAcronymsTest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.arabin.AlbertsonsAcronymsTest.databinding.ActivityMainBinding
import com.arabin.AlbertsonsAcronymsTest.retrofit.Response
import com.arabin.AlbertsonsAcronymsTest.retrofit.RestAPIStatus
import com.arabin.AlbertsonsAcronymsTest.retrofit.viewmodel.ResponseViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), ShareDataInterface {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun setResult(data: Response) {

    }
}