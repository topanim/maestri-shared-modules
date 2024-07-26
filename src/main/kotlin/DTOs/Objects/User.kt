package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.OptionSet.MaestriPermissionSet
import org.maestri.DTOs.OptionSet.UserRoleSet
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.URISerializer
import java.net.URI
import java.util.*


/// Пространство имен `User` содержит типы данных для взаимодействия с информацией о салонах красоты.
///
/// Включает параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// используемые для обработки данных о салонах в системе.
object User {
    // MARK: - Parameters -
    data object Parameters {
        /// Параметры для частичного обновления профиля пользователя.
        ///
        /// ### Properties:
        ///   - nickname: Новый псевдоним пользователя, если требуется обновление.
        ///  - avatar: Новая ссылка на изображение аватара пользователя, если требуется обновление.
        ///  - contact: Контактная информация пользователя, для востонавления данных и так далее
        @Serializable
        data class Patch(
            var nickname: String?,
            @Serializable(URISerializer::class) var avatar: URI?,
            var contact: Contact.Parameters.Create?,
        ) : Parametable
    }

    // MARK: - Responses -
    data object Responses {
        /// Полные данные о пользователе для отображения на странице профиля.
        ///
        /// ### Properties:
        ///   - id: Уникальный идентификатор пользователя в системе.
        ///  - avatar: Ссылка на изображение аватара пользователя, может быть не указана.
        ///  - nickname: Отображаемое имя пользователя.
        ///  - contact: Полная контактная информация, ассоциированная с профилем пользователя.
        ///  - options: Роли пользователя, определенные в системе.
        @Serializable
        data class Full(
            @Serializable(UUIDSerializer::class) var id: UUID,
            @Serializable(URISerializer::class) var avatar: URI?,
            var nickname: String,
            var contact: Contact.Responses.Full?,
            var options: UserRoleSet,
            var permissions: MaestriPermissionSet,
        ) : Responsable

        /// Краткие данные о пользователе для отображения на главной странице.
        ///
        /// ### Properties:
        ///  - avatar: Ссылка на изображение аватара пользователя, может быть не указана.
        ///  - nickname: Псевдоним пользователя для представления в пользовательском интерфейсе.
        @Serializable
        data class Partial(
            @Serializable(URISerializer::class) var avatar: URI?,
            var nickname: String
        ) : Responsable
    }
}

