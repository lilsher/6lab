package commands

import data.Vehicle
import data.Output
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Класс AddIfMaxCommand добавляет новый автомобиль в VehicleCollection, если его значение больше, чем самого большого
 * элемента в VehicleCollection.
 *
 */
class AddIfMax : Command() {

    override fun execute(args: List<Any>): String {
        val vehicleJson = args[0] as String
        val vehicle = Json.decodeFromString<Vehicle>(vehicleJson)
        val added = vehicleCollection.addIfMax(vehicle)
        return if (added) Output.Success_added else Output.Not_max
    }
}