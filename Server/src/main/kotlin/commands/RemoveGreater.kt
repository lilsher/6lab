package commands

import data.Output
import data.Vehicle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Объект RemoveGreater удаляет из VehicleCollection все транспортные средства с характеристиками.
 * Которые превышают заданную.
 *
 */
class RemoveGreater : Command() {
    override fun execute(args: List<Any>): String {
        if (args.isEmpty()) {
            throw IllegalArgumentException("RemoveGreater command expects 1 argument, but got none.")
        }
        val vehicleJson = args[0] as String
        val vehicle = Json.decodeFromString<Vehicle>(vehicleJson)

        val initialSize = vehicleCollection.size()
        vehicleCollection.removeGreater(vehicle)

        return if (vehicleCollection.size() < initialSize) {
            Output.removedGreater
        } else {
            return Output.nothingToRemove
        }
    }
}


