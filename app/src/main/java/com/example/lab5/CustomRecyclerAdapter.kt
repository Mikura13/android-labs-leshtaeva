package com.example.lab5

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(
    private val images: List<Image>
) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageView)
        val text: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]
        holder.text.text = image.name
        holder.image.setImageResource(image.imageRes)

        // Обработчик нажатия на картинку — открывает диалог
        holder.image.setOnClickListener {
            showImageDialog(holder.itemView, image.imageRes)
        }
    }

    override fun getItemCount(): Int = images.size

    private fun showImageDialog(view: View, imageRes: Int) {
        val dialog = Dialog(view.context)
        dialog.setContentView(R.layout.dialog_image)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val dialogImageView = dialog.findViewById<ImageView>(R.id.dialogImageView)
        dialogImageView.setImageResource(imageRes)

        dialogImageView.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }
}