package commands

import data.Output

/**
 * Класс SaveCommand сохраняет коллекцию Vehicle VehicleCollection в файл. *
 */
class Save : Command() {
    override fun execute(args: List<Any>): String {
        vehicleCollection.saveToFile()
        return Output.Success_save
    }


}
