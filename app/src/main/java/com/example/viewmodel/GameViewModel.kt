package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.engine.LifeEventEngine
import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

data class AnnualLogEntry(
    val age: Int,
    val title: String,
    val description: String,
    val statSummary: String = "",
    val emoji: String = "📌"
)

data class GameUiState(
    val profile: CharacterProfile,
    val age: Int = 0,
    val stats: CharacterStats = CharacterStats(),
    val relatives: List<Relative> = emptyList(),
    val milestones: List<LifeMilestone> = emptyList(),
    val logs: List<AnnualLogEntry> = emptyList(),
    val currentScenario: GameScenario? = null,
    val lastOutcomeMessage: String? = null,
    val isDead: Boolean = false,
    val deathCause: String = "",
    val legacyTitle: String = "",
    val ancestors: List<AncestorRecord> = emptyList(),
    val activeTab: String = "story" // "story", "stats", "family", "activities"
)

class GameViewModel : ViewModel {

    private val _uiState = MutableStateFlow(createInitialGame(1))
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private val playedScenarioIds = mutableSetOf<String>()

    constructor() {
        startNewLife(generation = 1)
    }

    private fun createInitialGame(generation: Int, inheritedWealth: Long = 500, parentTrait: String? = null): GameUiState {
        val names = listOf("سامي", "ريان", "كريم", "ليلى", "نور", "طارق", "فاطمة", "سارة", "عمر", "يوسف", "مريم", "زياد", "هدى", "أحمد")
        val cities = listOf("الرياض", "القاهرة", "دبي", "بغداد", "عمان", "الدار البيضاء", "بيروت", "تونس", "جدة")
        val traits = listOf("سريع البديهة", "رياضي بالفطرة", "روح الدعابة والمرح", "صاحب كاريزما", "محب للمعرفة", "هادئ الطباع")

        val characterName = names[Random.nextInt(names.size)]
        val birthCity = cities[Random.nextInt(cities.size)]
        val chosenTrait = parentTrait ?: traits[Random.nextInt(traits.size)]

        val dadName = names.filter { it != characterName }[Random.nextInt(names.size - 1)]
        val momName = listOf("أمينة", "فاطمة", "سعاد", "منى", "خديجة", "لطيفة")[Random.nextInt(6)]

        val initialRelatives = listOf(
            Relative(id = "dad", name = dadName, role = "الأب", age = 28 + Random.nextInt(6), relationPoints = 85),
            Relative(id = "mom", name = momName, role = "الأم", age = 26 + Random.nextInt(5), relationPoints = 90)
        )

        val initialStats = CharacterStats(
            health = 85 + Random.nextInt(15),
            intelligence = 55 + Random.nextInt(25),
            relationships = 75 + Random.nextInt(15),
            wealth = inheritedWealth,
            happiness = 80 + Random.nextInt(15)
        )

        val profile = CharacterProfile(
            name = characterName,
            birthCity = birthCity,
            generation = generation,
            specialTrait = chosenTrait
        )

        val birthLog = AnnualLogEntry(
            age = 0,
            title = "بداية رحلة الحياة 👶",
            description = "ولدت في مستشفى مبارك بمدينة $birthCity لعائلة محبة، وسجلت كفرد جديد في شجرة العائلة (الجيل $generation). التميمة الخاصة: $chosenTrait.",
            emoji = "🌟"
        )

        return GameUiState(
            profile = profile,
            age = 0,
            stats = initialStats,
            relatives = initialRelatives,
            milestones = listOf(LifeMilestone(0, "صرخة الميلاد", "قدومك السعيد إلى هذا العالم في $birthCity", "👶")),
            logs = listOf(birthLog),
            currentScenario = null,
            lastOutcomeMessage = null,
            isDead = false,
            deathCause = "",
            legacyTitle = "",
            ancestors = emptyList()
        )
    }

    fun startNewLife(generation: Int = 1, inheritedWealth: Long = 500, parentTrait: String? = null) {
        val currentAncestors = _uiState.value.ancestors
        playedScenarioIds.clear()
        val fresh = createInitialGame(generation, inheritedWealth, parentTrait).copy(
            ancestors = currentAncestors
        )
        _uiState.value = fresh
    }

