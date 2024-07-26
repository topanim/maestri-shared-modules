package org.maestri.DTOs.Objects

import kotlinx.serialization.Serializable
import org.maestri.Serializers.DateSerializer
import java.util.*

@Serializable
data class DateInterval(
    val duration: Int,
    @Serializable(DateSerializer::class) val start: Date

)