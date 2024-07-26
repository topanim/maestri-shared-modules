package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Enums.ServiceCategory
import org.maestri.DTOs.Primitives.Price
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import java.util.*

/// Пространство имен `Service` содержит типы данных для взаимодействия с информацией о салонах красоты.
///
/// Включает параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// используемые для обработки данных о салонах в системе.
object Service {
    // MARK: - Parameters -
    data object Parameters {
        /// Параметры запроса для получения списка услуг.
        /// Могут включать фильтрацию и сортировку, но в данном примере не определены.
        @Serializable
        data class Retrieve(
            val salons: List<@Serializable(UUIDSerializer::class) UUID>?,
            val employees: List<@Serializable(UUIDSerializer::class) UUID>?,
            val value: String?,
            val page: Int,
            val per: Int,
        ) : Parametable

        @Serializable
        data class RetrieveFull(
            @Serializable(UUIDSerializer::class) val salon: UUID?
        ) : Parametable

        /// Параметры, передаваемые в теле запроса при создании новой услуги.
        /// Требуют указания обязательных полей для регистрации услуги в системе.
        ///
        /// ### Properties:
        /// - `title`: Название услуги.
        /// - `description`: Описание услуги, детализирующее предоставляемую процедуру.
        /// - 'language': Язык в стиле Access language
        @Serializable
        data class Create(
            var title: String,
            var description: String,
            var category: ServiceCategory,
            var languageval: String,
        ) : Parametable

        /// Параметры, передаваемые в теле запроса для частичного обновления данных об услуге.
        /// Позволяют изменить название и/или описание существующей услуги.
        ///
        /// ### Properties:
        /// - `title`: Новое название услуги (опционально).
        /// - `description`: Новое описание услуги (опционально).
        /// - 'language': Язык в стиле Access language (требуется для обновления title || description)
        @Serializable
        data class Patch(
            var title: String?,
            var description: String?,
            var category: ServiceCategory?,
            var language: String,
        ) : Parametable
    }

    // MARK: - Responses -
    data object Responses {
        /// Полная структура ответа, содержащая все данные об услуге.
        /// Используется для передачи детальной информации о конкретной услуге.
        ///
        /// ### Properties:
        /// - `id`: Уникальный идентификатор услуги.
        /// - `title`: Название услуги.
        /// - `description`: Подробное описание услуги.
        @Serializable
        data class Full(
            @Serializable(UUIDSerializer::class) var id: UUID,
            var title: String,
            var description: String,
            var category: ServiceCategory,
            var procedures: List<Procedure.Responses.Partial>,
        ) : Responsable

        /// Упрощенная структура ответа для услуг.
        /// Может использоваться для передачи краткой информации об услугах в списках или при кратком обзоре.
        ///
        /// ### Properties:
        /// - `id`: Уникальный идентификатор услуги.
        /// - `title`: Название услуги.
        /// - `description`: Описание услуги, содержащее основные характеристики.
        @Serializable
        data class Partial(
            @Serializable(UUIDSerializer::class) var id: UUID,
            var title: String,
            var description: String,
            var category: ServiceCategory,
            var minPrice: Price?,
            var minDuration: Int?,
        ) : Responsable
    }
}