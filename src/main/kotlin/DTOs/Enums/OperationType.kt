package org.maestri.DTOs.Enums

import kotlinx.serialization.SerialName

enum class OperationType {
    @SerialName("cashIn")
    CASH_IN, // внесение налички
    UNRECORDED,  // неучтенка
    BONUS,  // премия
    SALARY,  // зарплата
    APPOINTMENT //
}
