package de.hglabor.utils.kutils.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.DoubleArraySerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.axay.kspigot.extensions.geometry.vec
import org.bukkit.util.Vector

class VectorSerializer : KSerializer<Vector> {
    private val delegateSerializer = DoubleArraySerializer()
    @Suppress("EXPERIMENTAL_API_USAGE")
    override val descriptor = SerialDescriptor("Vector", delegateSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: Vector) {
        val data = doubleArrayOf(value.x, value.y, value.z)
        encoder.encodeSerializableValue(delegateSerializer, data)
    }

    override fun deserialize(decoder: Decoder): Vector {
        val array = decoder.decodeSerializableValue(delegateSerializer)
        return vec(array[0], array[1], array[2])
    }

}