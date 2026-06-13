package com.example.propertytaxcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class TaxHistoryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TaxHistoryScreen(this)
        }
    }
}

data class StatItem(
    val title: String,
    val value: String,
    val icon: ImageVector,
    val systemAccentColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaxHistoryScreen(
    activity: TaxHistoryActivity
) {
    val dbHelper = remember { DatabaseHelper(activity) }

    // Unified Cyber Architecture Color Tokens
    val baseDarkBackground = Color(0xFF070A13)
    val cardBackground = Color(0xFF0F1526)
    val neonMintAccent = Color(0xFF00E676)
    val electricBlue = Color(0xFF00B0FF)
    val slateSubtext = Color(0xFF94A3B8)
    val edgeBorderStroke = Color(0xFF1E293B)

    val stats = listOf(
        StatItem(
            "Total Properties",
            dbHelper.getTotalProperties().toString(),
            Icons.Default.Home,
            Color.White
        ),
        StatItem(
            "Total Tax Pooled",
            "₹ %,.2f".format(dbHelper.getTotalTaxCollected()),
            Icons.Default.AttachMoney,
            neonMintAccent
        ),
        StatItem(
            "Highest Peak Tax",
            "₹ %,.2f".format(dbHelper.getHighestTax()),
            Icons.Default.BarChart,
            neonMintAccent
        ),
        StatItem(
            "Lowest Base Tax",
            "₹ %,.2f".format(dbHelper.getLowestTax()),
            Icons.Default.TrendingDown,
            Color(0xFFEF5350) // Soft High-Alert Red for lower vector scale
        ),
        StatItem(
            "Mean Average Tax",
            "₹ %,.2f".format(dbHelper.getAverageTax()),
            Icons.Default.Analytics,
            electricBlue
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ANALYTICAL STATISTICS",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Return onto Base Terminal",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF060616)
                )
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(baseDarkBackground)
                .padding(padding)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp)
            ) {
                // Header Status Subtitle Info Module block
                item(span = { GridItemSpan(2) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = "METRICS AGGREGATION CORE",
                            fontSize = 12.sp,
                            color = electricBlue,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "Real-time query computations compiled across structural local parameters.",
                            fontSize = 13.sp,
                            color = slateSubtext,
                            modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
                        )
                    }
                }

                // Grid Processing Nodes
                items(stats) { stat ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(150.dp)
                            .border(1.dp, edgeBorderStroke, RoundedCornerShape(12.dp)),
                        colors = CardDefaults.cardColors(containerColor = cardBackground),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stat.title.uppercase(),
                                    fontSize = 11.sp,
                                    color = slateSubtext,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace,
                                    letterSpacing = 0.5.sp
                                )

                                Icon(
                                    imageVector = stat.icon,
                                    contentDescription = null,
                                    tint = electricBlue,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            Column {
                                Text(
                                    text = stat.value,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Black,
                                    color = stat.systemAccentColor,
                                    fontFamily = FontFamily.SansSerif
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = "DATABASE COMPILED",
                                    fontSize = 9.sp,
                                    color = Color(0xFF475569),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}