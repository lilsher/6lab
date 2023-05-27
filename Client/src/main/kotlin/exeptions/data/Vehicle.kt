package exeptions.data

import kotlinx.serialization.Serializable
import utils.LocalDateTimeSerializer
import java.time.LocalDateTime

/**
 * Класс, объекты которого мы зраним в коллекции
 */
@Serializable
data class Vehicle(
    val id: Int,//Значение поля должно быть больше 0,
    // Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически
    val name: String, //Поле не может быть null, Строка не может быть пустой
    val coordinates: Coordinates,//Поле не может быть null
    @Serializable(with = LocalDateTimeSerializer::class)
    val creationDate: LocalDateTime,//Поле не может быть null,
    // Значение этого поля должно генерироваться автоматически
    val enginePower: Int,//Значение поля должно быть больше 0
    val distanceTravelled: Double,//Значение поля должно быть больше 0
    val type: VehicleType?,//Поле может быть null
    val fuelType: FuelType?,//Поле может быть null
) : Comparable<Vehicle> {

    /**
     * Compares this Vehicle object with another Vehicle object by their IDs.
     *
     * @param other The Vehicle object to compare with.
     * @return A negative value if this object is less than the other object, 0 if they are equal, and a positive value if this object is greater than the other object.
     */
    override fun compareTo(other: Vehicle): Int {
        return id.compareTo(other.id)
    }
}