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
import com.example.movieshowcase.databinding.SquareListItemBinding
import kotlin.math.roundToInt


class HorizontalRecyclerAdapter(
    private val dataList: ArrayList<ItemModel>,
    private val size: SIZE,
    private val context: Context,
    private val listener: (ItemModel, Int) -> Unit
) :
    RecyclerView.Adapter<HorizontalRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = SquareListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v, size, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataList[position])
        holder.itemView.setOnClickListener { listener(dataList[position], position) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(var itemBinding: SquareListItemBinding, var size: SIZE, var context: Context) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(dataItem: ItemModel) {
            itemBinding.squareListTitle.text = dataItem.name
            setImageViewSize()
            setImageSrc(dataItem)
        }

        private fun setImageSrc(dataItem: ItemModel) {
            Glide.with(itemBinding.squareListImage.context)
                .load(dataItem.image)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .transition(withCrossFade())
                .into(itemBinding.squareListImage)
        }

        private fun setImageViewSize() {
            when (size) {
                SIZE.LANDSCAPE -> {
                    val parms = itemBinding.squareListImage.layoutParams as FrameLayout.LayoutParams
                    parms.width = dpToPx(250)
                    parms.height = dpToPx(150)
                    itemBinding.squareListImage.layoutParams = parms

                }
                SIZE.PORTRAIT -> {
                    val parms = itemBinding.squareListImage.layoutParams as FrameLayout.LayoutParams
                    parms.width = dpToPx(180)
                    parms.height = dpToPx(200)
                    itemBinding.squareListImage.layoutParams = parms
                    itemBinding.squareListPlay.visibility = View.GONE

                }

            }
        }

        private fun dpToPx(dp: Int): Int {
            val displayMetrics: DisplayMetrics = context.resources.displayMetrics
            return ((dp * displayMetrics.density) + 0.5).toInt();
        }

    }


}
