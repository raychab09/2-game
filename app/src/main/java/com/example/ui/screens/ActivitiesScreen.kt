package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CharacterStats

data class ActivityItem(
    val id: String,
    val title: String,
    val description: String,
    val costAr: String,
    val effectSummary: String,
    val emoji: String,
    val canAfford: (CharacterStats) -> Boolean
)

@Composable
fun ActivitiesScreen(
    stats: CharacterStats,
    onDoActivity: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val activities = listOf(
        ActivityItem(
            id = "doctor",
            title = "زيارة الطبيب والفحص الشامل",
            description = "فحص العلامات الحيوية، فحص الدم، وتلقي الفيتامينات المقوية.",
            costAr = "200 $",
            effectSummary = "+15 صحة ❤️ | +5 سعادة 🎭",
            emoji = "🩺",
            canAfford = { it.wealth >= 200 }
        ),
        ActivityItem(
            id = "study",
            title = "قراءة كتب وتطوير المهارات",
            description = "جلسة قراءة عميقة في علوم الإدارة والفلسفة والتقنية.",
            costAr = "مجاناً",
            effectSummary = "+10 ذكاء 🧠 | +2 سعادة 🎭",
            emoji = "📚",
            canAfford = { true }
        ),
        ActivityItem(
            id = "work",
            title = "ساعات عمل إضافية ومشروع جانبي",
            description = "التركيز على تحصيل دخل إضافي ومضاعفة المكافأة الشهرية.",
            costAr = "مجهود بدني",
            effectSummary = "+دخل مالي 💰 | -4 صحة 📊",
            emoji = "💼",
            canAfford = { true }
        ),
        ActivityItem(
            id = "family",
            title = "جلسة عائلية دافئة حول الشاي",
            description = "قضاء وقت لطيف ومشاركة الحكايات وتبادل الدعم مع الأهل.",
            costAr = "مجاناً",
            effectSummary = "+15 علاقات 💬 | +12 سعادة 🎭",
            emoji = "☕",
            canAfford = { true }
        ),
        ActivityItem(
            id = "gym",
            title = "الاشتراك في النادي الرياضي",
            description = "تمارين لياقة ورفع أثقال لتنشيط الدورة الدموية وبناء العضلات.",
            costAr = "100 $",
            effectSummary = "+12 صحة ❤️ | +8 سعادة 🎭",
            emoji = "🏋️",
            canAfford = { it.wealth >= 100 }
        ),
        ActivityItem(
            id = "leisure",
            title = "رحلة ترفيهية ونزهة في الطبيعة",
            description = "تغيير جو والاستمتاع بالمطاعم والحدائق والفعاليات الحيوية.",
            costAr = "150 $",
            effectSummary = "+20 سعادة 🎭 | +5 صحة ❤️",
            emoji = "🎢",
            canAfford = { it.wealth >= 150 }
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("activities_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "⚡ أنشطة وتطوير هذا العام",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "يمكنك خوض أنشطة إضافية كل عام لتحسين مؤشراتك وصحتك ورصيدك قبل التقدم في العمر.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(activities) { activity ->
                val affordable = activity.canAfford(stats)

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .clickable(enabled = affordable) { onDoActivity(activity.id) }
                        .testTag("activity_${activity.id}"),
                    shape = RoundedCornerShape(14.dp),
                    color = if (affordable) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                            else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f),
                    tonalElevation = if (affordable) 2.dp else 0.dp
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(text = activity.emoji, fontSize = 28.sp)

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = activity.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (affordable) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                )
                                Text(
                                    text = activity.costAr,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (affordable) Color(0xFF10B981) else Color(0xFFEF4444)
                                )
                            }

                            Text(
                                text = activity.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = activity.effectSummary,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}
