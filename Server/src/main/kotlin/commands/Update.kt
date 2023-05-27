package commands

import data.Vehicle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Класс UpdateCommand отвечает за обновление конкретного автомобиля в коллекции VehicleCollection
 * путем предоставления действительного ID.
 *
 */
class Update : Command() {

    override fun execute(args: List<Any>): String {
        if (args.size < 2 || args[0] !is String) {
            return "ID and/or Vehicle object is not provided or has an incorrect format."
        }

        val id: Int = try {
            args[0].toString().toInt()
        } catch (e: NumberFormatException) {
            return "Invalid ID format. Please enter a valid number."
        }

        val vehicleJson = args[1] as String
        val updatedVehicle = Json.decodeFromString<Vehicle>(vehicleJson)

        val vehicleToUpdate = vehicleCollection.show().find { it.id == id }

        return if (vehicleToUpdate != null) {
            vehicleCollection.update(id, updatedVehicle)
            "Lab work with ID: $id has been updated."
        } else {
            "No lab work found with ID: $id."
        }
    }

}
