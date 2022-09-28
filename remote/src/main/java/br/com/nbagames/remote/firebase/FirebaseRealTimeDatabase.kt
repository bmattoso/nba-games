package br.com.nbagames.remote.firebase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseRealTimeDatabase(
    val database: DatabaseReference
) {

    suspend inline fun <reified T> getJsonNode(nodeKey: String, childKey: String): T? {
        try {
            val dataSnapshot = withContext(IO) { database.child(nodeKey).child(childKey).awaitGet() }
            return dataSnapshot.getValue(T::class.java)
        } catch (e: Exception) {
            Log.e("FIRESTORE_ERROR", e.toString(), e)
        }
        return null
    }

    suspend fun DatabaseReference.awaitGet(): DataSnapshot = suspendCoroutine { continuation ->
        this.get().addOnFailureListener { exception ->
            continuation.resumeWithException(exception)
        }.addOnSuccessListener { dataSnapshot ->
            continuation.resume(dataSnapshot)
        }
    }
}
