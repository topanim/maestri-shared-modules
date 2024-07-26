package org.maestri.DTOs.Enums.utils

import kotlinx.serialization.modules.SerializersModuleBuilder

interface PolymorphSerializable {
    val module :  SerializersModuleBuilder.() -> Unit
}