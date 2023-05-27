package commands

/**
 * Класс ShowCommand отображает все транспортные средства в коллекции VehicleCollection. *
 */
class Show : Command() {
    override fun execute(args: List<Any>): String {
        return vehicleCollection.show().joinToString(separator = "\n")
    }

}
