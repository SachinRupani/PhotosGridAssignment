package com.obvious.photosgridassignment.ui.photosGrid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.obvious.photosgridassignment.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class PhotosGridFragment : Fragment(R.layout.fragment_photos_grid) {

    //Instantiate viewModel (Scoped to nav graph)
    private val viewModel: PhotosGridViewModel by hiltNavGraphViewModels(
        R.id.navigation_main
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}