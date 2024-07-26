package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Enums.AppointmentStatus
import org.maestri.DTOs.Primitives.Address
import org.maestri.DTOs.Primitives.Price
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.DateSerializer
import java.util.*

/// Пространство имен `Appointment` содержит типы данных для работы с записями на прием.
///
/// В него входят как параметры для запросов (`Parameters`), так и модели ответов (`Responses`),
/// которые используются для сериализации данных, отправляемых и получаемых от API.
object Appointment {
    //MARK: - Parameters -
    data object Parameters {

        /// Параметры запроса `Retrieve` определяют фильтры для получения записей на прием
        /// за определенный временной интервал с возможной фильтрацией по сотрудникам и салонам.
        ///
        /// ### Properties:
        ///   - startDate: `Date?` - начало временного интервала для выборки данных.
        ///  - endDate: `Date?` - конец временного интервала для выборки данных.
        ///  - employees: `[UUID]?` - необязательный массив идентификаторов сотрудников для фильтрации.
        ///  - salons: `[UUID]?` - необязательный массив идентификаторов салонов для фильтрации.
        /// Если массив не предоставлен, выборка осуществляется по всем сущностям.
        @Serializable
        data class Retrieve(
            @Serializable(with = DateSerializer::class) val startDate: Date?,
            @Serializable(DateSerializer::class) val endDate: Date?,
            val employees: List<@Serializable(UUIDSerializer::class) UUID>?,
            val salons: List<@Serializable(UUIDSerializer::class) UUID>?,
            @Serializable(with = UUIDSerializer::class) val customer: UUID?
        ) : Parametable

        /// `Create` описывает параметры тела запроса для создания новой записи на прием.
        ///
        /// ### Properties:
        ///   - salon: `UUID` - идентификатор салона.
        ///  - master: `UUID` - идентификатор мастера. Если нету то значит Appointment открыт к подтверждению любым клиентом у которого будет доступ к ссылке
        ///  - customer: `UUID?` - идентификатор клиента.
        ///  - procedures: `[UUID]` - массив идентификаторов процедур.
        ///  - time: `Interval` - временной интервал записи на прием.
        ///  - price: `Price` - цена записи на прием.
        ///  - address: `UUID` - идентификатор адреса салона.
        @Serializable
        data class Create(
            @Serializable(with = UUIDSerializer::class) val masterId: UUID,
            @Serializable(with = UUIDSerializer::class) val customerId: UUID?,
            val proceduresId: List<@Serializable(with = UUIDSerializer::class) UUID>,
            val time: DateInterval
        ) : Parametable

        /// `Patch` определяет параметры для частичного обновления данных записи на прием.
        ///
        /// ### Properties:
        ///   - time: ``Interval?`` - новый временной интервал для записи, если требуется изменение.
        ///  - price: ``Price?`` - новая цена для записи, если требуется изменение.
        ///  - procedures: `[UUID]?` - новый список идентификаторов процедур, если требуется изменение.
        @Serializable
        data class Patch(
            val time: DateInterval? = null,
            val price: Price? = null,
            val proceduresId: List<@Serializable(with = UUIDSerializer::class) UUID>? = null,
        ) : Parametable
    }

    //MARK: - Responses -
    data object Responses {
        /// `Full` представляет полную информацию о записи на прием, включая все связанные данные.
        ///
        /// ### Properties:
        ///   - id: `UUID` - уникальный идентификатор записи на прием.
        ///  - salon: ``Salon.Responses.Partial`` - информация о салоне.
        ///  - customer: ``Customer.Responses.Partial`` - информация о клиенте.
        ///  - customerLink: ``URL`` - ссылка на назначения клиента.
        ///  - master: ``Employee.Responses.Partial`` - информация о мастере.
        ///  - procedures: ``[Procedure.Responses.Partial]`` - список процедур.
        ///  - time: ``Interval`` - временной интервал записи.
        ///  - price: ``Price`` - цена записи.
        ///  - address: ``Address.Responses.Full`` - полная информация об адресе салона.
        @Serializable
        data class Full(
            @Serializable(with = UUIDSerializer::class) var id: UUID,
            var status: AppointmentStatus,
            var salon: Salon.Responses.Partial,
            var customer: Customer.Responses.Partial,
            var master: Employee.Responses.Partial,
            var procedures: List<Procedure.Responses.Partial>,
            var time: DateInterval,
            var price: Price,
            var address: Address,
        ) : Responsable

        /// `Partial` содержит частичную информацию о записи на прием, обычно используемую для списков и обзоров.
        ///
        /// ### Properties:
        ///   - id: `UUID` - уникальный идентификатор записи.
        ///  - customer: ``Customer.Responses.Partial`` - информация о клиенте.
        ///  - master: ``Employee.Responses.Partial`` - информация о мастере.
        ///  - time: ``Interval`` - временной интервал записи.
        ///  - price: ``Price`` - цена записи.

        @Serializable
        data class Partial(
            @Serializable(with = UUIDSerializer::class) var id: UUID,
            var status: AppointmentStatus,
            var customer: Customer.Responses.Partial,
            var master: Employee.Responses.Partial,
            var time: DateInterval,
            var price: Price,
            var procedures: String
        ) : Responsable
    }
}
