package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.polymorphic
import org.maestri.DTOs.Enums.utils.PolymorphSerializable
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import java.util.*

/// Пространство имен `Device` содержит типы данных для работы с информацией о клиентах.
///
/// В него входят  модели ответов (`Responses`),
/// используемые для обмена данными между клиентом и сервером в контексте клиентских данных.
object Device {
    // MARK: - Parameters -
    data object Parameters {

        @Serializable
        /* data */ class Create : Parametable

        @Serializable
        data class RegisterPush(
            val token: Token
        ) : Parametable {
            @Serializable
            sealed class Token {
                companion object : PolymorphSerializable {
                    override val module: SerializersModuleBuilder.() -> Unit = {
                        polymorphic(Token::class) {
                            subclass(Apple::class, Apple.serializer())
                            subclass(Fcm::class, Fcm.serializer())
                        }
                    }
                }

                @Serializable
                data class Apple(
                    val token: String
                ) : Token()

                @Serializable
                data class Fcm(
                    val token: String
                ) : Token()
            }
        }
    }

    // MARK: - Responses -
    data object Responses {
        @Serializable
        data class Full(
            @Serializable(with = UUIDSerializer::class) var id: UUID
        ) : Responsable
    }
}


