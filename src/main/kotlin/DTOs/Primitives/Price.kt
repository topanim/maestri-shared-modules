package org.maestri.DTOs.Primitives

import kotlinx.serialization.Serializable
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable

@Serializable
data class Price(
    var amount: Float,
    var currency: String
): Parametable, Responsable
