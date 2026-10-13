package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.engine.LifeEventEngine
import com.example.model.CharacterStats
import com.example.model.LifeStage
import com.example.model.Relative
import com.example.viewmodel.GameViewModel
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context matches app name`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("مسارات الحياة", appName)
    }

    @Test
    fun `verify life stages from age`() {
        assertEquals(LifeStage.CHILDHOOD, LifeStage.fromAge(5))
        assertEquals(LifeStage.ADOLESCENCE, LifeStage.fromAge(16))
        assertEquals(LifeStage.YOUNG_ADULT, LifeStage.fromAge(28))
        assertEquals(LifeStage.MIDDLE_AGE, LifeStage.fromAge(45))
        assertEquals(LifeStage.SENIOR, LifeStage.fromAge(72))
    }

    @Test
    fun `verify 25 scenarios configured across 5 stages`() {
        val scenarios = LifeEventEngine.scenarios
        assertTrue(scenarios.size >= 25)

        val childhoodScenarios = scenarios.filter { it.stage == LifeStage.CHILDHOOD }
        val teenScenarios = scenarios.filter { it.stage == LifeStage.ADOLESCENCE }
        val youngAdultScenarios = scenarios.filter { it.stage == LifeStage.YOUNG_ADULT }
        val middleAgeScenarios = scenarios.filter { it.stage == LifeStage.MIDDLE_AGE }
        val seniorScenarios = scenarios.filter { it.stage == LifeStage.SENIOR }

        assertTrue(childhoodScenarios.size >= 5)
        assertTrue(teenScenarios.size >= 5)
        assertTrue(youngAdultScenarios.size >= 5)
        assertTrue(middleAgeScenarios.size >= 5)
        assertTrue(seniorScenarios.size >= 5)
    }

    @Test
    fun `verify GameViewModel initial state and advancing year`() {
        val viewModel = GameViewModel()
        val initial = viewModel.uiState.value
        assertEquals(0, initial.age)
        assertFalse(initial.isDead)
        assertTrue(initial.profile.name.isNotBlank())
        assertEquals(1, initial.profile.generation)

        viewModel.advanceYear()
        val next = viewModel.uiState.value
        assertEquals(1, next.age)
        assertTrue(next.logs.isNotEmpty())
    }

    @Test
    fun `verify stats clamps between 0 and 100`() {
        val stats = CharacterStats(health = 95, intelligence = 90, relationships = 90, wealth = 100, happiness = 90)
        val clamped = stats.copyWithClamps(dHealth = 20, dWealth = -500)
        assertEquals(100, clamped.health)
        assertEquals(0L, clamped.wealth)
    }

    @Test
    fun `verify generational continuation as child`() {
        val viewModel = GameViewModel()
        val child = Relative("child1", "أنس", "ابن", 18, 90)
        viewModel.continueAsChild(child)

        val newState = viewModel.uiState.value
        assertEquals(2, newState.profile.generation)
        assertEquals(0, newState.age)
        assertFalse(newState.isDead)
    }
}
