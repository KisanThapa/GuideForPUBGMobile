package com.np.kisanthapa.guideforpubgmobile.animate

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View


object ViewAnimation {

    fun rotateFab(view: View, rotate: Boolean): Boolean {

        view.animate().setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {})
            .rotation(if (rotate) 135f else 0f)
        return rotate

    }

}