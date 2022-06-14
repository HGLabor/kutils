package de.hglabor.utils.kutils.serialization

import de.hglabor.utils.kutils.world
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import org.bukkit.Location
import org.bukkit.World

class LocationSerializer : KSerializer<Location> {
    @Suppress("EXPERIMENTAL_API_USAGE")
    override val descriptor = buildClassSerialDescriptor("Location") {
        element<String>("world")
        element<Double>("x")
        element<Double>("y")
        element<Double>("z")
        element<Float>("yaw")
        element<Float>("pitch")
    }

    override fun serialize(encoder: Encoder, value: Location) {
        encoder.encodeStructure(descriptor) {
            if (value.world != null) encodeStringElement(descriptor, 0, value.world.name)
            encodeDoubleElement(descriptor, 1, value.x)
            encodeDoubleElement(descriptor, 2, value.y)
            encodeDoubleElement(descriptor, 3, value.z)
            if (value.pitch != 0f || value.yaw != 0f) {
                encodeFloatElement(descriptor, 4, value.yaw)
                encodeFloatElement(descriptor, 5, value.pitch)
            }
        }
    }

    override fun deserialize(decoder: Decoder): Location =
        decoder.decodeStructure(descriptor) {
            var world: World? = null
            var x = 0.0
            var y = 0.0
            var z = 0.0
            var yaw = 0f
            var pitch = 0f
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> world = world(decodeStringElement(descriptor, index))
                    1 -> x = decodeDoubleElement(descriptor, index)
                    2 -> y = decodeDoubleElement(descriptor, index)
                    3 -> z = decodeDoubleElement(descriptor, index)
                    4 -> yaw = decodeFloatElement(descriptor, index)
                    5 -> pitch = decodeFloatElement(descriptor, index)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }

            Location(world, x, y, z, yaw, pitch)
        }

}