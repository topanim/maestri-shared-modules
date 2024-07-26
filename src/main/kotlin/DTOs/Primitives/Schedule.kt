package org.maestri.DTOs.Primitives

import kotlinx.serialization.Serializable
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable

object Schedule {

    /// WorkSchedule: расписание которое содержит в себе одну неделю
    @Serializable
    data class Week(
        var monday: Day? = null,
        var tuesday: Day? = null,
        var wednesday: Day? = null,
        var thursday: Day? = null,
        var friday: Day? = null,
        var saturday: Day? = null,
        var sunday: Day? = null
    ) : Parametable, Responsable

    /// DaySchedule: модель одного дня в расписании
    @Serializable
    data class Day(
        var workTime: String,
        var offTime: List<String>
    ) : Parametable, Responsable
}
