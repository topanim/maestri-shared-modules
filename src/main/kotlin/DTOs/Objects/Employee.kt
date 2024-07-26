package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Enums.PaymentType
import org.maestri.DTOs.Enums.ProfessionalEmployee
import org.maestri.DTOs.Primitives.Schedule
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.URISerializer
import java.net.URI
import java.util.*


/// Пространство имен `Employee` содержит типы данных для работы с информацией о сотрудниках.
///
/// Включает в себя параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// которые применяются для обмена данными о сотрудниках между клиентскими приложениями и сервером.
object Employee {
    //MARK: - Parameters -
    data object Parameters {

        /// Параметры запроса для получения списка сотрудников.
        /// Позволяют фильтровать сотрудников по определенным салонам.
        ///
        /// ### Properties:
        /// - `salons`: Список идентификаторов салонов для фильтрации сотрудников.
        @Serializable
        data class Retrieve(
            val salonsId: List<@Serializable(UUIDSerializer::class) UUID>
        ) : Parametable

        /// Параметры, передаваемые в теле запроса при приглашение нового сотрудника.
        ///
        /// ### Properties:
        /// - `salondId`: Идентификатор салона
        @Serializable
        data class Invite(
            @Serializable(with = UUIDSerializer::class) var salondId: UUID,
            @Serializable(with = UUIDSerializer::class) var positionId: UUID,
            var contacts: List<Contact.Parameters.Create>,
            var timetable: Schedule.Week?,
            var description: String?
        ) : Parametable

        /// Параметры для частичного обновления Employee.
        ///
        /// ### Properties:
        ///  - contacts: Контактная информация Employee, для связи с ним
        /// - timetable: Расписание времени работы
        @Serializable
        data class Patch(
            @Serializable(with = UUIDSerializer::class) var positionId: UUID?,
            var contacts: List<Contact.Parameters.Create>?,
            var timetable: Schedule.Week?,
            var description: String?

        ) : Parametable

        @Serializable
        data class Salary(
            var paymentType: PaymentType,
            @Serializable(with = UUIDSerializer::class) var salonId: UUID
        ) : Parametable
    }

    //MARK: - Responses -
    data object Responses {
        /// `Full` возвращает полный набор информации о сотруднике, предназначенный для подробного просмотра.
        ///
        /// ### Properties:
        /// - id: `UUID` - уникальный идентификатор сотрудника.
        /// - nickname: `String` - псевдоним сотрудника.
        /// - avatar: `URL?` - URL-адрес аватара сотрудника, может быть `nil`, если аватар отсутствует.
        /// - contacts: `[Contact.Responses.Full]` - полный список контактной информации сотрудника.
        /// - position: Position.Responses.Full - полная информация о дожности
        /// - procedures: `[Procedure.Responses.Partial]?` - опциональный список процедур, которые сотрудник выполняет.
        @Serializable
        data class Full(
            @Serializable(with = UUIDSerializer::class) var id: UUID,
            var user: ProfessionalEmployee,
            var description: String?,
            var canEdit: Boolean = false,
            var contacts: List<Contact.Responses.Full>,
            var timetable: Timetable.Responses.Week,
            @Serializable(with = UUIDSerializer::class) var salonId: UUID,
            var position: Position.Responses.Full,
            var procedures: List<Procedure.Responses.Partial>?
        ) : Responsable

        /// `Partial` возвращает упрощенную информацию о сотруднике для использования в списках или кратких обзорах.
        ///
        /// ### Properties:
        /// - id: `UUID` - уникальный идентификатор сотрудника.
        /// - name: `String` - имя сотрудника.
        /// - contacts: `[Contact.Responses.Full]` - полный список контактной информации сотрудника.
        @Serializable
        data class Partial(
            @Serializable(with = UUIDSerializer::class) var id: UUID,
            var name: String,
            @Serializable(URISerializer::class) var avatar: URI?,
            var contacts: List<Contact.Responses.Full>
        ) : Responsable
    }
}
