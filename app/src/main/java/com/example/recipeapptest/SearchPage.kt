package com.example.recipeapptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.example.recipeapptest.R
import com.example.recipeapptest.adapter.PersonAdapter
import com.example.recipeapptest.databinding.ActivitySearchPageBinding
import com.example.recipeapptest.model.Person

class SearchPage : AppCompatActivity() {
    private var _binding: ActivitySearchPageBinding? = null
    private val binding get() = _binding!!

    private var people: ArrayList<Person> = arrayListOf()
    private var matchedPeople: ArrayList<Person> = arrayListOf()
    private var personAdapter: PersonAdapter = PersonAdapter(people)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_search_page)

        initRecyclerView()
        performSearch()
    }

    override fun onResume() {
        initRecyclerView()
        super.onResume()
    }

    private fun initRecyclerView() {

        people = arrayListOf(
            Person("Dessert", "Cake"),
            Person("Dessert", "Muffin"),
            Person("Dessert", "Cream Puff"),
            Person("Appetizer", "Fries"),
            Person("Appetizer", "Baked Potato"),
            Person("Seafood", "Tomyam"),
            Person("Seafood", "Siakap"),
            Person("Lunch", "Nasi Ayam"),
            Person("Lunch", "Nasi Lemak"),
            Person("Lunch", "Mihun Sup"),
        )

        personAdapter = PersonAdapter(people).also {
            binding.recyclerView.adapter = it
            binding.recyclerView.adapter!!.notifyDataSetChanged()
        }
        binding.searchView.isSubmitButtonEnabled = true
    }

    private fun performSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })
    }

    private fun search(text: String?) {
        matchedPeople = arrayListOf()

        text?.let {
            people.forEach { person ->
                if (person.course.contains(text, true) ||
                    person.type.toString().contains(text, true)
                ) {
                    matchedPeople.add(person)
                    updateRecyclerView()
                }
            }
            if (matchedPeople.isEmpty()) {
                Toast.makeText(this, "No match found!", Toast.LENGTH_SHORT).show()
            }
            updateRecyclerView()
        }
    }

    private fun updateRecyclerView() {
        binding.recyclerView.apply {
            personAdapter.list = matchedPeople
            personAdapter.notifyDataSetChanged()
        }
    }
}