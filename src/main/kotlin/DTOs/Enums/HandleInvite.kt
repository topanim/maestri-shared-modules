package org.maestri.DTOs.Enums

import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.polymorphic
import org.maestri.DTOs.Enums.utils.PolymorphSerializable
import org.maestri.DTOs.Objects.Customer
import org.maestri.DTOs.Protocols.Responsable

@Serializable
sealed class HandleInvite : Responsable {
    companion object : PolymorphSerializable {
        override val module: SerializersModuleBuilder.() -> Unit = {
            polymorphic(HandleInvite::class) {
                subclass(Success::class, Success.serializer())
                subclass(Request::class, Request.serializer())
            }
        }
    }

    @Serializable
    data class Success(
        val customer: Customer.Responses.Full
    ) : HandleInvite()

    @Serializable
    data class Request(
        val verify: Customer.Responses.Verify
    ) : HandleInvite()
}
