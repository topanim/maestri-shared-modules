package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Enums.NoticeCategory
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.DateSerializer
import java.util.*

/// Пространство имен `Notice` содержит типы данных для работы с информацией о клиентах.
///
/// В него входят  модели ответов (`Responses`),
/// используемые для обмена данными между клиентом и сервером в контексте клиентских данных.
object Notice {
    // MARK: - Responses -
    data object Responses {
        /// Полная информация об уведомлении.
        /// Включает в себя всю необходимую информацию об уведомлении, которое может быть представлено пользователю.
        ///
        /// ### Properties:
        /// - `id`: Уникальный идентификатор уведомления.
        /// - `title`: Заголовок уведомления, предоставляющий краткую суть сообщения.
        /// - `body`: Основное содержимое уведомления, которое переносит детальную информацию.
        /// - `date`: Дата и время создания уведомления, может быть `nil`, если дата не предоставляется.
        @Serializable
        data class Full(
            @Serializable(with = UUIDSerializer::class) var id: UUID,
            var titleKey: String,
            var messageKey: String,
            var parameters: String?,
            var category: NoticeCategory,
            var isRead: Boolean,
            @Serializable(with = DateSerializer::class) var date: Date?,
        ) : Responsable
    }
}
