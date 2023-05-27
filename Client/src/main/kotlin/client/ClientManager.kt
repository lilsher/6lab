package client

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.IOException
import java.net.*
import java.nio.ByteBuffer

class ClientManager(private val host: String, private val port: Int) {
    private val socket: DatagramSocket = DatagramSocket()
    private val buffer: ByteBuffer = ByteBuffer.allocate(65507)

    fun sendCommand(commandData: CommandData) {
        try {
            val commandWithArgs = commandData.copy(arguments = commandData.arguments.map { it.copy(value = it.value) })
            val serializedCommand = Json.encodeToString(commandWithArgs)
            val bytes = serializedCommand.toByteArray()
            val packet = DatagramPacket(bytes, bytes.size, InetSocketAddress(host, port))
            socket.send(packet)
        } catch (e: UnknownHostException) {
            println("Unable to reach the host: $host")
        } catch (e: SocketException) {
            println("Failed to send the command due to a socket error.")
        } catch (e: IOException) {
            println("Failed to send the command due to an IO error.")
        }
    }

    fun receiveResponse(): Response {
        return try {
            val packet = DatagramPacket(buffer.array(), buffer.limit())
            socket.receive(packet)
            val serializedResponse = String(packet.data, 0, packet.length)
            Json.decodeFromString(serializedResponse)
        } catch (e: SocketException) {
            println("Failed to receive the response due to a socket error.")
            // Return a default error response
            Response(false, "Failed to receive the response due to a socket error.")
        } catch (e: IOException) {
            println("Failed to receive the response due to an IO error.")
            // Return a default error response
            Response(false, "Failed to receive the response due to an IO error.")
        }
    }

    fun sendCommandAndGetResponse(commandData: CommandData): Response {
        sendCommand(commandData)
        return receiveResponse()
    }
}
