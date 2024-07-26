package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import java.util.*

/// Пространство имен `Offtime` содержит типы данных для взаимодействия с информацией о салонах красоты.
///
/// Включает параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// используемые для обработки данных о салонах в системе.
object Offtime {
    // MARK: - Parameters -
    data object Parameters {
        /// Параметры для определения временных промежутков, когда услуги не будут доступны.
        /// Используется для учета периодов отгулов, отпусков и других нерабочих интервалов.
        @Serializable
        data class Create(
            var interval: DateInterval,
            var reason: String?,
            var timeZone: String
        ): Parametable
    }

    // MARK: - Responses -
    data object Responses {

        /// Структура полного ответа, содержащая расписание работы на неделю.
        /// Включает статус работы и расписание по дням недели в текстовом формате.
        @Serializable
        data class Full(
            @Serializable(with = UUIDSerializer::class) var id: UUID,
            var interval: DateInterval,
            var reason: String?,
            var timeZone: String
        ): Responsable

        @Serializable
        data class Partial(
            @Serializable(with = UUIDSerializer::class) var id: UUID,
            var interval: DateInterval
        ) : Responsable
    }
}
