package com.example.propertytaxcalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class SplashScreenActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen {
                startActivity(
                    Intent(
                        this@SplashScreenActivity,
                        HomeActivity::class.java
                    )
                )
                finish()
            }
        }
    }
}

@Composable
fun SplashScreen(
    onNavigate: () -> Unit
) {
    // Dynamic sequential states for staggered animation loops
    var iconVisible by remember { mutableStateOf(false) }
    var textVisible by remember { mutableStateOf(false) }
    var progressVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200)
        iconVisible = true
        delay(400)
        textVisible = true
        delay(300)
        progressVisible = true

        // Total screen hold duration (3000ms setup)
        delay(2100)
        onNavigate()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF070A13), // Deep Premium Obsidian
                        Color(0xFF0F1526), // Midnight Core Dark
                        Color(0xFF172036)  // Muted Royal Base
                    )
                )
            )
    ) {
        // Central Core Brand Matrix
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated Icon Node
            AnimatedVisibility(
                visible = iconVisible,
                enter = fadeIn(animationSpec = tween(800)) +
                        slideInVertically(animationSpec = tween(800)) { -40 }
            ) {
                Icon(
                    imageVector = Icons.Default.HomeWork,
                    contentDescription = "Property Architecture Node",
                    tint = Color(0xFF00E676), // Rich Cyber Mint / Growth Accent
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Animated Typography Core
            AnimatedVisibility(
                visible = textVisible,
                enter = fadeIn(animationSpec = tween(1000)) +
                        slideInVertically(animationSpec = tween(1000)) { 30 }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "PROPERTY TAX",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.5.sp,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "CALCULATOR",
                        color = Color(0xFF00B0FF), // Neon Electric Blue Accent
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light,
                        letterSpacing = 4.sp,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Smart Asset Evaluation Engine",
                        color = Color(0xFF94A3B8), // Sleek Slate Subtext
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }

        // Bottom Telemetry Tracker (Soft Loading Feedback)
        AnimatedVisibility(
            visible = progressVisible,
            enter = fadeIn(animationSpec = tween(600)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .width(140.dp)
                        .height(3.dp),
                    color = Color(0xFF00E676),
                    trackColor = Color(0xFF1E293B)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "SECURE PROTOCOL INITIALIZATION",
                    color = Color(0xFF475569), // Muted Industrial Slate
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }
        }
    }
}