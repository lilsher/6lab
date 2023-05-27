package utils

/**
 * Утилита, нужная для генирации ID
 */
object IdGenerator {
    private val generatedIds = mutableSetOf<Int>()


    fun generateUniqueId(): Int {
        var id: Int
        do {
            id = (1..Int.MAX_VALUE).random()
        } while (generatedIds.contains(id))
        generatedIds.add(id)
        return id
    }
}

