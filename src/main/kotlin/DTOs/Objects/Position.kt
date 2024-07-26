package org.maestri.DTOs.Objects

import Serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import org.maestri.DTOs.Enums.SalaryType
import org.maestri.DTOs.OptionSet.PermissionSet
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable
import java.util.*

/// Пространство имен `Position` содержит типы данных для работы с информацией о сотрудниках.
///
/// Включает в себя параметры для запросов (`Parameters`) и модели ответов (`Responses`),
/// которые применяются для обмена данными о сотрудниках между клиентскими приложениями и сервером.
object Position {
    //MARK: - Parameters -
    data object Parameters {
        @Serializable
        data class Create(
            var title: String,
            var permissions: PermissionSet,
            var salary: SalaryType
        ) : Parametable

        @Serializable
        data class Patch(
            var title: String?,
            var permissions: PermissionSet?,
            var salary: SalaryType?
        ) : Parametable
    }

    //MARK: - Responses -
    data object Responses {
        @Serializable
        data class Full(
            @Serializable(UUIDSerializer::class) var id: UUID,
            var title: String,
            var permissions: PermissionSet,
            var salary: SalaryType,
        ) : Responsable
    }
}