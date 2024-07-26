package org.maestri.DTOs.Primitives

import kotlinx.serialization.Serializable
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.DateSerializer
import java.util.*


@Serializable
data class Token(
    var value: String,
    @Serializable(DateSerializer::class) var expiration: Date
) : Responsable