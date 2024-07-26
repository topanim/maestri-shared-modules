package org.maestri.DTOs.Objects.Finance

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Enums.PaymentType
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.DateSerializer
import java.util.*

object Cashbox {
    //MARK: - Parameters -
    data object Parameters {

        @Serializable
        data class Create(
            @Serializable(UUIDSerializer::class) var salonId: UUID,
            var paymentType: PaymentType
        ) : Parametable

        @Serializable
        data class Retrieve(
            var paymentType: PaymentType?,
            @Serializable(DateSerializer::class) var startDate: Date?,
            @Serializable(DateSerializer::class) var endDate: Date?
        ) : Parametable
    }

    //MARK: - Responses -
    data object Responses {

        data class Full(
            var id: UUID,
            var createDate: Date,
            var paymentType: PaymentType
        ) : Responsable
    }
}
