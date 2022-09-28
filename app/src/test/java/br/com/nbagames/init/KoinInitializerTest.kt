package br.com.nbagames.init

import android.app.Application
import com.google.common.truth.Truth.assertThat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.test.check.checkModules

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
        val koinApplication = koinInitializer.create(FakeApplication())
        koinApplication.checkModules()
    }

    @Test
    fun `Check Initializer has no dependencies`() {
        assertThat(koinInitializer.dependencies()).isEmpty()
    }

    private class FakeApplication : Application()
}
