package com.np.kisanthapa.guideforpubgmobile.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_supported_weapon_layout.view.*

class SupportedWeaponAdapter(val context: Context) :
    RecyclerView.Adapter<SupportedWeaponAdapter.SupportedWWeaponViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SupportedWWeaponViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_supported_weapon_layout, parent, false)
        )

    private var supportedWeaponDataList = emptyList<SupportedWeaponModel>()
    internal fun setSupportedWeaponData(data: List<SupportedWeaponModel>) {
        supportedWeaponDataList = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = supportedWeaponDataList.size

    override fun onBindViewHolder(holder: SupportedWWeaponViewHolder, position: Int) {
        holder.bindView(context, supportedWeaponDataList[position])
    }

    class SupportedWWeaponViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val supportedWeaponCount = view.supportedWeaponCount
        private val supportedWeaponImage = view.supportedWeaponImage
        private val supportedWeaponName = view.supportedWeaponName

        fun bindView(context: Context, supportedWeaponModel: SupportedWeaponModel) {
            supportedWeaponCount.text = "${supportedWeaponModel.weaponCount}."
            supportedWeaponName.text = supportedWeaponModel.weaponName

            Log.i("TTTTTT", "Image name: ${supportedWeaponModel.weaponImageName}")

            try {
                Picasso.get().load(
                    UtilityObject.getDrawableIdByName(
                        context,
                        supportedWeaponModel.weaponImageName
                    )
                )
                    .into(supportedWeaponImage)
            } catch (e: Exception) {
                supportedWeaponImage.setImageResource(R.drawable.ic_image_error_placeholder)
            }

        }
    }
}