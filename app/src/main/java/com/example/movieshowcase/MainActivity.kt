package com.example.movieshowcase

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.movieshowcase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupHeaderView()
        setupCastCrewView()
        setupFooterView()
    }


    private fun createHeaderList(): ArrayList<ItemModel> {
        val listData: ArrayList<ItemModel> = arrayListOf()
        for (item in MovieData.headerListImages) {
            listData.add(ItemModel("", item))
        }
        return listData
    }

    private fun createFooterList(): ArrayList<ItemModel> {
        val listData: ArrayList<ItemModel> = arrayListOf()
        for (item in MovieData.footerListImages) {
            listData.add(ItemModel("", item))
        }
        return listData
    }

    private fun createCastList(): ArrayList<ItemModel> {
        val listData: ArrayList<ItemModel> = arrayListOf()
        for (item in MovieData.centreCastImages) {
            listData.add(ItemModel(item.first, item.second))
        }
        return listData
    }

    private fun createCrewList(): ArrayList<ItemModel> {
        val listData: ArrayList<ItemModel> = arrayListOf()
        for (item in MovieData.centreCrewImages) {
            listData.add(ItemModel(item.first, item.second))
        }
        return listData
    }

    private fun setupHeaderView() {
        binding.headerLayoutInclude.headerRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = HorizontalRecyclerAdapter(
                createHeaderList(),
                SIZE.LANDSCAPE,
                this@MainActivity
            ) { item, position ->
                Toast.makeText(
                    this@MainActivity,
                    "Clicked on actor: ${item.image}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            Glide.with(binding.headerLayoutInclude.headerImage.context)
                .load(MovieData.headerPoster)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.headerLayoutInclude.headerImage)
        }
    }

    private fun setupCastCrewView() {

        binding.centreLayoutInclude.centreLayoutReadMore.setOnClickListener {
            if (binding.centreLayoutInclude.centreLayoutReadMore.text.toString().contains("More")) {
                binding.centreLayoutInclude.circularListContainer.visibility = View.VISIBLE
                binding.centreLayoutInclude.centreLayoutReadMore.text = "Read Less"

            } else {
                binding.centreLayoutInclude.circularListContainer.visibility = View.GONE
                binding.centreLayoutInclude.centreLayoutReadMore.text = "Read More"

            }

        }
        binding.centreLayoutInclude.centreCastRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CircularRecyclerAdapter(createCastList()) { item, position ->
                Toast.makeText(
                    this@MainActivity,
                    "Clicked on actor: ${item.image}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.centreLayoutInclude.centreCrewRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CircularRecyclerAdapter(createCrewList()) { item, position ->
                Toast.makeText(
                    this@MainActivity,
                    "Clicked on actor: ${item.image}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun setupFooterView() {
        binding.footerLayoutInclude.footerRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = HorizontalRecyclerAdapter(
                createFooterList(),
                SIZE.PORTRAIT,
                this@MainActivity
            ) { item, position ->
                Toast.makeText(
                    this@MainActivity,
                    "Clicked on actor: ${item.image}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}