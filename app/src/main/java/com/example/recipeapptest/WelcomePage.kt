package com.example.recipeapptest

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.recipeapptest.databinding.ActivityWelcomePageBinding

 class WelcomePage : AppCompatActivity(), OnItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityWelcomePageBinding

    var courses = arrayOf<String?>("Appetizer", "Dessert",
        "Seafood", "Vegeterian")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val imgbtn = findViewById<ImageButton>(R.id.imageButton)

        val searchpage = findViewById<SearchView>(R.id.searchView)

        val spin = findViewById<Spinner>(R.id.spinner)
        spin.onItemSelectedListener = this
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses)
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = ad

        val navController = findNavController(R.id.nav_host_fragment_content_welcome_page)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


        imgbtn.setOnClickListener{
        val intent = Intent(this,IngredientPage:: class.java )
        startActivity(intent)
        }

        searchpage.setOnCloseListener {
            val intent = Intent(this, SearchPage:: class.java)
            startActivity(intent)
        }


    }



    override fun onItemSelected(parent: AdapterView<*>?,
                                view: View, position: Int,
                                id: Long) {
        // make toastof name of course
        // which is selected in spinner
        Toast.makeText(applicationContext,
            courses[position],
            Toast.LENGTH_LONG)
            .show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_welcome_page)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}