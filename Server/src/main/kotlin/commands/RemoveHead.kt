package commands

/**
 * Класс RemoveHeadCommand удаляет и возвращает первый элемент в коллекции Vehicle VehicleCollection. *
 */
class RemoveHead : Command() {
    override fun execute(args: List<Any>): String {
        return vehicleCollection.removeHead().toString()
    }


}
