package com.ndv.recyclerandcards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val avatar: ImageView = view.findViewById(R.id.avatar)
    val description: TextView = view.findViewById(R.id.name)
    val cardView = view
}

class PersonRecyclerAdapter(private val persons: Array<Person>) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_image, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val person = persons[position]
        val cardViewContext = viewHolder.cardView.context
        val drawableImage = ContextCompat.getDrawable(cardViewContext, person.imageId)

        viewHolder.description.text = person.name
        viewHolder.avatar.setImageDrawable(drawableImage)
    }

    override fun getItemCount() = persons.size
}
