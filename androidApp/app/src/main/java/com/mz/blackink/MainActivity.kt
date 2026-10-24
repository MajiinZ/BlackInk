package com.mz.blackink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mz.blackink.navigation.BlackInkNavGraph
import com.mz.blackink.shared.ui.BlackInkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { BlackInkTheme { BlackInkNavGraph() } }
    }
}
