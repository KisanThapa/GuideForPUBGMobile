package com.np.kisanthapa.guideforpubgmobile.utility

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import kotlin.math.roundToInt


object UtilityObject {

    fun isRecyclerViewHeader(position: Int) = position == -1

    fun Context.resIdByName(resIdName: String?, resType: String): Int {
        resIdName?.let {
            return resources.getIdentifier(it, resType, packageName)
        }
        throw Resources.NotFoundException()
    }

    fun getDrawableIdByName(context: Context, imageName: String?) =
        context.resources.getIdentifier(imageName, "drawable", context.packageName)

    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        var width = drawable.intrinsicWidth
        width = if (width > 0) width else 1
        var height = drawable.intrinsicHeight
        height = if (height > 0) height else 1
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }


}