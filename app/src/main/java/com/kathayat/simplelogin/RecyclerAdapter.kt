package com.kathayat.simplelogin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter:RecyclerView.Adapter<RecyclerAdapter.ImageViewHolder>() {


    inner class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.recimageView)
    }


    var imgData = listOf(R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,
        R.drawable.image6,R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyler_view,
            parent,false)

        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return imgData.size
    }


}