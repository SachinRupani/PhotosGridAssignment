package com.obvious.photosgridassignment.uiTests

import androidx.lifecycle.ViewModelStore
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.obvious.photosgridassignment.R
import com.obvious.photosgridassignment.launchFragmentInHiltContainer
import com.obvious.photosgridassignment.ui.photos.photoGrid.PhotosGridFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class PhotoGridFragmentTest {

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testPhotoGridItemClick_PhotoDetailsPage_Navigated() {
        runBlocking {

            //Launch fragment
            launchPhotoGridFragment()

            //Wait for recycler view items to fill up
            delay(1500)

            //Click on first photo grid item
            onView(withId(R.id.rvPhotosGrid)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

            //Expected Result
            val expectedNavigatedId = navController.currentDestination?.id

            //Actual Result
            val actualNavigatedId = R.id.fragment_photo_details

            //Check that it navigates to Detail screen
            Assert.assertEquals(expectedNavigatedId, actualNavigatedId)
        }

    }

    /**
     * Function to launch [PhotosGridFragment]
     * in test with [TestNavHostController]
     */
    private fun launchPhotoGridFragment() {
        launchFragmentInHiltContainer<PhotosGridFragment> {
            navController.setViewModelStore(ViewModelStore())
            navController.setGraph(R.navigation.main_nav_graph)
            navController.setCurrentDestination(R.id.fragment_photos_grid)
            viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    Navigation.setViewNavController(this.requireView(), navController)

                }
            }

        }
    }

}