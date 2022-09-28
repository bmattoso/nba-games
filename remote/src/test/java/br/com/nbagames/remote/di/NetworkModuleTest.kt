package br.com.nbagames.remote.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.test.check.checkModules

class NetworkModuleTest {

    private lateinit var koinApplication: KoinApplication
    private val firebaseDatabase: FirebaseDatabase = mockk(relaxed = true)
    private val databaseReference: DatabaseReference = mockk(relaxed = true)

    @ExperimentalSerializationApi
    @BeforeEach
    fun setup() {
        mockkStatic(FirebaseDatabase::class)
        every { FirebaseDatabase.getInstance() } answers { firebaseDatabase }
        every { firebaseDatabase.reference } answers { databaseReference }

        koinApplication = startKoin {
            modules(
                NetworkModule.getNetworkModule("https://api-nba-v1.p.rapidapi.com/", "", "")
            )
        }
    }

    @Test
    fun `Should create all dependencies from networkModule`() {
        koinApplication.checkModules()
    }
}
