package com.memorygame.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.memorygame.presentation.theme.GameTheme
import com.memorygame.presentation.ui.GameScreen
import com.memorygame.presentation.viewmodel.GameViewModel

class RootActivity : ComponentActivity() {

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameTheme {
                GameScreen(vm =gameViewModel)
            }
        }
    }
}