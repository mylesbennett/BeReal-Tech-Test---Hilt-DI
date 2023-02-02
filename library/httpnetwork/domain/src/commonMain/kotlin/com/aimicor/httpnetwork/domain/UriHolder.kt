package com.aimicor.httpnetwork.domain

import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json

@Serializable(with = UriSerializer::class)
data class UriHolder(
    val uri: String
)

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = UriHolder::class)
object UriSerializer : KSerializer<UriHolder> {

    override fun serialize(encoder: Encoder, value: UriHolder) {
        // possibly improve with https://stackoverflow.com/a/72306244/1089629
        encoder.encodeString(Json.encodeToString(value.uri.encodeToByteArray()))
    }

    override fun deserialize(decoder: Decoder): UriHolder =
        // possibly improve with https://stackoverflow.com/a/72306244/1089629
        UriHolder(Json.decodeFromString<ByteArray>(decoder.decodeString()).decodeToString())
}
