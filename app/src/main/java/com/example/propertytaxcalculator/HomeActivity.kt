package com.example.propertytaxcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeScreen()
        }
    }
}

data class DashboardItem(
    val title: String,
    val icon: ImageVector,
    val hexAccentColor: Color,
    val targetRoutingCode: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current

    // Cyberpunk Core Token Array Design Palette
    val baseDarkBackground = Color(0xFF070A13)
    val cardBackground = Color(0xFF0F1526)
    val neonMintAccent = Color(0xFF00E676)
    val electricBlue = Color(0xFF00B0FF)
    val slateSubtext = Color(0xFF94A3B8)
    val gridBorderColor = Color(0xFF1E293B)

    val menuItems = listOf(
        DashboardItem("Calculate Tax", Icons.Default.Calculate, neonMintAccent, "CALC_NODE"),
        DashboardItem("Saved Records", Icons.Default.Save, electricBlue, "RECORDS_NODE"),
        DashboardItem("Tax History", Icons.Default.History, electricBlue, "HISTORY_NODE"),
        DashboardItem("About App", Icons.Default.Info, neonMintAccent, "ABOUT_NODE")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "CORE DASHBOARD MATRIX",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF060616)
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(baseDarkBackground)
                .padding(padding)
        ) {
            // HIGH-END TELEMETRY HUB OVERLAY BANNER
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF09121F), Color(0xFF0F1526))
                        )
                    )
                    .border(1.dp, gridBorderColor)
                    .padding(24.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(neonMintAccent, RoundedCornerShape(50))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ENGINE STANDBY // CHANNELS ONLINE",
                            color = neonMintAccent,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Property Tax Calculator",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Execute localized real estate liability evaluations.",
                        color = slateSubtext,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(menuItems) { item ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(140.dp)
                            .border(1.dp, gridBorderColor, RoundedCornerShape(12.dp))
                            .clickable {
                                when (item.targetRoutingCode) {
                                    "CALC_NODE" -> {
                                        context.startActivity(
                                            Intent(context, CalculateTaxActivity::class.java)
                                        )
                                    }
                                    "RECORDS_NODE" -> {
                                        context.startActivity(
                                            Intent(context, SavedRecordsActivity::class.java)
                                        )
                                    }
                                    "HISTORY_NODE" -> {
                                        context.startActivity(
                                            Intent(context, TaxHistoryActivity::class.java)
                                        )
                                    }
                                    "ABOUT_NODE" -> {
                                        context.startActivity(
                                            Intent(context, AboutActivity::class.java)
                                        )
                                    }
                                    else -> {
                                        Toast.makeText(context, "Routing failure detected: ${item.title}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = cardBackground
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Isolated Accent Icon Frame
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = item.hexAccentColor,
                                modifier = Modifier.size(28.dp)
                            )

                            Column {
                                Text(
                                    text = item.title.uppercase(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    letterSpacing = 0.5.sp,
                                    fontFamily = FontFamily.Monospace
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = "INITIALIZE NODE",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF475569),
                                    fontFamily = FontFamily.Monospace,
                                    letterSpacing = 1.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}