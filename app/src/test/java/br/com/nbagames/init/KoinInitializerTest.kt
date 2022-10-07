package br.com.nbagames.init

import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources
import android.os.LocaleList
import com.google.common.truth.Truth.assertThat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.spyk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.test.check.checkModules
import java.util.Locale

internal class KoinInitializerTest {

    private lateinit var koinInitializer: KoinInitializer
    private val firebaseDatabase: FirebaseDatabase = mockk(relaxed = true)
    private val databaseReference: DatabaseReference = mockk(relaxed = true)

    @BeforeEach
    fun setup() {
        mockkStatic(FirebaseDatabase::class)
        every { FirebaseDatabase.getInstance() } answers { firebaseDatabase }
        every { firebaseDatabase.reference } answers { databaseReference }

        koinInitializer = KoinInitializer()
    }

    @Test
    fun `Guarantee modules definitions`() {
        mockkStatic(Application::class)
        val fakeApplication = spyk(FakeApplication())
        mockApplicationLocale(fakeApplication)

        val koinApplication = koinInitializer.create(fakeApplication)
        koinApplication.checkModules()
    }

    @Test
    fun `Check Initializer has no dependencies`() {
        assertThat(koinInitializer.dependencies()).isEmpty()
    }

    private class FakeApplication : Application()

    private fun mockApplicationLocale(application: Application) {
        val resources = mockk<Resources>(relaxed = true)
        val configuration = mockk<Configuration>(relaxed = true)

        every { application.resources } answers { resources }
        every { resources.configuration } answers { configuration }
        every { configuration.locales } answers { LocaleList(Locale.CANADA) }
    }
}
