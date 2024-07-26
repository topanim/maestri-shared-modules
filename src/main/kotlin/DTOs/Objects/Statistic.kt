package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Primitives.Price
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.DateSerializer
import java.util.*

/// Пространство имен `Statistic` содержит типы данных для взаимодействия с информацией о салонах красоты.
///
/// Включает параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// используемые для обработки данных о салонах в системе.
object Statistic {
    // MARK: - Parameters -
    data object Parameters {
        /// Параметры запроса для фильтрации статистики по записям на прием.
        /// Позволяют задать временной диапазон и фильтры для выборки статистических данных.
        ///
        /// ### Properties:
        /// - `startDate`: Начальная дата временного диапазона.
        /// - `endDate`: Конечная дата временного диапазона.
        /// - `employees`: Список идентификаторов сотрудников, для которых необходима статистика.
        /// - `salons`: Список идентификаторов салонов, для которых требуется статистика.
        @Serializable
        data class AppointmentsQuery(
            @Serializable(DateSerializer::class) val startDate: Date,
            @Serializable(DateSerializer::class) val endDate: Date,
            val employees: List<@Serializable(UUIDSerializer::class) UUID>?,
            val salons: List<@Serializable(UUIDSerializer::class) UUID>?,
        ) : Parametable
    }

    // MARK: - Responses -
    data object Responses {

        /// Структура, представляющая статистику по записям на прием.
        /// Включает в себя информацию о суммарной стоимости и количестве записей.
        ///
        /// ### Properties:
        /// - `price`: Общая сумма стоимости всех записей в заданном фильтре.
        /// - `count`: Общее количество записей на прием в заданном временном диапазоне и согласно указанным фильтрам.
        @Serializable
        data class Appointments(
            var price: Price,
            var count: Int
        ) : Responsable
    }
}
