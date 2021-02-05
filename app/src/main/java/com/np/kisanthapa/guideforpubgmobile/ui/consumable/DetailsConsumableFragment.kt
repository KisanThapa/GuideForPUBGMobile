package com.np.kisanthapa.guideforpubgmobile.ui.consumable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.model.ConsumableModelClassParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso

class DetailsConsumableFragment : Fragment() {

    private lateinit var mConsumableImageCardView: CardView
    private lateinit var mConsumableImageView: ImageView
    private lateinit var mConsumableName: TextView
    private lateinit var mConsumableFeature: TextView
    private lateinit var mConsumableSummary: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_details_consumable, container, false)

        // init views here
        mConsumableImageCardView = root.findViewById(R.id.mConsumableImageCardView)
        mConsumableImageView = root.findViewById(R.id.mConsumableImageView)
        mConsumableName = root.findViewById(R.id.mConsumableName)
        mConsumableFeature = root.findViewById(R.id.mConsumableFeature)
        mConsumableSummary = root.findViewById(R.id.mConsumableSummary)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val consumableData =
            DetailsConsumableFragmentArgs.fromBundle(requireArguments()).consumableDataArgs

        setDataInViews(consumableData)

    }

    private fun setDataInViews(consumableData: ConsumableModelClassParcelable) {

        mConsumableName.text = consumableData.consumableName
        mConsumableSummary.text = consumableData.consumableDescription
        mConsumableFeature.text = consumableData.consumableFeature

        // Set Image
        try {
            Picasso.get().load(
                UtilityObject.getDrawableIdByName(
                    requireContext(),
                    consumableData.consumableImageName
                )
            ).into(mConsumableImageView)
        } catch (e: Exception) {
            mConsumableImageView
                .setImageResource(R.drawable.ic_image_error_placeholder)
        }

    }

}
