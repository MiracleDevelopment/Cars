package com.sevenpeakssoftware.patipan.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sevenpeakssoftware.patipan.R
import com.sevenpeakssoftware.patipan.getDateFormat
import com.sevenpeakssoftware.patipan.loadPhotoWithGlide
import com.sevenpeakssoftware.patipan.shared.mapper.BaseCarsListItem
import com.sevenpeakssoftware.patipan.shared.mapper.CarsItemViewType
import com.sevenpeakssoftware.patipan.shared.mapper.CarsListItem
import com.sevenpeakssoftware.patipan.shared.mapper.EmptyItem
import kotlinx.android.synthetic.main.layout_audi_item_adapter.view.*
import kotlinx.android.synthetic.main.layout_empty_view.view.*
import java.lang.IllegalStateException

class CarsListAdapter : ListAdapter<BaseCarsListItem, RecyclerView.ViewHolder>(CarsListDiffUtil()) {

  private var onCarsListenerItem: OnCarsListenerItem? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    return when (viewType) {
      CarsItemViewType.audi -> {
        val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.layout_audi_item_adapter, parent, false)
        AudiViewHolder(view)
      }

      CarsItemViewType.emptyView -> {
        val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.layout_empty_view, parent, false)
        EmptyViewHolder(view, onCarsListenerItem)
      }

      else -> {
        throw  IllegalStateException("Not Found View Holder in ${this::class.java}")
      }
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when (holder) {
      is AudiViewHolder -> {
        val items = getItem(position) as? CarsListItem
        holder.onBind(items)
      }

      is EmptyViewHolder -> {
        val items = getItem(position) as? EmptyItem
        holder.onBind(items)
      }

    }
  }

  fun setOnCarsListenerItem(onCarsListenerItem: OnCarsListenerItem?){
    this.onCarsListenerItem = onCarsListenerItem
  }

  fun addItemCarsList(listCars: ArrayList<BaseCarsListItem>) {
    val listItems: ArrayList<BaseCarsListItem> = arrayListOf()
    listItems.addAll(listCars)

    submitList(listItems)
  }

  override fun getItemViewType(position: Int): Int {
    return when (getItem(position)) {
      is CarsListItem -> {
        CarsItemViewType.audi
      }

      is EmptyItem -> {
        CarsItemViewType.emptyView
      }

      else -> {
        throw IllegalStateException("Not Found Item View Type of ${this::class.java}")
      }
    }
  }

  inner class AudiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun onBind(items: CarsListItem?) {
      items ?: return
      itemView.apply {
        audiItemTitle.text = items.title
        audiItemIngress.text = items.ingress

        audiItemDate.text = getDateFormat(items.publishDate) ?: "-"
        loadPhotoWithGlide(context, items.image, audiItemImLogo)
      }
    }
  }

  inner class EmptyViewHolder(
    itemView: View, private val onCarsListenerItem: OnCarsListenerItem?
  ) : RecyclerView.ViewHolder(itemView) {
    fun onBind(items: EmptyItem?) {
      items ?: return
      itemView.apply {
        emptyViewRetry.setOnClickListener {
          onCarsListenerItem?.onRetryAgain()
        }
      }
    }
  }

  class CarsListDiffUtil : DiffUtil.ItemCallback<BaseCarsListItem>() {
    override fun areItemsTheSame(oldItem: BaseCarsListItem, newItem: BaseCarsListItem): Boolean {
      return oldItem.itemId() == newItem.itemId()
    }

    override fun areContentsTheSame(oldItem: BaseCarsListItem, newItem: BaseCarsListItem): Boolean {
      return if (oldItem is CarsListItem && newItem is CarsListItem) {
        oldItem.itemId == newItem.itemId && oldItem.id == newItem.id
      } else {
        true
      }
    }
  }
}

interface OnCarsListenerItem {
  fun onRetryAgain()
}