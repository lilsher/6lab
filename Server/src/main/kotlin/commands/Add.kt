package commands

import data.Vehicle
import data.Output
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Add : Command() {

    override fun execute(args: List<Any>): String {
        if (args.isEmpty()) {
            throw IllegalArgumentException("Add command expects 1 argument, but got none.")
        }
        val vehicleJson = args[0] as String
        val vehicle = Json.decodeFromString<Vehicle>(vehicleJson)
        vehicleCollection.add(vehicle)
        return Output.Success_added
    }
}
