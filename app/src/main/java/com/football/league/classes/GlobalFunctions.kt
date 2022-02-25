package com.football.league.classes

import android.content.Context
import android.graphics.BitmapFactory
import android.view.ViewGroup
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.makeramen.roundedimageview.RoundedImageView
import de.hdodenhof.circleimageview.CircleImageView



class GlobalFunctions {
    companion object {
        private fun getImageHeight(context: Context, resId: Int): Int {
            val dimensions = BitmapFactory.Options()
            dimensions.inJustDecodeBounds = true
            val mBitmap = BitmapFactory.decodeResource(context.resources, resId, dimensions)
            return dimensions.outHeight
        }

        private fun getImageWidth(context: Context, resId: Int): Int {
            val dimensions = BitmapFactory.Options()
            dimensions.inJustDecodeBounds = true
            val mBitmap = BitmapFactory.decodeResource(context.resources, resId, dimensions)
            return dimensions.outWidth
        }


        fun disableLayout(layout: ViewGroup) {
            layout.isEnabled = false
            for (i in 0 until layout.childCount) {
                val child = layout.getChildAt(i)
                if (child is ViewGroup) {
                    disableLayout(child)
                } else {
                    child.isEnabled = false
                }
            }
        }

        fun enableLayout(layout: ViewGroup) {
            layout.isEnabled = true
            for (i in 0 until layout.childCount) {
                val child = layout.getChildAt(i)
                if (child is ViewGroup) {
                    enableLayout(child)
                } else {
                    child.isEnabled = true
                }
            }
        }

        fun setImage(context: Context, imagePath: String?, image: CircleImageView, resId: Int) {
            if (imagePath != null) {
                try {
                    val Height = getImageHeight(context, resId)
                    val width = getImageWidth(context, resId)
                    image.layoutParams.height = Height
                    image.layoutParams.width = width

                    if (imagePath.contains(".svg"))
                        image.loadSVG(imagePath, resId)
                    else
                        glide(context, imagePath, image, resId)

                } catch (throwable: Throwable) {
                    throwable.printStackTrace()
                }
            }
        }

        fun setImage(context: Context, imagePath: String?, image: RoundedImageView, resId: Int) {
            if (imagePath != null) {
                try {
                    val Height = getImageHeight(context, resId)
                    image.layoutParams.height = Height

                    if (imagePath.contains(".svg"))
                        image.loadSVG(imagePath, resId)
                    else
                        glide(context, imagePath, image, resId)

                    image.setCornerRadius(10f, 10f, 10f, 10f)
                } catch (throwable: Throwable) {
                    throwable.printStackTrace()
                }


            }
        }

        private fun ImageView.loadSVG(url: String, resId: Int) {

            val imageLoader = ImageLoader.Builder(this.context)
                .componentRegistry { add(SvgDecoder(this@loadSVG.context)) }
                .build()

            val request = ImageRequest.Builder(this.context)
                .placeholder(resId)
                .error(resId)
                .data(url)
                .target(this)
                .build()

            imageLoader.enqueue(request)
        }

        private fun glide(context: Context, imagePath: String?, image: ImageView, resId: Int){
            Glide.with(context)
                .load(imagePath)
                .apply(
                    RequestOptions()
                        .placeholder(resId)
                        .centerCrop()
                ).error(resId)
                .into(image)
        }
    }
}