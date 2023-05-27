package commands

/**
 * Класс RemoveByIdCommand удаляет автомобиль из коллекции VehicleCollection, предоставляя действительный ID. *
 */
class RemoveById : Command() {
    override fun execute(args: List<Any>): String {
        if (args.isEmpty() || args[0] !is String) {
            return "ID is not provided or has an incorrect format."
        }

        val id: Int = try {
            args[0].toString().toInt()
        } catch (e: NumberFormatException) {
            return "Invalid ID format. Please enter a valid number."
        }

        val removed = vehicleCollection.removeById(id)
        return if (removed) {
            "Vehicle removed successfully."
        } else {
            "No vehicle found with the provided id."
        }
    }
}
