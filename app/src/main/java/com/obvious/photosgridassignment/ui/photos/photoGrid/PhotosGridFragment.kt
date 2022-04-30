package com.obvious.photosgridassignment.ui.photos.photoGrid

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.obvious.photosgridassignment.R
import com.obvious.photosgridassignment.application.utils.showToast
import com.obvious.photosgridassignment.databinding.FragmentPhotosGridBinding
import com.obvious.photosgridassignment.domain.entities.PhotoEntity
import com.obvious.photosgridassignment.ui.common.UiState
import com.obvious.photosgridassignment.ui.photos.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosGridFragment : Fragment(R.layout.fragment_photos_grid) {

    //Instantiate viewModel (Scoped to nav graph)
    private val viewModel: PhotoViewModel by hiltNavGraphViewModels(
        R.id.navigation_main
    )

    private var binding: FragmentPhotosGridBinding? = null

    private var adapter: PhotoGridAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotosGridBinding.bind(view)

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
                        binding?.progressLoading?.isVisible = true
                    }

                    //list retrieved successfully
                    is UiState.Success -> {
                        adapter?.submitList(data)
                        binding?.progressLoading?.isVisible = false
                    }

                    //Failed to retrieve the list
                    is UiState.Failure -> {
                        activity?.showToast(msg = failureMsg)
                        binding?.progressLoading?.isVisible = false
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
            adapter = PhotoGridAdapter(
                onItemClick = this@PhotosGridFragment::onItemClick
            )
            rvPhotosGrid.adapter = adapter
        }
    }

    /**
     * Function to handle the click event of the image item
     */
    private fun onItemClick(photoEntity: PhotoEntity) {
        val action =
            PhotosGridFragmentDirections.actionPhotosGridToPhotoDetails(photoTitle = photoEntity.title)
        findNavController().navigate(action)
    }
}