package com.obvious.photosgridassignment.domain.useCase

import com.obvious.photosgridassignment.Moshi
import com.obvious.photosgridassignment.application.utils.toDateOrNull
import com.obvious.photosgridassignment.data.restApiJsonModels.PhotoJsonModel
import com.obvious.photosgridassignment.data.safeApiCall
import com.obvious.photosgridassignment.domain.common.DataError
import com.obvious.photosgridassignment.domain.common.DataResult
import com.obvious.photosgridassignment.domain.entities.PhotoEntity
import com.obvious.photosgridassignment.domain.repositories.PhotoRepository
import com.obvious.photosgridassignment.domain.useCases.FetchSortedListOfPhotosUseCase
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.InputStreamReader

@RunWith(RobolectricTestRunner::class)
class FetchSortedListOfPhotosUseCaseTest {

    /**
     * Get moshi json adapter
     * @return [JsonAdapter] [PhotoJsonModel]
     */
    private fun getMoshiJsonAdapter(): JsonAdapter<List<PhotoJsonModel>?> {
        val listType = Types.newParameterizedType(List::class.java, PhotoJsonModel::class.java)
        return Moshi.moshiInstance.adapter(listType)
    }

    /**
     * Convert JSON string to POJO/Kotlin data class
     * [PhotoJsonModel] -> [PhotoEntity]
     */
    private fun getPhotoList(jsonString: String): List<PhotoEntity>? {
        return getMoshiJsonAdapter().fromJson(jsonString)?.map { photoJsonModel ->
            photoJsonModel.toPhotoEntity()
        }
    }

    /**
     * Function to get the JSON string from the JSON file
     * @param fileName String filename
     */
    private fun getJsonContent(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }

    /**
     * Function which provides the fake repository implementation
     * of [PhotoRepository]
     */
    private fun getRepositoryImpl(jsonFileName: String) = object : PhotoRepository {
        override suspend fun fetchListOfPhotos(): DataResult<List<PhotoEntity>, DataError> {
            return safeApiCall {
                val jsonString = getJsonContent(fileName = jsonFileName)
                getPhotoList(jsonString = jsonString) ?: emptyList()
            }
        }
    }

    /**
     * Test 1
     */
    @Test
    fun testPhotoList_isSortedDateDescending_ReturnsListDescending() {
        val useCase =
            FetchSortedListOfPhotosUseCase(repository = getRepositoryImpl("photos_list_normal.json"))
        runBlocking {
            //Expected
            val expectedResult = DataResult.Success(
                data = listOf(
                    PhotoEntity(
                        copyright = "ESA/HubbleNASA",
                        strDate = "2019-12-01",
                        explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
                        hdImageUrl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                        mediaType = "image",
                        serviceVersion = "v1",
                        title = "Starburst Galaxy M94 from Hubble",
                        thumbnailImageUrl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
                    ),
                    PhotoEntity(
                        copyright = "Steve Mazlin",
                        strDate = "2019-12-03",
                        explanation = "Is this what will become of our Sun? Quite possibly.  The first hint of our Sun's future was discovered inadvertently in 1764. At that time, Charles Messier was compiling a list of diffuse objects not to be confused with comets. The 27th object on Messier's list, now known as M27 or the Dumbbell Nebula, is a planetary nebula, the type of nebula our Sun will produce when nuclear fusion stops in its core. M27 is one of the brightest planetary nebulae on the sky, and can be seen toward the constellation of the Fox (Vulpecula) with binoculars. It takes light about 1000 years to reach us from M27, featured here in colors emitted by hydrogen and oxygen. Understanding the physics and significance of M27 was well beyond 18th century science. Even today, many things remain mysterious about bipolar planetary nebula like M27, including the physical mechanism that expels a low-mass star's gaseous outer-envelope, leaving an X-ray hot white dwarf.   APOD across world languages: Arabic, Catalan, Chinese (Beijing), Chinese (Taiwan), Croatian, Czech, Dutch, Farsi, French, French, German, Hebrew, Indonesian, Japanese, Korean, Montenegrin, Polish, Russian, Serbian, Slovenian,  Spanish and Ukrainian",
                        hdImageUrl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg",
                        mediaType = "image",
                        serviceVersion = "v1",
                        title = "M27: The Dumbbell Nebula",
                        thumbnailImageUrl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
                    )
                ).sortedByDescending { it.strDate.toDateOrNull() }
            )

            //Actual
            val actualResult = useCase.invoke()

            //Assertion
            Assert.assertEquals(expectedResult, actualResult)
        }
    }

    /**
     * Test 2
     */
    @Test
    fun testPhotoList_isSortedDateDescending_ReturnsListAscending() {
        val useCase =
            FetchSortedListOfPhotosUseCase(repository = getRepositoryImpl("photos_list_normal.json"))
        runBlocking {
            //Expected
            val expectedResult = DataResult.Success(
                data = listOf(
                    PhotoEntity(
                        copyright = "ESA/HubbleNASA",
                        strDate = "2019-12-01",
                        explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
                        hdImageUrl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                        mediaType = "image",
                        serviceVersion = "v1",
                        title = "Starburst Galaxy M94 from Hubble",
                        thumbnailImageUrl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
                    ),
                    PhotoEntity(
                        copyright = "Steve Mazlin",
                        strDate = "2019-12-03",
                        explanation = "Is this what will become of our Sun? Quite possibly.  The first hint of our Sun's future was discovered inadvertently in 1764. At that time, Charles Messier was compiling a list of diffuse objects not to be confused with comets. The 27th object on Messier's list, now known as M27 or the Dumbbell Nebula, is a planetary nebula, the type of nebula our Sun will produce when nuclear fusion stops in its core. M27 is one of the brightest planetary nebulae on the sky, and can be seen toward the constellation of the Fox (Vulpecula) with binoculars. It takes light about 1000 years to reach us from M27, featured here in colors emitted by hydrogen and oxygen. Understanding the physics and significance of M27 was well beyond 18th century science. Even today, many things remain mysterious about bipolar planetary nebula like M27, including the physical mechanism that expels a low-mass star's gaseous outer-envelope, leaving an X-ray hot white dwarf.   APOD across world languages: Arabic, Catalan, Chinese (Beijing), Chinese (Taiwan), Croatian, Czech, Dutch, Farsi, French, French, German, Hebrew, Indonesian, Japanese, Korean, Montenegrin, Polish, Russian, Serbian, Slovenian,  Spanish and Ukrainian",
                        hdImageUrl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg",
                        mediaType = "image",
                        serviceVersion = "v1",
                        title = "M27: The Dumbbell Nebula",
                        thumbnailImageUrl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
                    )
                ).sortedBy { it.strDate.toDateOrNull() }
            )

            //Actual
            val actualResult = useCase.invoke()

            //Assertion
            Assert.assertNotEquals(expectedResult, actualResult)
        }
    }

    /**
     * Test 3
     */
    @Test
    fun testPhotoList_isInvalidJsonError_ReturnsFailureJson() {
        val useCase =
            FetchSortedListOfPhotosUseCase(repository = getRepositoryImpl("photos_list_invalidJson.json"))
        runBlocking {
            //Expected
            val expectedResult = DataResult.Failure(
                failure = DataError.JsonDataError
            )

            //Actual
            val actualResult = useCase.invoke()

            //Assertion
            Assert.assertEquals(expectedResult, actualResult)
        }
    }

}