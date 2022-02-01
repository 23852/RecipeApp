package com.example.recipeapptest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapptest.databinding.RowItemBinding
import com.example.recipeapptest.model.Person

class PersonAdapter(
    var list: ArrayList<Person>
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            list[position]
        )
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private var item: RowItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun bind(person: Person) {
            item.course.text = person.name.trim()
            item.type.text = person.age.toString().trim()
        }
    }
}