package com.sevenpeakssoftware.patipan.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sevenpeakssoftware.patipan.R
import java.lang.IllegalStateException

class CarsListAdapter : ListAdapter<BaseCarsListItem, RecyclerView.ViewHolder>(CarsListDiffUitl()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    return when (viewType) {
      CarsItemViewType.audi -> {
        val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.layout_audi_item_adapter, parent, false)
        AudiViewHolder(view)
      }

      else -> {
        throw  IllegalStateException("Not Found View Holder in ${this::class.java}")
      }
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when (holder) {
      is AudiViewHolder -> {
        holder.onBind()
      }
    }
  }

  fun addItemCarsList(listCars: ArrayList<BaseCarsListItem>) {
    val listItems: ArrayList<BaseCarsListItem> = arrayListOf()

    listItems.add(CarsListItem())
    listItems.add(CarsListItem())
    listItems.add(CarsListItem())
    listItems.add(CarsListItem())
    listItems.add(CarsListItem())

    submitList(listItems)
  }

  override fun getItemViewType(position: Int): Int {
    return when (getItem(position)) {
      is CarsListItem -> {
        CarsItemViewType.audi
      }

      else -> {
        throw IllegalStateException("Not Found Item View Type of ${this::class.java}")
      }
    }
  }

  inner class AudiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun onBind() {

    }
  }

  class CarsListDiffUitl : DiffUtil.ItemCallback<BaseCarsListItem>() {
    override fun areItemsTheSame(oldItem: BaseCarsListItem, newItem: BaseCarsListItem): Boolean {
      return oldItem.itemId() == newItem.itemId()
    }

    override fun areContentsTheSame(oldItem: BaseCarsListItem, newItem: BaseCarsListItem): Boolean {
      return false
    }
  }
}