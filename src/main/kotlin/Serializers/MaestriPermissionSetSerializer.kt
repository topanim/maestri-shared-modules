package org.maestri.Serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.maestri.DTOs.OptionSet.MaestriPermissionSet
import java.net.URI

object MaestriPermissionSetSerializer: KSerializer<MaestriPermissionSet> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("MaestriPermissionSet", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): MaestriPermissionSet {
        return MaestriPermissionSet(decoder.decodeInt())
    }

    override fun serialize(encoder: Encoder, value: MaestriPermissionSet) {
        encoder.encodeString(value.toString())
    }
}