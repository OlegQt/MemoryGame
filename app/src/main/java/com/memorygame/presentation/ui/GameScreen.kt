package com.memorygame.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
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

    val logTxt = vm.logLine.observeAsState()

    Column() {
        Text(text = logTxt.value.toString())
        Row(modifier = Modifier.fillMaxWidth()) {
            Switch(
                checked = vm.darkMode.observeAsState().value ?: false,
                onCheckedChange = { vm.switchTheme() })
            Button(onClick = { vm.updateDynamic() }) {
                Text(text = "Quantity")
            }
            Button(onClick = { vm.remake() }) {
                Text(text = "/A")
            }
        }
        
        DynamicObjects(vm = vm)
    }
}

@Composable
fun DynamicObjects(vm:GameViewModel){
    val gameState = vm.gameState.collectAsState()
    
    Text(text = gameState.value.size.toString())
    
    Column(modifier = Modifier.fillMaxWidth()) {
        gameState.value.forEach { 
            val stick = it.collectAsState()
            
            Text(text = stick.value.name)
        }
    }
    
}