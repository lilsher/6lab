package commands

/**
 * Класс PrintUniqueFuelType возвращает строковое представление уникальных типов топлива
 * присутствующих в транспортных средствах в коллекции VehicleCollection.
 *
 */
class PrintUniqueFuelType : Command() {

    override fun execute(args: List<Any>): String {
        val uniqueFuelTypes = vehicleCollection.show().map { it.fuelType }.distinct()
        return uniqueFuelTypes.joinToString("\n")
    }

}