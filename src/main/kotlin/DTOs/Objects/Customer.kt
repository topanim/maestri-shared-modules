package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Enums.CustomerUser
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import java.util.*

/// Пространство имен `Customer` содержит типы данных для работы с информацией о клиентах.
///
/// В него входят параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// используемые для обмена данными между клиентом и сервером в контексте клиентских данных.
object Customer {
    //MARK: - Parameters -
    data object Parameters {

        @Serializable
        data class Registration(
            var contacts: List<Contact.Parameters.Create>
        ) : Parametable

        @Serializable
        data class Create(
            @Serializable(with = UUIDSerializer::class) var salonId: UUID?,
            var alias: String,
            var contacts: List<Contact.Parameters.Create>
        ) : Parametable

        /// Параметры запроса для получения списка клиентов.
        /// Позволяют фильтровать клиентов по определенным салонам.
        ///
        /// ### Properties:
        /// - `employees`: Список идентификаторов employees для фильтрации клиентов.
        @Serializable
        data class Retrieve(
            val salons: List<@Serializable(UUIDSerializer::class) UUID>?,
            val employees: List<@Serializable(UUIDSerializer::class) UUID>?,
        ) : Parametable


        /// Параметры для частичного обновления профиля пользователя.
        ///
        /// ### Properties:
        ///  - contacts: Контактная информация Customer, для связи с ним
        @Serializable
        data class Patch(
            var contacts: List<Contact.Parameters.Create>
        ) : Parametable

        /// Параметры для того, чтобы принять приглашение в приложение.
        ///
        /// ### Properties:
        ///  - contact: value Контакта для верификации
        @Serializable
        data class HandleInvite(
            var contact: String?
        ) : Parametable
    }

    //MARK: - Responses -
    data object Responses {
        @Serializable
        data class Full(
            @Serializable(with = UUIDSerializer::class) var id: UUID,
            var user: CustomerUser,
            var alias: String?,
            var contacts: List<Contact.Responses.Full>
        ) : Responsable

        @Serializable
        data class Partial(
            @Serializable(with = UUIDSerializer::class) var id: UUID,
            var user: CustomerUser,
            var alias: String?,
            var contacts: List<Contact.Responses.Full>
        ) : Responsable

        @Serializable
        data class Verify(
            public var contacts: List<Contact.Responses.Full>
        ) : Responsable
    }
}
