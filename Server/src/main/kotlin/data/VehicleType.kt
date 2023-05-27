package data

import kotlinx.serialization.Serializable

/**
 * Класс, который содержит типы транспортных средств
 */
@Serializable
enum class VehicleType {
    CAR,
    SUBMARINE,
    SHIP,
    BICYCLE,
    HOVERBOARD;
}