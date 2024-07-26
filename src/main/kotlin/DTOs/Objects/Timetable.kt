package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Primitives.Schedule
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.DateSerializer
import java.util.*

/// Пространство имен `Timetable` содержит типы данных для взаимодействия с информацией о салонах красоты.
///
/// Включает параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// используемые для обработки данных о салонах в системе.
object Timetable {
    // MARK: - Parameters -
    data object Parameters {
        /// Параметры запроса для поиска доступных временных слотов для процедур.
        /// Учитывает часовой пояс и список запрашиваемых процедур.
        ///
        /// ### Properties:
        /// - `procedures`: Список идентификаторов процедур, для которых ищутся слоты.
        @Serializable
        data class SearchSlot(
            var procedures: List<@Serializable(UUIDSerializer::class) UUID>
        ) : Parametable
    }

    // MARK: - Responses -
    data object Responses {
        /// Структура ответа, возвращающая доступные временные слоты.
        /// Представляет доступные интервалы для записи к мастеру или в салоне на ближайшие дни.
        ///
        /// ### Properties:
        /// - `days`: Словарь, сопоставляющий даты с массивами доступных временных интервалов.
        @Serializable
        data class Slot(
            var intervals: Map<@Serializable(DateSerializer::class) Date, List<DateInterval>>,
            var timeZone: String
        ) : Responsable


        @Serializable
        data class Week(
            var schedule: Schedule.Week,
            var timeZone: String
        ) : Parametable, Responsable
    }
}
