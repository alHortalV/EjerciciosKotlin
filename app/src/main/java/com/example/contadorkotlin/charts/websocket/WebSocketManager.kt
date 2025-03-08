package com.example.contadorkotlin.charts.websocket

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.*
import okhttp3.*
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

class WebSocketManager(private val url: String) {

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null
    private val _receivedData = MutableStateFlow<String?>(null)
    val receivedData: StateFlow<String?> = _receivedData.asStateFlow()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val mutex = kotlinx.coroutines.sync.Mutex()

    fun connect() {
        coroutineScope.launch {
            mutex.lock()
            try {
                val request = Request.Builder().url(url).build()
                webSocket = client.newWebSocket(request, object : WebSocketListener() {
                    override fun onOpen(webSocket: WebSocket, response: Response) {
                        println("WebSocket conectado")
                    }

                    override fun onMessage(webSocket: WebSocket, text: String) {
                        _receivedData.value = text
                    }


                    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                        println("WebSocket cerrado")
                        reconnect()
                    }

                    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                        reconnect()
                    }
                })
            } finally {
                mutex.unlock()
            }
        }
    }

    private fun reconnect() {
        coroutineScope.launch {
            delay(5.seconds)
            println("Intentando reconectar...")
            connect()
        }
    }

    fun disconnect() {
        webSocket?.close(1000, "Goodbye!")
        println("Desconectando WebSocket")
    }
}
