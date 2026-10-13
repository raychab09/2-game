package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CharacterProfile
import com.example.model.CharacterStats
import com.example.model.LifeMilestone
import com.example.model.Relative

@Composable
fun DeathSummaryScreen(
    profile: CharacterProfile,
    ageAtDeath: Int,
    deathCause: String,
    legacyTitle: String,
    finalStats: CharacterStats,
    milestones: List<LifeMilestone>,
    relatives: List<Relative>,
    onRestartNewLife: () -> Unit,
    onContinueAsChild: (Relative) -> Unit,
    onViewAncestors: () -> Unit,
    modifier: Modifier = Modifier
) {
    val aliveChildren = relatives.filter {
        (it.role.startsWith("ابن") || it.role.startsWith("ابنة")) && it.isAlive
    }

    val tombstoneGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1E1B4B),
            Color(0xFF0F172A),
            Color(0xFF020617)
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(tombstoneGradient)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .testTag("death_summary_screen"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Memorial Icon
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(Color(0xFF312E81)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "🕊️", fontSize = 38.sp)
        }

        // Header Title
        Text(
            text = "شريط العمر والوداع الأخير",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFF1F5F9),
            textAlign = TextAlign.Center
        )

        // Memorial Plaque / الشاهد التذكاري
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFF1E293B).copy(alpha = 0.9f),
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "هنا يرقد المرحوم بإذن الله",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF94A3B8)
                )

                Text(
                    text = profile.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF8FAFC)
                )

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF59E0B).copy(alpha = 0.2f)
                ) {
                    Text(
                        text = "اللقب: $legacyTitle",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFBBF24)
                    )
                }

                HorizontalDivider(color = Color(0xFF334155), modifier = Modifier.padding(vertical = 4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "عاش حتى", style = MaterialTheme.typography.labelSmall, color = Color(0xFF94A3B8))
                        Text(text = "$ageAtDeath عاماً", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "الجيل", style = MaterialTheme.typography.labelSmall, color = Color(0xFF94A3B8))
                        Text(text = "الجيل ${profile.generation}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "الثروة الموروثة", style = MaterialTheme.typography.labelSmall, color = Color(0xFF94A3B8))
                        Text(text = "${String.format("%,d", finalStats.wealth)}$", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFF34D399))
                    }
                }

                // Death Cause Notice
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFEF4444).copy(alpha = 0.15f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "سبب الوفاة:",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF87171)
                        )
                        Text(
                            text = deathCause,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFFCA5A5),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        // Life Milestones Reel / شريط الذكريات والمحطات
        if (milestones.isNotEmpty()) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = Color(0xFF1E293B).copy(alpha = 0.7f)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.WorkspacePremium, contentDescription = null, tint = Color(0xFFF59E0B))
                        Text(
                            text = "أبرز محطات وإنجازات العمر",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    milestones.forEach { milestone ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(text = milestone.emoji, fontSize = 20.sp)
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${milestone.title} (العمر: ${milestone.age})",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFFE2E8F0)
                                )
                                Text(
                                    text = milestone.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF94A3B8)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Generational Options / خيارات المستقبل والأجيال
        Text(
            text = "اختر مسارك القادم:",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE2E8F0)
        )

        // Option 1: Continue as Child
        if (aliveChildren.isNotEmpty()) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFF065F46).copy(alpha = 0.4f),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.FamilyRestroom, contentDescription = null, tint = Color(0xFF34D399))
                        Text(
                            text = "متابعة السلالة كأحد الأبناء (الجيل ${profile.generation + 1})",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6EE7B7)
                        )
                    }
                    Text(
                        text = "سينتقل للوريث جزء كبير من ثروتك (${String.format("%,d", (finalStats.wealth * 0.6).toLong())}$) مع صفاتك الوراثية:",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFA7F3D0)
                    )

                    aliveChildren.forEach { child ->
                        Button(
                            onClick = { onContinueAsChild(child) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("continue_child_${child.id}"),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF059669)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "اللعب بـ ${child.name} (${child.role} - العمر: ${child.age} سنة)",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Option 2: Fresh New Life
        Button(
            onClick = onRestartNewLife,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("restart_new_life_button"),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Replay, contentDescription = null)
                Text(
                    text = "خوض حياة جديدة كلياً (الجيل 1)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Option 3: View Ancestors Cemetery
        OutlinedButton(
            onClick = onViewAncestors,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("view_ancestors_button"),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFCBD5E1))
        ) {
            Text(text = "🏛️ سجل الأجداد ومقبرة الخلود", fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
