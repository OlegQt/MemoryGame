package com.memorygame.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.memorygame.R
import com.memorygame.presentation.MemoryStick
import com.memorygame.presentation.StickIcon
import com.memorygame.presentation.theme.GameTheme
import com.memorygame.presentation.viewmodel.GameViewModel

@Composable
fun GameScreen(vm: GameViewModel) {
    val darkMode = vm.darkMode.observeAsState()

    GameTheme(isDarkTheme = darkMode.value ?: false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            App(vm = vm)
        }
    }
}

@Composable
fun App(vm: GameViewModel) {
    Column {
        NewGame(vm)
        NightModeSwitch(vm)

        Divider(Modifier.padding(vertical = 16.dp))

        DataList(viewModel = vm)
    }
}

@Composable
fun DataList(viewModel: GameViewModel) {
    val dataList = viewModel.getGameState
    val memoryRowsCount = viewModel.sticksQuantity

    Column {
        dataList.chunked(memoryRowsCount).forEach {
            Row {
                it.forEach { data ->
                    // Display the list of data using composable
                    MemoryStick(stick = data, vm = viewModel)
                }
            }
        }
    }
}

@Composable
fun MemoryStick(stick: MemoryStick, vm: GameViewModel) {
    val isOpened = stick.isOpened
    val color = if (stick.isEnabled) Color.DarkGray else Color.Blue

    IconButton(onClick = {
        if (stick.isEnabled) vm.pushStick(stick.id)
    }) {
        Column {
            if (isOpened) Icon(
                imageVector = StickIcon.values()[stick.pictureId].imageVector,
                contentDescription = null,
                tint = color
            )
            else Icon(
                imageVector = StickIcon.values().last().imageVector,
                contentDescription = null,
                tint = color
            )
        }
    }
}

@Composable
fun ShowPrefs(viewModel: GameViewModel) {

}

@Composable
fun NightModeSwitch(vm: GameViewModel) {
    val darkMode: Boolean = vm.darkMode.observeAsState().value ?: false
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Night mode = $darkMode", modifier = Modifier.weight(1.0f))
        Switch(
            checked = darkMode,
            onCheckedChange = { vm.switchTheme() })
    }
}

@Composable
fun NewGame(vm: GameViewModel) {
    val quantity = rememberSaveable { mutableStateOf(vm.sticksQuantity) }

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Choose stick quantity", modifier = Modifier.weight(1.0f))

        IconButton(onClick = { vm.newGame(--quantity.value) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_decrease_24),
                contentDescription = null
            )
        }

        Text(text = (quantity.value * quantity.value).toString())


        IconButton(onClick = { vm.newGame(++quantity.value) }) {
            Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null)
        }
    }
}