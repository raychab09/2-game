package com.example.model

enum class LifeStage(val titleAr: String, val ageRangeAr: String, val minAge: Int, val maxAge: Int, val emoji: String) {
    CHILDHOOD("الطفولة", "0 - 12 سنة", 0, 12, "👶"),
    ADOLESCENCE("المراهقة", "13 - 19 سنة", 13, 19, "👦"),
    YOUNG_ADULT("الشباب والعمل", "20 - 39 سنة", 20, 39, "👨"),
    MIDDLE_AGE("منتصف العمر", "40 - 59 سنة", 40, 59, "🧔"),
    SENIOR("الشيخوخة والنهاية", "60+ سنة", 60, 120, "👴");

    companion object {
        fun fromAge(age: Int): LifeStage = when {
            age <= 12 -> CHILDHOOD
            age <= 19 -> ADOLESCENCE
            age <= 39 -> YOUNG_ADULT
            age <= 59 -> MIDDLE_AGE
            else -> SENIOR
        }
    }
}

data class CharacterStats(
    val health: Int = 85,          // 📊 الصحة (0 - 100)
    val intelligence: Int = 60,    // 🧠 الذكاء (0 - 100)
    val relationships: Int = 75,   // 💬 العلاقات (0 - 100)
    val wealth: Long = 500,        // 💰 المال (بالعملة المحلية)
    val happiness: Int = 80        // 🎭 السعادة (0 - 100)
) {
    fun copyWithClamps(
        dHealth: Int = 0,
        dIntelligence: Int = 0,
        dRelationships: Int = 0,
        dWealth: Long = 0,
        dHappiness: Int = 0
    ): CharacterStats {
        return copy(
            health = (health + dHealth).coerceIn(0, 100),
            intelligence = (intelligence + dIntelligence).coerceIn(0, 100),
            relationships = (relationships + dRelationships).coerceIn(0, 100),
            wealth = (wealth + dWealth).coerceAtLeast(0),
            happiness = (happiness + dHappiness).coerceIn(0, 100)
        )
    }
}

data class Relative(
    val id: String,
    val name: String,
    val role: String, // "الأب", "الأم", "أخ", "أخت", "الشريك", "ابن", "ابنة"
    val age: Int,
    var relationPoints: Int = 80,
    val isAlive: Boolean = true
)

data class LifeMilestone(
    val age: Int,
    val title: String,
    val description: String,
    val emoji: String
)

data class CharacterProfile(
    val name: String,
    val gender: String = "ذكر", // ذكر أو أنثى
    val birthCity: String = "الرياض",
    val generation: Int = 1,
    val familyBackground: String = "عائلة متوسطة الحال",
    val specialTrait: String = "سريع البديهة"
)