    // Advance 1 year in life
    fun advanceYear() {
        val currentState = _uiState.value
        if (currentState.isDead) return

        val newAge = currentState.age + 1

        // Check health decline or natural death
        var isDead = false
        var deathReason = ""

        // Age related health decay after 50
        val ageWear = when {
            newAge > 85 -> Random.nextInt(5, 15)
            newAge > 70 -> Random.nextInt(2, 8)
            newAge > 50 -> Random.nextInt(1, 4)
            else -> 0
        }

        var newStats = currentState.stats.copyWithClamps(dHealth = -ageWear)

        // Mortality chance for extreme old age or zero health
        if (newStats.health <= 0) {
            isDead = true
            deathReason = LifeEventEngine.evaluateDeathCause(newAge, newStats)
        } else if (newAge >= 75) {
            val deathRoll = Random.nextInt(100)
            val threshold = (newAge - 70) * 4 - (newStats.health / 5)
            if (deathRoll < threshold) {
                isDead = true
                deathReason = LifeEventEngine.evaluateDeathCause(newAge, newStats)
            }
        }

        // Relative aging and events
        val updatedRelatives = currentState.relatives.map { rel ->
            val relAge = rel.age + 1
            var alive = rel.isAlive
            if (relAge > 80 && Random.nextInt(100) < 15) {
                alive = false
            }
            rel.copy(age = relAge, isAlive = alive)
        }

        // Add child milestone if married and in adult age
        val childrenCount = updatedRelatives.count { it.role.startsWith("ابن") || it.role.startsWith("ابنة") }
        var relativesWithChildren = updatedRelatives
        val newMilestones = currentState.milestones.toMutableList()

        if (newAge in 23..38 && childrenCount < 3 && Random.nextInt(100) < 20) {
            val childGender = if (Random.nextBoolean()) "ابن" else "ابنة"
            val childNames = if (childGender == "ابن") listOf("زيد", "حمزة", "أنس", "سلطان", "آدم") else listOf("جنى", "ريما", "ياسمين", "تالا", "سلمى")
            val childName = childNames[Random.nextInt(childNames.size)]
            val newChild = Relative(
                id = "child_$newAge",
                name = childName,
                role = childGender,
                age = 0,
                relationPoints = 95
            )
            relativesWithChildren = relativesWithChildren + newChild
            newMilestones.add(LifeMilestone(newAge, "قدوم $childName 🍼", "رزقت بـ $childGender وسميته $childName ليملأ البيت بهجة وسروراً.", "🍼"))
        }

        if (isDead) {
            triggerDeath(newAge, newStats, deathReason)
            return
        }

        // Try picking an age-appropriate scenario
        val nextScenario = LifeEventEngine.pickScenario(newAge, playedScenarioIds)
        if (nextScenario != null) {
            playedScenarioIds.add(nextScenario.id)
        }

        // Generate a micro event for this year
        val micro = LifeEventEngine.generateAnnualMicroEvent(newAge, newStats)
        newStats = newStats.copyWithClamps(
            dHealth = micro.second.health,
            dIntelligence = micro.second.intelligence,
            dRelationships = micro.second.relationships,
            dWealth = micro.second.wealth,
            dHappiness = micro.second.happiness
        )

        val yearSummaryLog = AnnualLogEntry(
            age = newAge,
            title = "العمر $newAge سنة (${LifeStage.fromAge(newAge).titleAr})",
            description = micro.first,
            statSummary = micro.second.summaryAr(),
            emoji = LifeStage.fromAge(newAge).emoji
        )

        _uiState.value = currentState.copy(
            age = newAge,
            stats = newStats,
            relatives = relativesWithChildren,
            milestones = newMilestones,
            logs = listOf(yearSummaryLog) + currentState.logs,
            currentScenario = nextScenario,
            lastOutcomeMessage = null
        )
    }

    // Apply player's choice from a scenario
    fun applyScenarioChoice(choice: ScenarioChoice) {
        val state = _uiState.value
        val scenario = state.currentScenario ?: return

        var newStats = state.stats.copyWithClamps(
            dHealth = choice.statChange.health,
            dIntelligence = choice.statChange.intelligence,
            dRelationships = choice.statChange.relationships,
            dWealth = choice.statChange.wealth,
            dHappiness = choice.statChange.happiness
        )

        val newMilestones = state.milestones.toMutableList()
        if (choice.milestoneTitle != null) {
            newMilestones.add(
                LifeMilestone(
                    age = state.age,
                    title = choice.milestoneTitle,
                    description = choice.outcomeDescriptionAr,
                    emoji = scenario.emoji
                )
            )
        }

        val choiceLog = AnnualLogEntry(
            age = state.age,
            title = scenario.titleAr,
            description = "اخترت: ${choice.textAr}\nالنتيجة: ${choice.outcomeDescriptionAr}",
            statSummary = choice.statChange.summaryAr(),
            emoji = scenario.emoji
        )

        if (choice.isFatal || newStats.health <= 0) {
            val fatalReason = if (choice.fatalReason.isNotBlank()) choice.fatalReason else choice.outcomeDescriptionAr
            _uiState.value = state.copy(
                stats = newStats,
                milestones = newMilestones,
                logs = listOf(choiceLog) + state.logs,
                currentScenario = null,
                lastOutcomeMessage = choice.outcomeDescriptionAr
            )
            triggerDeath(state.age, newStats, fatalReason)
            return
        }

        _uiState.value = state.copy(
            stats = newStats,
            milestones = newMilestones,
            logs = listOf(choiceLog) + state.logs,
            currentScenario = null,
            lastOutcomeMessage = choice.outcomeDescriptionAr
        )
    }

