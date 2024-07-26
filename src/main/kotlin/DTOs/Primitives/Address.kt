package org.maestri.DTOs.Primitives

import kotlinx.serialization.Serializable
import org.maestri.DTOs.Protocols.Parametable
import org.maestri.DTOs.Protocols.Responsable

/// Полные данные об адресе для ответа.
/// Структура включает в себя идентификатор и основные данные об адресе, используемые для ответа в API.
///
/// ### Properties:
///  - address: Полное текстовое описание адреса.
///  - city: Название города, где находится адрес.
///  - country: Название страны, где находится адрес.
///  - latitude: Географическая широта адреса.
///  - longitude: Географическая долгота адреса.
@Serializable
data class Address(
    var address: String,
    var city: String,
    var country: String,
    var latitude: Double,
    var longitude: Double,
): Parametable, Responsable
