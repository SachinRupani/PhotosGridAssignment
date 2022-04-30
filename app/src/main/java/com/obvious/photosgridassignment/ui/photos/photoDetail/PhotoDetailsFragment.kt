package com.obvious.photosgridassignment.ui.photos.photoDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.obvious.photosgridassignment.R
import com.obvious.photosgridassignment.application.utils.showToast
import com.obvious.photosgridassignment.databinding.FragmentPhotoDetailsBinding
import com.obvious.photosgridassignment.ui.common.UiState
import com.obvious.photosgridassignment.ui.photos.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailsFragment : Fragment(R.layout.fragment_photo_details) {

    /**
     * Incoming arguments into the fragment
     */
    private val args: PhotoDetailsFragmentArgs by navArgs()

    private var binding: FragmentPhotoDetailsBinding? = null

    //Instantiate viewModel (Scoped to nav graph)
    private val viewModel: PhotoViewModel by hiltNavGraphViewModels(
        R.id.navigation_main
    )

    private var adapter: PhotoDetailPagerItemAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotoDetailsBinding.bind(view)
        setupAdapter()
        attachObservers()

    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    /**
     * Function which contains all the observers
     */
    private fun attachObservers() {
        viewModel.uiStatePhotos.observe(viewLifecycleOwner) { uiStatePhotos ->
            uiStatePhotos?.run {
                when (this) {

                    //List is loading currently
                    is UiState.Loading -> {

                    }

                    //list retrieved successfully
                    is UiState.Success -> {
                        adapter?.submitList(data)
                        scrollToClickedPhoto()
                    }

                    //Failed to retrieve the list
                    is UiState.Failure -> {
                        activity?.showToast(msg = failureMsg)
                    }
                }
            }
        }
    }

    /**
     * Function to setup the adapter in recycler view
     * (Populate recycler view with list/grid of photos)
     */
    private fun setupAdapter() {
        binding?.run {
            adapter = PhotoDetailPagerItemAdapter()
            viewPager.adapter = adapter

        }
    }

    /**
     * Function to scroll to clicked photo
     * incoming to this photo detail fragment
     */
    private fun scrollToClickedPhoto() {
        binding?.run {
            args.photoTitle?.let { photoTitleNotNull ->
                val activeItemIndex =
                    adapter?.currentList?.indexOfFirst { it.title == photoTitleNotNull } ?: 0
                if (activeItemIndex > 0) {
                    viewPager.post {
                        viewPager.setCurrentItem(activeItemIndex, true)
                    }
                }
            }
        }
    }
}