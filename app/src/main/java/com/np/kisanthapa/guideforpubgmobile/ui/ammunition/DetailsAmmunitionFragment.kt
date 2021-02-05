package com.np.kisanthapa.guideforpubgmobile.ui.ammunition

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.adapter.SupportedWeaponAdapter
import com.np.kisanthapa.guideforpubgmobile.adapter.SupportedWeaponModel
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.model.AmmunitionModelParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso

class DetailsAmmunitionFragment : Fragment() {

    private lateinit var mAmmunitionImageCardView: CardView
    private lateinit var mAmmunitionImageView: ImageView
    private lateinit var mAmmunitionName: TextView
    private lateinit var mAmmunitionFeature: TextView
    private lateinit var mAmmunitionSummary: TextView

    // For Supported Guns List
    private lateinit var mAmmSupportedWeaponRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_details_ammunition, container, false)


        // init views here
        mAmmunitionImageCardView = root.findViewById(R.id.mAmmunitionImageCardView)
        mAmmunitionImageView = root.findViewById(R.id.mAmmunitionImageView)
        mAmmunitionName = root.findViewById(R.id.mAmmunitionName)
        mAmmunitionFeature = root.findViewById(R.id.mAmmunitionFeature)
        mAmmunitionSummary = root.findViewById(R.id.mAmmunitionSummary)


        //Init recycler view here
        mAmmSupportedWeaponRecyclerView = root.findViewById(R.id.mAmmSupportedWeaponRecyclerView)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ammunitionData =
            DetailsAmmunitionFragmentArgs.fromBundle(requireArguments()).ammunitionDataArgs

        setDataInViews(ammunitionData)

    }

    private fun setDataInViews(ammunitionData: AmmunitionModelParcelable) {
        mAmmunitionName.text = ammunitionData.amName
        mAmmunitionSummary.text = ammunitionData.amSummary
        mAmmunitionFeature.text = ammunitionData.amFeature

        Picasso.get().load(
            UtilityObject.getDrawableIdByName(
                requireContext(),
                ammunitionData.amImageName
            )
        ).into(mAmmunitionImageView)


        // Prepare Supported weapon data here
        val weaponNameList = ammunitionData.amSupportedWeaponName.split("\n")
        val weaponImageList = ammunitionData.amSupportedWeaponImages.split("\n")

        val supportedWeaponDataList = mutableListOf<SupportedWeaponModel>()

        for ((index, value) in weaponNameList.withIndex()) {

            val weaponData =
                SupportedWeaponModel(
                    index + 1,
                    value,
                    weaponImageList[index]
                )
            supportedWeaponDataList.add(weaponData)
        }
        // Init Recycler View here
        initRecyclerView(supportedWeaponDataList)

    }

    private fun initRecyclerView(supportedWeaponDataList: List<SupportedWeaponModel>) {

        val supportedWeaponAdapter =
            SupportedWeaponAdapter(
                requireContext()
            )

        supportedWeaponAdapter.setSupportedWeaponData(supportedWeaponDataList)

        val viewManager = LinearLayoutManager(requireContext())
        viewManager.orientation = LinearLayoutManager.VERTICAL

        mAmmSupportedWeaponRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = supportedWeaponAdapter
        }
        // For Smooth scrolling
        // ViewCompat.setNestedScrollingEnabled(mAmmunitionRecyclerView, false);

    }

}
