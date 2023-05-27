package server


import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import server.server.CommandDataWithAddress
import utils.CommandExecutor
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetSocketAddress
import java.net.SocketException
import java.nio.ByteBuffer

class ServerManager(private val port: Int) {
    private val serverSocket: DatagramSocket = DatagramSocket(port)
    private var commandExecutor: CommandExecutor? = null
    private val buffer: ByteBuffer = ByteBuffer.allocate(1024)

    fun startServer(commandExecutor: CommandExecutor) {
        this.commandExecutor = commandExecutor
        println("Server started on port $port")

        runBlocking { handleCommands() }
    }

    private fun receiveCommandData(): CommandDataWithAddress {
        return try {
            val packet = DatagramPacket(buffer.array(), buffer.limit())
            serverSocket.receive(packet)
            val serializedCommand = String(packet.data, 0, packet.length)
            val commandData = Json.decodeFromString<CommandData>(serializedCommand)
            CommandDataWithAddress(commandData, packet.socketAddress as InetSocketAddress)
        } catch (e: SocketException) {
            println("Failed to receive the command due to a socket error.")
            CommandDataWithAddress(CommandData("error", listOf(CommandArgument("message", "Failed to receive the command due to a socket error."))), InetSocketAddress(0))
        } catch (e: IOException) {
            println("Failed to receive the command due to an IO error.")
            CommandDataWithAddress(CommandData("error", listOf(CommandArgument("message", "Failed to receive the command due to an IO error."))), InetSocketAddress(0))
        }
    }


    private fun sendResponse(response: Response, address: InetSocketAddress) {
        val serializedResponse = Json.encodeToString(response)
        val bytes = serializedResponse.toByteArray()
        val packet = DatagramPacket(bytes, bytes.size, address)
        serverSocket.send(packet)
    }

    private fun handleCommands() {
        try{
            while (true) {
                val commandDataWithAddress = receiveCommandData()
                val address = commandDataWithAddress.address
                val command = commandExecutor?.getCommand(commandDataWithAddress.commandData.commandName)
                if (command != null) {
                    val arguments = commandDataWithAddress.commandData.arguments.map { it.value!! }
                    val responseMessage = command.execute(arguments)
                    sendResponse(Response(true, responseMessage), address)
                } else {
                    sendResponse(Response(false, "Command not found: ${commandDataWithAddress.commandData.commandName}"), address)
                }
            }
        } catch (e: SocketException) {
            println("Failed to send the response due to a socket error.")
        } catch (e: IOException) {
            println("Failed to send the response due to an IO error.")
        }
    }



}
