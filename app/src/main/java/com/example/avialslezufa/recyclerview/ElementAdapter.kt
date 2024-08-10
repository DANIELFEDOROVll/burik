package com.example.avialslezufa.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avialslezufa.R
import com.example.avialslezufa.databinding.ElementBinding

class ElementAdapter: RecyclerView.Adapter<ElementAdapter.ElementHolder>() {
    val elementList = arrayListOf<Element>()
    class ElementHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ElementBinding.bind(item)
        fun bind(element: Element) = with(binding){
            imageViewElement.setImageResource(element.image)
            textViewElement.text = element.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element, parent, false)
        return ElementHolder(view)
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    override fun onBindViewHolder(holder: ElementHolder, position: Int) {
        holder.bind(elementList[position])
    }

    fun addElement(element: Element){
        elementList.add(element)
        notifyDataSetChanged()
    }
}