package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Primitives.Price
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import java.util.*

/// Пространство имен `Procedure` содержит типы данных для работы с информацией о сотрудниках.
///
/// Включает в себя параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// которые применяются для обмена данными о сотрудниках между клиентскими приложениями и сервером.
object Procedure {
    // MARK: - Parameters -
    data object Parameters {

        /// Параметры запроса для получения списка процедур.
        /// Позволяют фильтровать процедуры по определенным салонам и сотрудникам.
        ///
        /// ### Properties:
        /// - `salons`: Список идентификаторов салонов для фильтрации процедур.
        /// - `employees`: Список идентификаторов сотрудников для фильтрации процедур.
        @Serializable
        data class Retrieve(
            val salons: List<@Serializable(UUIDSerializer::class) UUID>?,
            val employees: List<@Serializable(UUIDSerializer::class) UUID>?
        ) : Parametable

        /// Параметры, передаваемые в теле запроса при создании новой процедуры.
        /// Требуют указания всех необходимых атрибутов процедуры.
        ///
        /// ### Properties:
        /// - `price`: Цена процедуры.
        /// - `duration`: Продолжительность процедуры в минутах.
        /// - `serviceId`: Идентификатор услуги, к которой относится процедура.
        /// - `employeeId`: Идентификатор работника, к которому привяжится процедура.
        @Serializable
        data class Create(
            val price: Price,
            val duration: Int,
            var description: String?,
            var alias: String?,
            @Serializable(UUIDSerializer::class) val serviceId: UUID,
            @Serializable(UUIDSerializer::class) val employeeId: UUID
        ) : Parametable

        /// Параметры, передаваемые в теле запроса для частичного обновления существующей процедуры.
        /// Позволяют изменить цену и/или продолжительность процедуры.
        ///
        /// ### Properties:
        /// - `price`: Новая цена процедуры (опционально).
        /// - `duration`: Новая продолжительность процедуры в минутах (опционально).
        @Serializable
        data class Patch(
            val priceval: Price?,
            val durationval: Int?,
            var description: String?,
            var alias: String?
        ) : Parametable
    }

    // MARK: - Responses -
    data object Responses {

        /// Полная структура ответа, содержащая все данные о процедуре.
        /// Используется для передачи полных деталей о процедуре, включая связанную услугу.
        ///
        /// ### Properties:
        /// - `id`: Уникальный идентификатор процедуры.
        /// - `price`: Цена процедуры.
        /// - `duration`: Продолжительность процедуры в минутах.
        /// - `service`: Полные данные о связанной услуге.
        @Serializable
        data class Full(
            @Serializable(UUIDSerializer::class) var id: UUID,
            var price: Price,
            var duration: Int,
            var description: String?,
            var alias: String?,
            var service: Service.Responses.Partial,
            var masterval: Employee.Responses.Partial
        ) : Responsable

        /// Упрощенная структура ответа для процедур.
        /// Может использоваться для передачи краткой информации о процедуре, включая основные данные связанной услуги.
        ///
        /// ### Properties:
        /// - `id`: Уникальный идентификатор процедуры.
        /// - `price`: Цена процедуры.
        /// - `duration`: Продолжительность процедуры в минутах.
        /// - `service`: Краткие данные о связанной услуге.
        @Serializable
        data class Partial(
            @Serializable(UUIDSerializer::class) var id: UUID,
            var price: Price,
            var duration: Int,
            var description: String?,
            var alias: String?,
            var service: Service.Responses.Partial,
            var master: Employee.Responses.Partial,
        ) : Responsable
    }
}
