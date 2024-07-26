package org.maestri.DTOs.Enums

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.polymorphic
import org.maestri.DTOs.Enums.utils.PolymorphSerializable
import org.maestri.DTOs.Primitives.Price
import java.util.*

enum class PaymentPeriod {
    DAY,
    MONTH,
    HOUR
}

@Serializable
sealed class SalaryPaymentType {
    companion object : PolymorphSerializable {
        override val module: SerializersModuleBuilder.() -> Unit = {
            polymorphic(SalaryPaymentType::class) {
                subclass(Procent::class, Procent.serializer())
                subclass(Value::class, Value.serializer())
            }
        }
    }

    @Serializable
    data class Procent(val procent: UInt) : SalaryPaymentType()

    @Serializable
    data class Value(val value: Price) : SalaryPaymentType()
}

@Serializable
sealed class SalaryType {
    companion object : PolymorphSerializable {
        override val module: SerializersModuleBuilder.() -> Unit = {
            polymorphic(SalaryType::class) {
                subclass(Procent::class, Procent.serializer())
                subclass(Grid::class, Grid.serializer())
                subclass(Wage::class, Wage.serializer())
                subclass(None::class, None.serializer())
            }
        }
    }

    @Serializable
    data class Procent(
        val value: UInt
    ) : SalaryType()

    @Serializable
    data class Grid(
        val proceduresMap: Map<@Serializable(UUIDSerializer::class) UUID, SalaryPaymentType>
    ) : SalaryType()

    @Serializable
    data class Wage(val price: Price, val period: PaymentPeriod) : SalaryType()

    @Serializable
    data object None : SalaryType()
}

