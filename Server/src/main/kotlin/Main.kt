package server

import org.koin.core.context.startKoin
import org.koin.dsl.module
import utils.CommandExecutor
import utils.VehicleCollection
import java.util.*

val koinModule = module {
    single { Stack<String>() }
    single {
        val fileName = System.getenv("VEHICLE") ?: "Collection.json"
        VehicleCollection.fromFile(fileName)
    }
}

fun main() {
    startKoin {
        modules(koinModule)
    }

    val commandExecutor = CommandExecutor()
    val serverManager = ServerManager(12342)

    serverManager.startServer(commandExecutor)
}
