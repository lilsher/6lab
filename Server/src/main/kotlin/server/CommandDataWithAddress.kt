package server.server

import server.CommandData
import java.net.InetSocketAddress

data class CommandDataWithAddress(val commandData: CommandData, val address: InetSocketAddress)
