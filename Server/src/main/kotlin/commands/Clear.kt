package commands

import data.Output

/**
 * Класс ClearCommand очищает коллекцию Vehicle VehicleCollection. *
 */
class Clear : Command() {
    override fun execute(args: List<Any>): String {
        vehicleCollection.clear()
        return Output.Success_clear
    }
}
