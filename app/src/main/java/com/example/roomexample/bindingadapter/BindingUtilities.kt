package com.example.roomexample.bindingadapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.roomexample.database.Scene

//for resolve app:setImage in the item_layout.xml
@BindingAdapter("setImage")
fun ImageView.setSceneImage(scene: Scene?) {
    scene?.let {
        if (scene.photoFile.isNotEmpty()) {
            Glide.with(this.context)
                .load(Uri.parse(scene.photoFile))
                .apply(RequestOptions().centerCrop())
                .into(this)
        } else {
            setImageResource(scene.photoId)
        }
    }
}

//for stopping the progressbar
@BindingAdapter("Data")
fun ProgressBar.setViewVisibility(dataChecked: Any?) {
    visibility = if (dataChecked != null) View.GONE else View.VISIBLE
}
