package commands



/**
 * Класс GroupCountingByFuelType группирует транспортные средства в коллекции VehicleCollection по типу топлива
 * и возвращает строковое представление подсчетов для каждого типа топлива.
 *
 */
class GroupCountingByFuelType : Command() {

    override fun execute(args: List<Any>): String {
        val groupCounts = vehicleCollection.show().groupBy { it.fuelType }
            .mapValues { (_, v) -> v.size }
        val formattedOutput = groupCounts.entries.joinToString("\n") {
            "${it.key}: ${it.value}"
        }
        return formattedOutput
    }


}