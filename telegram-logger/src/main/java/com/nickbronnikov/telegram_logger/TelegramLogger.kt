package com.nickbronnikov.telegram_logger

import okhttp3.*
import java.io.IOException

class TelegramLogger {
    companion object {
        const val TELEGRAM_API_URL = "api.telegram.org"
        const val SEND_MESSAGE_ACTION = "sendMessage"
        const val CHAT_ID_PARAM = "chat_id"
        const val TEXT_PARAM = "text"

        private lateinit var instance: TelegramLogger

        @Synchronized
        private fun getInstance(): TelegramLogger {
            if (!this::instance.isInitialized) {
                instance = TelegramLogger()
            }

            return instance
        }

        fun logToChannel(
            botApiToken: String,
            chatId: String,
            message: String,
            onSuccess: (response: String) -> Unit,
            onError: ((e: Throwable) -> Unit)? = null
        ) {
            getInstance().logToChannel(botApiToken, chatId, message, onSuccess, onError)
        }
    }

    private var client: OkHttpClient = OkHttpClient()

    private fun logToChannel(
        botApiToken: String,
        chatId: String,
        message: String,
        onSuccess: (response: String) -> Unit,
        onError: ((e: Throwable) -> Unit)? = null
    ) {
        val url = HttpUrl.Builder()
            .scheme("https")
            .host(TELEGRAM_API_URL)
            .addPathSegment("bot$botApiToken")
            .addPathSegment(SEND_MESSAGE_ACTION)
            .addEncodedQueryParameter(CHAT_ID_PARAM, chatId)
            .addQueryParameter(TEXT_PARAM, message)

        val request = Request.Builder()
            .url(url.build())
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onError?.let { it(e) }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    onSuccess(response.body?.string() ?: "")
                } else {
                    onError?.let { it(IOException("Unexpected code ${response.body?.string()}")) }
                }
            }
        })
    }
}
