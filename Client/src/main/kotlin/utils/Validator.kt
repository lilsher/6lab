package utils

import exeptions.data.Coordinates
import exeptions.ValidationException

/**
 * Класс, который валидирует вводимые пользователем значения
 */
class Validator {

    fun validateName(name: String?) {
        if (name.isNullOrBlank()) {
            throw ValidationException("Name cannot be empty.")
        }
    }

    fun validateCoordinates(coordinates: Coordinates?) {
        if (coordinates == null || coordinates.y > 297) {
            throw ValidationException("y coordinate value cannot be greater than 297.")
        }
    }


    fun validateEnginePower(enginePower: Int?) {
        if (enginePower == null || enginePower < 0) {
            throw ValidationException("Engine Power must be greater than 0.")
        }
    }


    fun validateDistanceTravelled(distanceTravelled: Double?) {
        if (distanceTravelled == null || distanceTravelled <= 0) {
            throw ValidationException("Travelled distance must be greater than 0.")
        }
    }


}
