package com.np.kisanthapa.guideforpubgmobile.ui.map

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.google.android.material.chip.ChipGroup
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject


class MapDetailsFragment : Fragment() {

    private lateinit var mMapTypeChipGroup: ChipGroup

    // Declare image view here
    private lateinit var mapImageView: SubsamplingScaleImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_map_details, container, false)

        mMapTypeChipGroup = root.findViewById(R.id.mapTypeChipGroup)

        // Init Image View here
        mapImageView = root.findViewById<SubsamplingScaleImageView>(R.id.mFullMapView)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get mapId that was passed from previous activity
        val mapId = MapDetailsFragmentArgs.fromBundle(requireArguments()).mapId


        // For first layer(Actual map layer) checking map type and setting it
        when (mapId) {
            1 -> {
                setMapDrawable(R.drawable.item_map_erangel_full_transparent)
            }
            2 -> {
                setMapDrawable(R.drawable.item_map_miramar_full_transparent)
            }
            3 -> {
                setMapDrawable(R.drawable.item_map_sanhok_full_transparent)
            }
            4 -> {
                setMapDrawable(R.drawable.item_map_vikendi_full_transparent)
            }
            5 -> {
                mMapTypeChipGroup.visibility = View.GONE
                setMapDrawable(R.drawable.item_map_training)
            }
        }

        // Finally set drawable in image view here
        mMapTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.chipNormalView -> {
                    when (mapId) {
                        1 -> setMapDrawable(R.drawable.item_map_erangel_full_transparent)
                        2 -> setMapDrawable(R.drawable.item_map_miramar_full_transparent)
                        3 -> setMapDrawable(R.drawable.item_map_sanhok_full_transparent)
                        4 -> setMapDrawable(R.drawable.item_map_vikendi_full_transparent)
                        5 -> setMapDrawable(R.drawable.item_map_training)
                    }
                }

                R.id.chipBlueZone -> {
                    when (mapId) {
                        1 -> setMapDrawable(R.drawable.item_map_erangel_full_bluezone)
                        2 -> setMapDrawable(R.drawable.item_map_miramar_full_bluezone)
                        3 -> setMapDrawable(R.drawable.item_map_sanhok_full_bluezone)
                        4 -> setMapDrawable(R.drawable.item_map_vikendi_full_bluezone)
                        5 -> setMapDrawable(R.drawable.item_map_training)
                    }
                }

                R.id.chipLandingSpot -> {
                    when (mapId) {
                        1 -> setMapDrawable(R.drawable.item_map_erangel_full_landing_spot)
                        2 -> setMapDrawable(R.drawable.item_map_miramar_full_landing_spot)
                        3 -> setMapDrawable(R.drawable.item_map_sanhok_full_landing_spot)
                        4 -> setMapDrawable(R.drawable.item_map_vikendi_full_landing_spot)
                        5 -> setMapDrawable(R.drawable.item_map_training)
                    }
                }

                R.id.chipLootingWeapon -> {
                    when (mapId) {
                        1 -> setMapDrawable(R.drawable.item_map_erangel_full_looting_weapon)
                        2 -> setMapDrawable(R.drawable.item_map_miramar_full_looting_weapon)
                        3 -> setMapDrawable(R.drawable.item_map_sanhok_full_looting_weapon)
                        4 -> setMapDrawable(R.drawable.item_map_vikendi_full_looting_weapon)
                        5 -> setMapDrawable(R.drawable.item_map_training)
                    }
                }

                R.id.chipHotspot -> {
                    when (mapId) {
                        1 -> setMapDrawable(R.drawable.item_map_erangel_full_hotspot)
                        2 -> setMapDrawable(R.drawable.item_map_miramar_full_hotspot)
                        3 -> setMapDrawable(R.drawable.item_map_sanhok_full_hotspot)
                        4 -> setMapDrawable(R.drawable.item_map_vikendi_full_hotspot)
                        5 -> setMapDrawable(R.drawable.item_map_training)
                    }
                }

                else -> {
                    when (mapId) {
                        1 -> setMapDrawable(R.drawable.item_map_erangel_full_transparent)
                        2 -> setMapDrawable(R.drawable.item_map_miramar_full_transparent)
                        3 -> setMapDrawable(R.drawable.item_map_sanhok_full_transparent)
                        4 -> setMapDrawable(R.drawable.item_map_vikendi_full_transparent)
                        5 -> setMapDrawable(R.drawable.item_map_training)
                    }

                }

            }

        }

    }

    private fun setMapDrawable(drawableId: Int) {

//        Glide.with(requireContext()).load(drawableId).into(mapImageView)

        val imageBitmap = UtilityObject.drawableToBitmap(requireContext().getDrawable(drawableId)!!) as Bitmap

        mapImageView.setImage(ImageSource.bitmap(imageBitmap))

    }

}
