package com.memorygame.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.twotone.AccountBox
import androidx.compose.material.icons.twotone.Clear
import androidx.compose.ui.graphics.vector.ImageVector

enum class StickIcon(val imageVector: ImageVector) {
    A(Icons.Filled.AddCircle),
    B(Icons.Filled.ThumbUp),
    C(Icons.Filled.Delete),
    E(Icons.Filled.CheckCircle),
    F(Icons.Filled.AccountCircle),
    G(Icons.Filled.Add),
    H(Icons.Filled.Build),
    I(Icons.Filled.Face),
    J(Icons.Filled.DateRange),
    K(Icons.Filled.Edit),
    L(Icons.Filled.Info),
    M(Icons.TwoTone.Clear),
    N(Icons.TwoTone.AccountBox)
}
