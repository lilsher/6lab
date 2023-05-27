package commands

/**
 * Класс InfoCommand отображает информацию о транспортном средстве VehicleCollection.
 */
class Info : Command() {
    override fun execute(args: List<Any>): String {
        return vehicleCollection.getInfo()
    }
}
