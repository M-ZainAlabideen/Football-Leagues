package com.football.league.classes

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.makeramen.roundedimageview.RoundedImageView
import de.hdodenhof.circleimageview.CircleImageView
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.calligraphy3.R
import io.github.inflationx.viewpump.ViewPump


class GlobalFunctions {
    companion object {
        fun setUpFont() {
            ViewPump.init(
                ViewPump.builder()
                    .addInterceptor(
                        CalligraphyInterceptor(
                            CalligraphyConfig.Builder()
                                .setDefaultFontPath(Constants.REGULAR_FONT)
                                .setFontAttrId(R.attr.fontPath)
                                .build()
                        )
                    )
                    .build()
            )
        }

        fun getImageHeight(context: Context, resId: Int): Int {
            val dimensions = BitmapFactory.Options()
            dimensions.inJustDecodeBounds = true
            val mBitmap = BitmapFactory.decodeResource(context.resources, resId, dimensions)
            return dimensions.outHeight
        }

        fun getImageWidth(context: Context, resId: Int): Int {
            val dimensions = BitmapFactory.Options()
            dimensions.inJustDecodeBounds = true
            val mBitmap = BitmapFactory.decodeResource(context.resources, resId, dimensions)
            return dimensions.outWidth
        }


        fun DisableLayout(layout: ViewGroup) {
            layout.isEnabled = false
            for (i in 0 until layout.childCount) {
                val child = layout.getChildAt(i)
                if (child is ViewGroup) {
                    DisableLayout(child)
                } else {
                    child.isEnabled = false
                }
            }
        }

        fun EnableLayout(layout: ViewGroup) {
            layout.isEnabled = true
            for (i in 0 until layout.childCount) {
                val child = layout.getChildAt(i)
                if (child is ViewGroup) {
                    EnableLayout(child)
                } else {
                    child.isEnabled = true
                }
            }
        }
//
//        fun setImage(context: Context, imagePath: String?, image: CircleImageView, resId: Int) {
//            if (imagePath != null) {
//                try {
//                    val Width = getImageWidth(context, resId)
//                    val Height = getImageHeight(context, resId)
//                    image.layoutParams.height = Height
//                    image.layoutParams.width = Width
//                    Glide.with(context)
//                        .load(imagePath)
//                        .apply(
//                            RequestOptions()
//                                .placeholder(resId)
//                                .centerCrop()
//                        ).into(image)
//
//                } catch (throwable: Throwable) {
//                    throwable.printStackTrace()
//                }
//            }
//        }

        fun setImage(context: Context, imagePath: String?, image: RoundedImageView, resId: Int) {
            if (imagePath != null) {
                try {
//                    val Width = getImageWidth(context, resId)
//                    val Height = getImageHeight(context, resId)
//                    image.layoutParams.height = Height
//                    image.layoutParams.width = Width
                    Glide.with(context)
                        .load(imagePath)
                        .apply(
                            RequestOptions()
                                .placeholder(resId)
                                .centerCrop()
                        ).into(image)

                    image.setCornerRadius(10f, 10f, 10f, 10f)
                } catch (throwable: Throwable) {
                    throwable.printStackTrace()
                }


            }
        }

    }
}