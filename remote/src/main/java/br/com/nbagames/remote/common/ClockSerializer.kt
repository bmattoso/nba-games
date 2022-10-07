package br.com.nbagames.remote.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ClockSerializer : KSerializer<String?> {

    override val descriptor = PrimitiveSerialDescriptor("Any", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): String? {
        return try {
            decoder.decodeString()
        } catch (ex: SerializationException) {
            null
        }
    }

    override fun serialize(encoder: Encoder, value: String?) {
        encoder.encodeString(value.toString())
    }
}
