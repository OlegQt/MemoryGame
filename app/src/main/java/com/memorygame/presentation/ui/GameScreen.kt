package com.memorygame.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.memorygame.presentation.viewmodel.GameViewModel

@Composable
fun GameScreen(vm:GameViewModel){
    val logTxt = vm.logLine.observeAsState()
    
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = logTxt.value.toString())
        
    }

}