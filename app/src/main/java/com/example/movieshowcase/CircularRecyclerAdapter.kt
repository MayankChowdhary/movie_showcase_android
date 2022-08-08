package com.example.movieshowcase

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.movieshowcase.databinding.CircleListItemBinding
import com.example.movieshowcase.databinding.SquareListItemBinding
import kotlin.math.roundToInt


class CircularRecyclerAdapter(
    private val dataList: ArrayList<ItemModel>,
    private val listener: (ItemModel, Int) -> Unit
) :
    RecyclerView.Adapter<CircularRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = CircleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataList[position])
        holder.itemView.setOnClickListener { listener(dataList[position], position) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(var itemBinding: CircleListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(dataItem: ItemModel) {
            itemBinding.circleListTitle.text = dataItem.name
            setImageSrc(dataItem)
        }

        private fun setImageSrc(dataItem: ItemModel) {
            Glide.with(itemBinding.circleListImage.context)
                .load(dataItem.image)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .transition(withCrossFade())
                .circleCrop()
                .into(itemBinding.circleListImage)
        }

    }


}
