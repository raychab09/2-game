package com.example.model

data class StatChange(
    val health: Int = 0,
    val intelligence: Int = 0,
    val relationships: Int = 0,
    val wealth: Long = 0,
    val happiness: Int = 0
) {
    fun summaryAr(): String {
        val parts = mutableListOf<String>()
        if (health != 0) parts.add("${if (health > 0) "+$health" else "$health"} صحة 📊")
        if (intelligence != 0) parts.add("${if (intelligence > 0) "+$intelligence" else "$intelligence"} ذكاء 🧠")
        if (relationships != 0) parts.add("${if (relationships > 0) "+$relationships" else "$relationships"} علاقات 💬")
        if (wealth != 0L) parts.add("${if (wealth > 0) "+$wealth" else "$wealth"}$ 💰")
        if (happiness != 0) parts.add("${if (happiness > 0) "+$happiness" else "$happiness"} سعادة 🎭")
        return parts.joinToString(" | ")
    }
}

data class ScenarioChoice(
    val id: String,
    val textAr: String,
    val outcomeDescriptionAr: String,
    val statChange: StatChange,
    val isFatal: Boolean = false,
    val fatalReason: String = "",
    val milestoneTitle: String? = null
)

data class GameScenario(
    val id: String,
    val stage: LifeStage,
    val titleAr: String,
    val storyAr: String,
    val emoji: String,
    val choices: List<ScenarioChoice>,
    val minAge: Int = 0,
    val maxAge: Int = 120,
    val isTriggeredOnce: Boolean = true
)

data class AncestorRecord(
    val generation: Int,
    val name: String,
    val ageAtDeath: Int,
    val deathCause: String,
    val finalWealth: Long,
    val legacyTitle: String,
    val topMilestones: List<String>,
    val childrenCount: Int
)
