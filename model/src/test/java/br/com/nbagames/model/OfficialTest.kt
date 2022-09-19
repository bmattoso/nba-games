package br.com.nbagames.model

import android.util.Log
import android.webkit.URLUtil
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class OfficialTest {

    @Test
    fun `Is Valid all Official StoragePath`() {
        Official.values().forEach { official: Official ->
            val isOfficialStoragePathValid = URLUtil.isValidUrl(official.getStoragePath())
            Log.i("Official Name Url Testing", official.id)
            assertThat(isOfficialStoragePathValid).isTrue()
        }
    }

    @Test
    fun `Convert Scott Foster into Official`() {
        val official = "Scott Foster".toOfficial()
        assertThat(official).isEqualTo(Official.ScottFoster)
    }

    @Test
    fun `Convert not valid official name into null`() {
        val official = "Bruno Mattoso".toOfficial()
        assertThat(official).isNull()
    }
}
