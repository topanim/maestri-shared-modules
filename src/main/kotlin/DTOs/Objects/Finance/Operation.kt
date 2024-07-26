package org.maestri.DTOs.Objects.Finance

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Enums.OperationType
import org.maestri.DTOs.Enums.PaymentType
import org.maestri.DTOs.Primitives.Price
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.DateSerializer
import java.util.*

object Operation {
    //MARK: - Parameters -
    data object Parameters {
        @Serializable
        data class Create(
            var price: Price,
            var operationType: OperationType,
            var paymentType: PaymentType,
            @Serializable(UUIDSerializer::class) var salonId: UUID
        ) : Parametable

        @Serializable
        data class Retrieve(
            @Serializable(DateSerializer::class) var startDate: Date?,
            @Serializable(DateSerializer::class) var endDate: Date?,
            var paymentType: PaymentType?,
            var cashboxIds: List<@Serializable(UUIDSerializer::class) UUID>?
        ) : Parametable
    }

    //MARK: - Responses -
    data object Responses {
        @Serializable
        data class Full(
            @Serializable(UUIDSerializer::class) var id: UUID,
            @Serializable(DateSerializer::class) var createDate: Date,
            var price: Price,
            var paymentType: PaymentType
        ) : Responsable
    }
}
