package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.adapter.MagazineRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model.MagazineModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model.MagazineModelParcelable

class MagazineFragment : Fragment() {

    companion object {
        fun newInstance() = MagazineFragment()
    }

    // View Model
    private lateinit var viewModel: MagazineViewModel

    // Recycler view
    private lateinit var mMagazineRecyclerView: RecyclerView

    // Adapter
    private lateinit var mMagazineRecyclerAdapter: MagazineRecyclerAdapter

    // Nav Controller
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.magazine_fragment, container, false)

        // Init Recycler view
        mMagazineRecyclerView = root.findViewById(R.id.mMagazineRecyclerView)

        // View Model
        viewModel = ViewModelProvider(this).get(MagazineViewModel::class.java)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavController = Navigation.findNavController(view)

        initRecyclerView()

        // Observe data here
        viewModel.magazineList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mMagazineRecyclerAdapter.setMagazineData(it)
            }
        })
    }

    private fun initRecyclerView() {
        mMagazineRecyclerAdapter = MagazineRecyclerAdapter(requireContext())

        val viewManager = GridLayoutManager(requireContext(), 2)

        mMagazineRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mMagazineRecyclerAdapter
        }

        // Click handling
        mMagazineRecyclerAdapter.onSetMagazineItemClickListener(object :
            MagazineRecyclerAdapter.MagazineItemClickCallback {
            override fun onItemClick(magazineModel: MagazineModel) {

                val magModelParcelable = MagazineModelParcelable(
                    magazineModel.magId,
                    magazineModel.magName,
                    magazineModel.magSummary,
                    magazineModel.magFeature,
                    magazineModel.magCapacity,
                    magazineModel.magSupportedWeaponName,
                    magazineModel.magSupportedWeaponImage,
                    magazineModel.magSupportedWeaponDefCap,
                    magazineModel.magSupportedWeaponExtCap,
                    magazineModel.magImage
                )

                mNavController.navigate(
                    MagazineFragmentDirections.actionMagazineFragmentToMagazineDetailsFragment(
                        magModelParcelable,
                        magazineModel.magName
                    )
                )

            }

        })

    }

}