package org.maestri.DTOs.Enums

import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.polymorphic
import org.maestri.DTOs.Enums.utils.PolymorphSerializable
import org.maestri.DTOs.Objects.User
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.DateSerializer
import org.maestri.Serializers.URISerializer
import java.net.URI
import java.net.URL

@Serializable
sealed class CustomerUser : Responsable {
    companion object : PolymorphSerializable {
        override val module: SerializersModuleBuilder.() -> Unit = {
            polymorphic(CustomerUser::class) {
                subclass(Link::class, Link.serializer())
                subclass(Value::class, Value.serializer())
            }
        }
    }

    @Serializable
    data class Link(
        @Serializable(URISerializer::class) val url: URI
    ) : CustomerUser()

    @Serializable
    data class Value(val customer: User.Responses.Partial) : CustomerUser()
}


