package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Enums.SalonType
import org.maestri.DTOs.Primitives.Address
import org.maestri.DTOs.Primitives.Schedule
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.URISerializer
import java.net.URI
import java.util.*

/// Пространство имен `Salon` содержит типы данных для взаимодействия с информацией о салонах красоты.
///
/// Включает параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// используемые для обработки данных о салонах в системе.
object Salon {
    //MARK: - Parameters -
    data object Parameters {
        /// Параметры для создания салона.
        /// Передаются в теле запроса при регистрации нового салона в системе.
        @Serializable
        data class Create(
            val nameval: String,
            val typeval: SalonType,
            @Serializable(URISerializer::class) val logoval: URI?,
            val timeZoneval: String,
            val descriptionval: String?,
            val timetableval: Schedule.Week,
            val addressval: Address,
            var contacts: List<Contact.Parameters.Create>,
        ) : Parametable

        /// Параметры для обновления информации о салоне.
        /// Передаются в теле запроса при изменении данных салона.
        ///
        /// ### Properties:
        /// - name: Новое название салона, если требуется обновление.
        /// - logo: Новый URL адрес логотипа, если требуется обновление.
        @Serializable
        data class Patch(
            val name: String?,
            val type: SalonType?,
            val description: String?,
            @Serializable(URISerializer::class) val logo: URI?,
            val timetable: Schedule.Week?,
        ) : Parametable
    }

    //MARK: - Responses -
    data object Responses {

        /// Полная информация о салоне для подробного отображения.
        ///
        /// ### Properties:
        /// - id: Уникальный идентификатор салона.
        /// - name: Название салона.
        /// - type: Тип салона, определенный перечислением `SalonType`.
        /// - logo: URL адрес логотипа салона, может быть `nil`.
        /// - address: Полная информация об адресе (`Address.Responses.Full`).
        /// - canEdit: Флаг, указывающий на возможность редактирования информации о салоне текущим пользователем.
        /// - isFavorite: Флаг, указывающий, отмечен ли салон как избранный у текущего пользователя.
        /// - timetable: Полная информация о расписании салона (`Timetable.Responses.Full`).
        /// - masters: Опциональный список мастеров салона с полной информацией (`Employee.Responses.Full`).
        @Serializable
        data class Full(
            @Serializable(UUIDSerializer::class) var id: UUID,
            var name: String,
            var type: SalonType,
            var description: String?,
            @Serializable(URISerializer::class) var logo: URI?,
            var address: Address,
            var isActive: Boolean,
            var canEdit: Boolean = false,
            var isFavorite: Boolean = false,
            var timetable: Timetable.Responses.Week,
            var masters: List<Employee.Responses.Full>?,
        ) : Responsable

        /// Упрощенная информация о салоне для краткого отображения.
        ///
        /// ### Properties:
        /// - id: Уникальный идентификатор салона.
        /// - name: Название салона.
        /// - type: Тип салона, определенный перечислением `SalonType`.
        /// - logo: URL адрес логотипа салона, может быть `nil`.
        /// - address: Краткая информация об адресе или просто строка адреса.
        /// - isFavorite: Флаг, указывающий, отмечен ли салон как избранный у текущего пользователя.
        @Serializable
        data class Partial(
            @Serializable(UUIDSerializer::class) var id: UUID,
            var name: String,
            var type: SalonType,
            @Serializable(URISerializer::class) var logo: URI?,
            var address: Address, // Может быть можно просто строку присылать, но тогда не понятно как на карте отображать
            var isFavorite: Boolean = false
        ) : Responsable
    }
}

