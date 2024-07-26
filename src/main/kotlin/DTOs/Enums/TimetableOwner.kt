package org.maestri.DTOs.Enums

import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.polymorphic
import org.maestri.DTOs.Enums.utils.PolymorphSerializable
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import java.util.*

@Serializable
sealed class TimetableOwner : Parametable, Responsable {
    companion object : PolymorphSerializable {
        override val module: SerializersModuleBuilder.() -> Unit = {
            polymorphic(TimetableOwner::class) {
                subclass(Salon::class, Salon.serializer())
                subclass(Employee::class, Employee.serializer())
            }
        }
    }

    var description: String = when (this) {
        is Employee -> "employee:$id"
        is Salon -> "salon:$id"
    }

    @Serializable
    data class Salon(val id: UUID) : TimetableOwner()

    @Serializable
    data class Employee(val id: UUID) : TimetableOwner()
}
