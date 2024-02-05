package com.memorygame.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.memorygame.presentation.theme.GameTheme
import com.memorygame.presentation.viewmodel.GameViewModel
import com.memorygame.presentation.viewmodel.MemoryButton

@Composable
fun GameScreen(vm: GameViewModel) {

    val logTxt = vm.logLine.observeAsState()

    val quantity = vm.quantity.collectAsState()

    GameTheme(isDarkTheme = vm.darkMode.value) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                Text(text = logTxt.value.toString() + "${quantity.value}")
                Switch(checked = vm.darkMode.value, onCheckedChange = { vm.switchDarkLightMode() })
                Counter(vm)
                repeat(times = quantity.value) {
                    Button(onClick = { }) {
                        Text(text = "BTN $it")
                    }
                }
            }
        }
    }
}


@Composable
fun MemoryBtn(btnState: State<MemoryButton>) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = btnState.value.name)
    }
}

@Composable
fun Counter(vm: GameViewModel) {
    val quantity = vm.quantity.collectAsState()

    Card {
        Row {
            IconButton(onClick = { vm.reduceMemory() }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
            }

            Text(text = quantity.value.toString())

            IconButton(onClick = { vm.plusMemory() }) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null)
            }
        }
    }

}
