package com.example.exerciseapplication.ui.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseapplication.R
import com.example.exerciseapplication.model.MenuItem

public class RecycleViewAdapter : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    private var list: List<MenuItem> = emptyList()
    var onDeleteItem: ((MenuItem) -> Unit)? = null

    var onUpdateItem: ((MenuItem) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<MenuItem>) {
        list = newList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val llItemView: LinearLayout = view.findViewById(R.id.llItemView)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val ibDelete: ImageButton = view.findViewById(R.id.ibDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecycleViewAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclew_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecycleViewAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.tvTitle.text = item.name
        holder.tvPrice.text = "%,.0f đ".format(item.price)

        holder.ibDelete.setOnClickListener {
            onDeleteItem?.invoke(item)
        }

        holder.llItemView.setOnClickListener {
            onUpdateItem?.invoke(item)
        }
    }

    override fun getItemCount(): Int = list.size
}