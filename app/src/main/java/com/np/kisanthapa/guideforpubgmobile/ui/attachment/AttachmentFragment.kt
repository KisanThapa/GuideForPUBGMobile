package com.np.kisanthapa.guideforpubgmobile.ui.attachment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.adapter.AttachmentRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.model.AttachmentModelClass

class AttachmentFragment : Fragment() {

    companion object {
        fun newInstance() = AttachmentFragment()
    }

    private lateinit var viewModel: AttachmentViewModel

    // Recycler View
    private lateinit var mAttachmentRecyclerView: RecyclerView

    // Adapter
    private lateinit var attachmentAdapter: AttachmentRecyclerAdapter

    // Navigation Controller
    private lateinit var mNavController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.attachment_fragment, container, false)


        mAttachmentRecyclerView = root.findViewById(R.id.mAttachmentRecyclerView)


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AttachmentViewModel::class.java)

        // Init recycler view here
        initRecyclerView()


        //Observe view model here
        viewModel.mapAttachmentList.observe(viewLifecycleOwner, Observer {
            it?.let {
                attachmentAdapter.setAttachmentData(it)
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // init nav controller
        mNavController = Navigation.findNavController(view)
    }

    private fun initRecyclerView() {

        // View Manager
        val viewManager = GridLayoutManager(requireContext(), 2)

        // Adapter
        attachmentAdapter = AttachmentRecyclerAdapter(requireContext())

        // Set up settings in recycler view
        mAttachmentRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = attachmentAdapter
        }

        attachmentAdapter.setOnAttachmentItemClickListener(object :
            AttachmentRecyclerAdapter.AttachmentItemClickCallback {
            override fun onAttachmentItemClick(attachmentData: AttachmentModelClass) {

                when (attachmentData.id) {
                    1 -> {
                        val args = AttachmentFragmentDirections.actionNavAttachmentToScopeFragment(
                            attachmentData.attachName
                        )
                        mNavController.navigate(args)
                    }
                    2 -> {
                        val args =
                            AttachmentFragmentDirections.actionNavAttachmentToMagazineFragment(
                                attachmentData.attachName
                            )
                        mNavController.navigate(args)
                    }
                    3 -> {
                        val args = AttachmentFragmentDirections.actionNavAttachmentToMuzzleFragment(
                            attachmentData.attachName
                        )
                        mNavController.navigate(args)
                    }
                    4 -> {
                        val args = AttachmentFragmentDirections.actionNavAttachmentToGripFragment(
                            attachmentData.attachName
                        )
                        mNavController.navigate(args)
                    }
                    5 -> {
                        val args = AttachmentFragmentDirections.actionNavAttachmentToStockFragment(
                            attachmentData.attachName
                        )
                        mNavController.navigate(args)
                    }
                    else -> {
                        Toast.makeText(requireContext(), "No Attachment", Toast.LENGTH_SHORT).show()
                    }

                }

            }

        })

    }

}
