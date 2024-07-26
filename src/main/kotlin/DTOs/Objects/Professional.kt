package org.maestri.DTOs.Objects

import kotlinx.serialization.Serializable
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import org.maestri.Serializers.URISerializer
import java.net.URI

/// Пространство имен `Professional` содержит типы данных для работы с информацией о клиентах.
///
/// В него входят параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// используемые для обмена данными между клиентом и сервером в контексте клиентских данных.
object Professional {
    //MARK: - Parameters -
    data object Parameters {
        @Serializable
        /* data */ class Create : Parametable
    }

    //MARK: - Responses -
    data object Responses {
        @Serializable
        data class Partial(
            @Serializable(URISerializer::class) var avatar: URI?,
            var nickname: String
        ) : Responsable
    }
}
