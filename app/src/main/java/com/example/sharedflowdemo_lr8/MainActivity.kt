package com.example.sharedflowdemo_lr8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.lifecycle.ViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import com.example.sharedflowdemo_lr8.ui.theme.SharedflowdemoLR8Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedflowdemoLR8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScreenSetup(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

class DemoViewModel : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        sharedFlowInit()
    }
    fun sharedFlowInit() {
        viewModelScope.launch {
            for (i in 1..1000) {
                delay(2000.milliseconds)
                _sharedFlow.emit(i)
            }
        }
    }
}
@Composable
fun ScreenSetup(
    modifier: Modifier = Modifier,
    viewModel: DemoViewModel = viewModel()
) {
    MainScreen(modifier)
}
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
}