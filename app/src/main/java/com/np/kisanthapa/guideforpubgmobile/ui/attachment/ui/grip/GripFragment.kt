package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip

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
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.adapter.GripRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.model.GripModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.model.GripModelParcelable

class GripFragment : Fragment() {

    companion object {
        fun newInstance() = GripFragment()
    }

    // View Model
    private lateinit var viewModel: GripViewModel

    // Recycler view
    private lateinit var mGripRecyclerView: RecyclerView

    // Adapter
    private lateinit var mGripRecyclerAdapter: GripRecyclerAdapter

    // Nav Controller
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.grip_fragment, container, false)

        viewModel = ViewModelProvider(this).get(GripViewModel::class.java)

        // Init Recycler view
        mGripRecyclerView = root.findViewById(R.id.mGripRecyclerView)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = Navigation.findNavController(view)

        initRecyclerView()

        // Observe data here
        viewModel.gripList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mGripRecyclerAdapter.setGripData(it)
            }
        })

    }

    private fun initRecyclerView() {

        mGripRecyclerAdapter = GripRecyclerAdapter(requireContext())

        val viewManager = GridLayoutManager(requireContext(), 2)

        mGripRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mGripRecyclerAdapter
        }

        // Click handling
        mGripRecyclerAdapter.onSetGripItemClickListener(object :
            GripRecyclerAdapter.GripItemClickCallback {
            override fun onItemClick(gripModel: GripModel) {

                val gripModelParcelable = GripModelParcelable(
                    gripModel.gripId,
                    gripModel.gripName,
                    gripModel.gripSummary,
                    gripModel.gripCapacity,
                    gripModel.gripFeature,
                    gripModel.gripSupportedWeaponName,
                    gripModel.gripSupportedWeaponImage,
                    gripModel.gripImage
                )

                mNavController.navigate(
                    GripFragmentDirections.actionGripFragmentToGripDetailsFragment(
                        gripModelParcelable,
                        gripModel.gripName
                    )
                )

            }

        })

    }

}