    private fun triggerDeath(age: Int, stats: CharacterStats, reason: String) {
        val state = _uiState.value
        val legacy = LifeEventEngine.generateLegacyTitle(age, stats, stats.wealth)
        val childrenCount = state.relatives.count { (it.role.startsWith("ابن") || it.role.startsWith("ابنة")) && it.isAlive }

        val record = AncestorRecord(
            generation = state.profile.generation,
            name = state.profile.name,
            ageAtDeath = age,
            deathCause = reason,
            finalWealth = stats.wealth,
            legacyTitle = legacy,
            topMilestones = state.milestones.take(4).map { it.title },
            childrenCount = childrenCount
        )

        val updatedAncestors = listOf(record) + state.ancestors

        val deathLog = AnnualLogEntry(
            age = age,
            title = "وفاة الشخصية وانتقالها للدار الآخرة 🕊️",
            description = "انتقل إلى رحمة الله ${state.profile.name} عن عمر يناهز $age سنة. السبب: $reason.",
            emoji = "⚰️"
        )

        _uiState.value = state.copy(
            isDead = true,
            deathCause = reason,
            legacyTitle = legacy,
            ancestors = updatedAncestors,
            logs = listOf(deathLog) + state.logs,
            currentScenario = null
        )
    }

    // Continue as one of the children
    fun continueAsChild(childRelative: Relative) {
        val state = _uiState.value
        val inheritedWealth = (state.stats.wealth * 0.6).toLong().coerceAtLeast(1000)
        startNewLife(
            generation = state.profile.generation + 1,
            inheritedWealth = inheritedWealth,
            parentTrait = state.profile.specialTrait
        )
    }

    // Quick annual activities
    fun doActivity(type: String) {
        val state = _uiState.value
        if (state.isDead) return

        when (type) {
            "doctor" -> {
                if (state.stats.wealth >= 200) {
                    val newStats = state.stats.copyWithClamps(dHealth = 15, dWealth = -200, dHappiness = 5)
                    logActivity("زيارة الطبيب وإجراء فحص شامل", "أكد الطبيب سلامة أجهزتك الحيوية مع نصائح للمحافظة على النشاط.", "+15 صحة | -200$", "🩺", newStats)
                }
            }
            "study" -> {
                val newStats = state.stats.copyWithClamps(dIntelligence = 10, dHappiness = 2)
                logActivity("قراءة كتب وبحوث لتطوير الذات", "قرأت كتاباً عميقاً في الفلسفة والإدارة زاد من سعة أفقك.", "+10 ذكاء | +2 سعادة", "📚", newStats)
            }
            "work" -> {
                val gain = when (LifeStage.fromAge(state.age)) {
                    LifeStage.CHILDHOOD -> 20L
                    LifeStage.ADOLESCENCE -> 150L
                    LifeStage.YOUNG_ADULT -> 2500L
                    LifeStage.MIDDLE_AGE -> 4500L
                    LifeStage.SENIOR -> 1000L
                }
                val newStats = state.stats.copyWithClamps(dWealth = gain, dHealth = -4, dHappiness = -2)
                logActivity("ساعات عمل إضافية ومشروع جانبي", "بذلت جهداً إضافياً وحصلت على مكافأة مالية مستحقة.", "+$gain$ | -4 صحة", "💼", newStats)
            }
            "family" -> {
                val newStats = state.stats.copyWithClamps(dRelationships = 15, dHappiness = 12)
                logActivity("جلسة عائلية دافئة حول الشاي", "قضيت وقتاً رائعاً وتبادلتم الضحكات والذكريات المشتركة.", "+15 علاقات | +12 سعادة", "☕", newStats)
            }
            "leisure" -> {
                if (state.stats.wealth >= 150) {
                    val newStats = state.stats.copyWithClamps(dHappiness = 20, dWealth = -150, dHealth = 5)
                    logActivity("رحلة ترفيهية ونزهة في الطبيعة", "استمتعت بيوم مليء بالمرح والراحة وتجديد النشاط.", "+20 سعادة | +5 صحة | -150$", "🎢", newStats)
                }
            }
            "gym" -> {
                if (state.stats.wealth >= 100) {
                    val newStats = state.stats.copyWithClamps(dHealth = 12, dHappiness = 8, dWealth = -100)
                    logActivity("تمرين رياضي شاق في النادي", "حرقت السعرات الحرارية وشحنت طاقتك الإيجابية.", "+12 صحة | +8 سعادة | -100$", "🏋️", newStats)
                }
            }
        }
    }

    private fun logActivity(title: String, desc: String, summary: String, emoji: String, newStats: CharacterStats) {
        val state = _uiState.value
        val log = AnnualLogEntry(
            age = state.age,
            title = title,
            description = desc,
            statSummary = summary,
            emoji = emoji
        )
        _uiState.value = state.copy(
            stats = newStats,
            logs = listOf(log) + state.logs
        )
    }

    fun dismissOutcome() {
        _uiState.value = _uiState.value.copy(lastOutcomeMessage = null)
    }

    fun setActiveTab(tab: String) {
        _uiState.value = _uiState.value.copy(activeTab = tab)
    }
}
