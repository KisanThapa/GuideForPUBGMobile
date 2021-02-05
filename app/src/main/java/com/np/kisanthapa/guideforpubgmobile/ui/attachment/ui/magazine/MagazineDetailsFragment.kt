package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.adapter.SupportedWeaponAdapter
import com.np.kisanthapa.guideforpubgmobile.adapter.SupportedWeaponModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.adapter.MagSupportedWeaponAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model.MagSupportedWeaponModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model.MagazineModelParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso

class MagazineDetailsFragment : Fragment() {


    private lateinit var mMagazineImageCardView: CardView
    private lateinit var mMagazineImageView: ImageView
    private lateinit var mMagazineName: TextView
    private lateinit var mMagazineFeature: TextView
    private lateinit var mMagazineSummary: TextView

    // For Supported Guns List
    private lateinit var mMagazineSupportedWeaponRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_magazine_details, container, false)

        // init views here
        mMagazineImageCardView = root.findViewById(R.id.mMagazineImageCardView)
        mMagazineImageView = root.findViewById(R.id.mMagazineImageView)
        mMagazineName = root.findViewById(R.id.mMagazineName)
        mMagazineFeature = root.findViewById(R.id.mMagazineFeature)
        mMagazineSummary = root.findViewById(R.id.mMagazineSummary)

        //Init recycler view here
        mMagazineSupportedWeaponRecyclerView =
            root.findViewById(R.id.mMagazineSupportedWeaponRecyclerView)



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val magazineData =
            MagazineDetailsFragmentArgs.fromBundle(requireArguments()).magazineDataArgs

        setDataInViews(magazineData)

    }

    private fun setDataInViews(magazineData: MagazineModelParcelable) {
        mMagazineName.text = magazineData.magName
        mMagazineSummary.text = magazineData.magSummary
        mMagazineFeature.text = magazineData.magFeature

        Picasso.get().load(
            UtilityObject.getDrawableIdByName(
                requireContext(),
                magazineData.magImage
            )
        ).into(mMagazineImageView)


        // Prepare Supported weapon data here
        val weaponNameList = magazineData.magSupportedWeaponName.split("\n")
        val weaponImageList = magazineData.magSupportedWeaponImage.split("\n")
        val magDefCapacity = magazineData.magSupportedWeaponDefCap.split("\n")
        val magExtCapacity = magazineData.magSupportedWeaponExtCap.split("\n")

        val supportedWeaponDataList = mutableListOf<MagSupportedWeaponModel>()

        for ((index, value) in weaponNameList.withIndex()) {

            val weaponData =
                MagSupportedWeaponModel(
                    index + 1,
                    value,
                    weaponImageList[index],
                    magDefCapacity[index],
                    magExtCapacity[index]
                )
            supportedWeaponDataList.add(weaponData)
        }
        // Init Recycler View here
        initRecyclerView(supportedWeaponDataList)

    }

    private fun initRecyclerView(supportedWeaponDataList: MutableList<MagSupportedWeaponModel>) {

        val supportedWeaponAdapter =
            MagSupportedWeaponAdapter(
                requireContext()
            )

        supportedWeaponAdapter.setSupportedWeaponData(supportedWeaponDataList)

        val viewManager = LinearLayoutManager(requireContext())
        viewManager.orientation = LinearLayoutManager.VERTICAL

        mMagazineSupportedWeaponRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = supportedWeaponAdapter
        }
    }

}