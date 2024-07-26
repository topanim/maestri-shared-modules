package org.maestri.DTOs.Enums

import kotlinx.serialization.Serializable

/// `AppointmentStatus` описывает состояние appoinment
///
/// Используется для понимание кто отклонил или подтвердил
/// Так же отображает состояние успешно ли завершился или провалился
@Serializable
enum class AppointmentStatus {
    /// Этот статус ожидает подтверждения либо перехода по ссылки аттача customer и approve
    //    @SerialName("requested")
    REQUESTED,
    /// Те кто подтвердили запись: `customer_approved` или `master_approved` или `both_approved`
    /// Так же этот статус подойдёт для отображение того
    /// что запись завершена успешно если `approved_all` и время процедуры уже прошло

    //    @SerialName("customer_approved")
    CUSTOMER_APPROVED,

    //    @SerialName("master_approved")
    MASTER_APPROVED,

    //    @SerialName("both_approved")
    BOTH_APPROVED,

    /// Те кто отклонил запись: `customer_declined` или `master_declined`
    /// Так же этот статус подойдёт для отображение того
    /// что кто то не пришёл на процедуру и время процедуры уже прошло
//    @SerialName("customer_declined")
    CUSTOMER_DECLINED,

    //    @SerialName("master_declined")
    MASTER_DECLINED,
}
