package com.example.propertytaxcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SavedRecordsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SavedRecordsScreen(this)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedRecordsScreen(
    activity: SavedRecordsActivity
) {
    val dbHelper = remember { DatabaseHelper(activity) }

    val records by remember {
        mutableStateOf(dbHelper.getAllRecords())
    }

    // Modern Cyberpunk System Color Tokens Map
    val baseDarkBackground = Color(0xFF070A13)
    val cardBackground = Color(0xFF0F1526)
    val neonMintAccent = Color(0xFF00E676)
    val electricBlue = Color(0xFF00B0FF)
    val slateSubtext = Color(0xFF94A3B8)
    val borderStrokeColor = Color(0xFF1E293B)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "LOCAL PERSISTENCE RECORDS",
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
                            contentDescription = "Return onto Dashboard Matrix",
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
            if (records.isEmpty()) {
                // CYBER TERMINAL EMPTY FALLBACK MATRIX UX
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Dns,
                        contentDescription = null,
                        tint = borderStrokeColor,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "NULL SET DETECTED",
                        color = Color(0xFFEF5350), // Hex Soft Warning Red
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "No operational calculation parameters synced onto local database tables yet.",
                        color = slateSubtext,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.sp
                    )
                }
            } else {
                // CORE DATABASE LAZY CHANNEL LAYER
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(records) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, borderStrokeColor, RoundedCornerShape(12.dp)),
                            colors = CardDefaults.cardColors(containerColor = cardBackground),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {
                                // Node Meta Identifier Label Row
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "ASSET REGISTRY OWNER",
                                            fontSize = 9.sp,
                                            color = electricBlue,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.Monospace,
                                            letterSpacing = 1.sp
                                        )
                                        Text(
                                            text = item.ownerName.uppercase(),
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.White
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .background(Color(0xFF060616), RoundedCornerShape(4.dp))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                            .border(1.dp, Color(0xFF334155), RoundedCornerShape(4.dp))
                                    ) {
                                        Text(
                                            text = item.createdDate,
                                            fontSize = 11.sp,
                                            color = slateSubtext,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                // Structural Grid Parameters Layout
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFF070A13), RoundedCornerShape(8.dp))
                                        .padding(12.dp)
                                ) {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        TelemetryDataChip(label = "FRAMEWORK", value = item.propertyType, fallbackAccent = Color.White)
                                        TelemetryDataChip(label = "TOTAL SCALING", value = "${item.area} SQ.FT", fallbackAccent = electricBlue)
                                    }
                                    HorizontalDivider(color = Color(0xFF1E293B), thickness = 0.5.dp)
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        TelemetryDataChip(label = "CHRONO AGE", value = item.buildingAge, fallbackAccent = Color.White)
                                        TelemetryDataChip(label = "OCCUPANCY", value = item.occupancy, fallbackAccent = Color.White)
                                    }
                                    HorizontalDivider(color = Color(0xFF1E293B), thickness = 0.5.dp)
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        TelemetryDataChip(label = "REGIONAL ZONE", value = item.zone, fallbackAccent = electricBlue)
                                    }
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                // Final Processed Financial Liability Vector Block
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            Brush.horizontalGradient(
                                                colors = listOf(Color(0xFF09121F), Color(0xFF0F1526))
                                            ),
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                        .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(6.dp))
                                        .padding(horizontal = 14.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.AssignmentTurnedIn,
                                            contentDescription = null,
                                            tint = neonMintAccent,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "LIABILITY EVALUATED :",
                                            fontSize = 11.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.Monospace,
                                            letterSpacing = 0.5.sp
                                        )
                                    }

                                    Text(
                                        text = "₹ %,.2f".format(item.taxAmount),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Black,
                                        color = neonMintAccent,
                                        fontFamily = FontFamily.SansSerif
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TelemetryDataChip(
    label: String,
    value: String,
    fallbackAccent: Color
) {
    Column {
        Text(
            text = label,
            fontSize = 9.sp,
            color = Color(0xFF475569),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 0.5.sp
        )
        Text(
            text = value,
            fontSize = 13.sp,
            color = fallbackAccent,
            fontWeight = FontWeight.SemiBold
        )
    }
}