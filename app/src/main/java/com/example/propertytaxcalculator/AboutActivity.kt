package com.example.propertytaxcalculator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AboutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AboutScreen {
                finish()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current

    // Unified Cyberpunk Design System Color Profile
    val baseDarkBackground = Color(0xFF070A13)
    val cardBackground = Color(0xFF0F1526)
    val neonMintAccent = Color(0xFF00E676)
    val electricBlue = Color(0xFF00B0FF)
    val slateSubtext = Color(0xFF94A3B8)
    val systemBorderColor = Color(0xFF1E293B)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "SYSTEM METADATA NODES",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Return onto Pipeline",
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // SECTION 1: CORE APPLICATION MATRIX OVERVIEW
                AboutProfileCard(
                    title = "APPLICATION PROFILE",
                    icon = Icons.Default.Info,
                    accentColor = electricBlue,
                    borderDropColor = systemBorderColor,
                    containerColor = cardBackground
                ) {
                    Text(
                        text = "Property Tax Calculator",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "A high-performance algorithmic compilation built to automate architectural estate assessment evaluations via localized unit area matrices. All target profiles compute metrics dynamically and pipe telemetry tracking records seamlessly into structured local persistence clusters.",
                        fontSize = 14.sp,
                        color = slateSubtext,
                        lineHeight = 20.sp // Aligned line spacing for perfect UI layout text block flow
                    )
                }

                // SECTION 2: SYSTEM FUNCTIONALITIES INDEX
                AboutProfileCard(
                    title = "OPERATIONAL REPERTOIRE",
                    icon = Icons.Default.Layers,
                    accentColor = neonMintAccent,
                    borderDropColor = systemBorderColor,
                    containerColor = cardBackground
                ) {
                    val capabilitiesList = listOf(
                        "Algorithmic Tax Estimation Engine",
                        "Local Relational SQLite Cache Arrays",
                        "Real-Time Telemetry Calculation Logs",
                        "Asset Statistics Analytical Dashboard",
                        "Responsive Dynamic Flow UX Controls"
                    )

                    capabilitiesList.forEach { feature ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = "▪",
                                color = neonMintAccent,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = feature,
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                // SECTION 3: REVENUE EVALUATION EQUATION LAYER
                AboutProfileCard(
                    title = "CORE MATHEMATICAL FORMULA",
                    icon = Icons.Default.Functions,
                    accentColor = electricBlue,
                    borderDropColor = systemBorderColor,
                    containerColor = cardBackground
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF060616), RoundedCornerShape(8.dp))
                            .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(8.dp))
                            .padding(14.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = "Assessed Value = Area × Unit Rate × Age Factor × Occupancy Factor × Zone Factor",
                                fontSize = 13.sp,
                                fontFamily = FontFamily.Monospace,
                                color = electricBlue,
                                fontWeight = FontWeight.Bold
                            )
                            HorizontalDivider(color = Color(0xFF1E293B), thickness = 1.dp)
                            Text(
                                text = "Property Tax Liability = Assessed Value × 15%",
                                fontSize = 13.sp,
                                fontFamily = FontFamily.Monospace,
                                color = neonMintAccent,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // SECTION 4: ARCHITECT CREDENTIALS
                AboutProfileCard(
                    title = "SYSTEM ARCHITECT NODE",
                    icon = Icons.Default.AccountCircle,
                    accentColor = neonMintAccent,
                    borderDropColor = systemBorderColor,
                    containerColor = cardBackground
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            DeveloperMetadataLabel("Lead Engineer", "Jeren")
                            DeveloperMetadataLabel("Platform Infrastructure", "Native Android")
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            DeveloperMetadataLabel("Core Engine Language", "Kotlin Core")
                            DeveloperMetadataLabel("Relational Layer", "SQLite Engine")
                        }
                    }
                }

                // SECTION 5: OUTBOUND GATEWAY PIPELINES
                AboutProfileCard(
                    title = "TELEMETRY CHANNELS",
                    icon = Icons.Default.Link,
                    accentColor = electricBlue,
                    borderDropColor = systemBorderColor,
                    containerColor = cardBackground
                ) {
                    OutboundGatewayRow(
                        platformLabel = "GITHUB PIPELINE HUB",
                        urlTarget = "https://github.com/jeren-dev",
                        displayAddress = "github.com/jeren-dev",
                        accentColor = neonMintAccent,
                        context = context
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutboundGatewayRow(
                        platformLabel = "LINKEDIN CLUSTER",
                        urlTarget = "https://linkedin.com/in/jeren-j-656a6031b",
                        displayAddress = "linkedin.com/in/jeren-j",
                        accentColor = electricBlue,
                        context = context
                    )
                }

                // SECTION 6: FIRMWARE REGISTRY PACKS
                AboutProfileCard(
                    title = "FIRMWARE COMPILATION STATE",
                    icon = Icons.Default.Verified,
                    accentColor = neonMintAccent,
                    borderDropColor = systemBorderColor,
                    containerColor = cardBackground
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "VERSION CHASSIS", fontSize = 11.sp, color = slateSubtext, fontFamily = FontFamily.Monospace)
                            Text(text = "v1.0.0-PROD", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = "DEPLOYMENT BLOCK", fontSize = 11.sp, color = slateSubtext, fontFamily = FontFamily.Monospace)
                            Text(text = "STABLE COMPILATION", fontSize = 14.sp, color = neonMintAccent, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // FOOTER SYSTEM STAMP
                Text(
                    text = "© 2026 PROPERTY TAX CALCULATOR INITIALIZATION NODE. ALL CHANNELS OPERATIONAL.",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 10.sp,
                    color = Color(0xFF475569),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun AboutProfileCard(
    title: String,
    icon: ImageVector,
    accentColor: Color,
    borderDropColor: Color,
    containerColor: Color,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, borderDropColor, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 14.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = accentColor,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.5.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
            content()
        }
    }
}

@Composable
fun DeveloperMetadataLabel(label: String, value: String) {
    Column {
        Text(text = label.uppercase(), fontSize = 10.sp, color = Color(0xFF94A3B8), fontFamily = FontFamily.Monospace)
        Text(text = value, fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun OutboundGatewayRow(
    platformLabel: String,
    urlTarget: String,
    displayAddress: String,
    accentColor: Color,
    context: android.content.Context
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlTarget))
                context.startActivity(intent)
            }
    ) {
        Text(
            text = platformLabel,
            fontSize = 10.sp,
            color = Color(0xFF94A3B8),
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.sp
        )
        Text(
            text = displayAddress,
            fontSize = 15.sp,
            color = accentColor,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
    }
}