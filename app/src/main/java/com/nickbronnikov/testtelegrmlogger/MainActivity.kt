package com.nickbronnikov.testtelegrmlogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nickbronnikov.telegram_logger.TelegramLogger
import com.nickbronnikov.testtelegrmlogger.ui.theme.TestTelegramLoggerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTelegramLoggerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SentTelegramMessage {
                        TelegramLogger.logToChannel(
                            "",
                            "",
                            it,
                            {

                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SentTelegramMessage(
    onSent: (message: String) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Surface {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label =  { Text(text = "Message") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { onSent(text) }) {
                Text(text = "Sent message")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SentTelegramMessagePreview() {
    TestTelegramLoggerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SentTelegramMessage {}
        }
    }
}
