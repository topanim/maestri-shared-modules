package org.maestri.DTOs.Enums

import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.polymorphic
import org.maestri.DTOs.Enums.utils.PolymorphSerializable
import org.maestri.DTOs.Objects.Professional
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.URISerializer
import java.net.URI

@Serializable
sealed class ProfessionalEmployee : Responsable {
    companion object : PolymorphSerializable {
        override val module: SerializersModuleBuilder.() -> Unit = {
            polymorphic(ProfessionalEmployee::class) {
                subclass(Link::class, Link.serializer())
                subclass(Value::class, Value.serializer())
            }
        }
    }

    @Serializable
    data class Link(
        @Serializable(URISerializer::class) val url: URI
    ) : ProfessionalEmployee()

    @Serializable
    data class Value(
        val user: Professional.Responses.Partial
    ) : ProfessionalEmployee()
}


