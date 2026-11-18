package com.example.lab5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val images = ArrayList<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setData()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomRecyclerAdapter(images)
    }

    private fun setData() {
        images.add(Image("Image 1", R.drawable.image1))
        images.add(Image("Image 2", R.drawable.image2))
        images.add(Image("Image 3", R.drawable.image3))
        images.add(Image("Image 4", R.drawable.image4))
    }
}