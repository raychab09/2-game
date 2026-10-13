package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.components.CharacterHeaderCard
import com.example.ui.components.ScenarioDialog
import com.example.ui.components.StatBarsCard
import com.example.viewmodel.GameUiState
import com.example.viewmodel.GameViewModel

enum class MainTab(val title: String, val icon: ImageVector) {
    STORY("السجل", Icons.Default.MenuBook),
    ACTIVITIES("الأنشطة", Icons.Default.Bolt),
    FAMILY("العائلة", Icons.Default.FamilyRestroom),
    ANCESTORS("الأجداد", Icons.Default.AccountBalance)
}

@Composable
fun MainGameScreen(
    viewModel: GameViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(MainTab.STORY) }
    var showAncestorsModal by remember { mutableStateOf(false) }

    // If character died, show Death Summary Screen
    if (uiState.isDead) {
        DeathSummaryScreen(
            profile = uiState.profile,
            ageAtDeath = uiState.age,
            deathCause = uiState.deathCause,
            legacyTitle = uiState.legacyTitle,
            finalStats = uiState.stats,
            milestones = uiState.milestones,
            relatives = uiState.relatives,
            onRestartNewLife = { viewModel.startNewLife(generation = 1) },
            onContinueAsChild = { child -> viewModel.continueAsChild(child) },
            onViewAncestors = { showAncestorsModal = true }
        )

        if (showAncestorsModal) {
            AncestorsScreen(
                ancestors = uiState.ancestors,
                onBack = { showAncestorsModal = false }
            )
        }
        return
    }

    if (showAncestorsModal) {
        AncestorsScreen(
            ancestors = uiState.ancestors,
            onBack = { showAncestorsModal = false }
        )
        return
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag("main_game_screen"),
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .testTag("bottom_nav_bar")
            ) {
                MainTab.values().forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = { Icon(imageVector = tab.icon, contentDescription = tab.title) },
                        label = { Text(text = tab.title, fontWeight = FontWeight.Bold) },
                        modifier = Modifier.testTag("nav_item_${tab.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .statusBarsPadding()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Header card
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                CharacterHeaderCard(
                    profile = uiState.profile,
                    age = uiState.age,
                    onAdvanceYear = { viewModel.advanceYear() }
                )
            }

            // Stat bars card
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                StatBarsCard(stats = uiState.stats)
            }

            // Active Tab Content
            Box(modifier = Modifier.weight(1f)) {
                when (selectedTab) {
                    MainTab.STORY -> {
                        StoryLogsScreen(
                            logs = uiState.logs,
                            lastOutcomeMessage = uiState.lastOutcomeMessage,
                            onDismissOutcome = { viewModel.dismissOutcome() }
                        )
                    }
                    MainTab.ACTIVITIES -> {
                        ActivitiesScreen(
                            stats = uiState.stats,
                            onDoActivity = { actId -> viewModel.doActivity(actId) }
                        )
                    }
                    MainTab.FAMILY -> {
                        FamilyScreen(relatives = uiState.relatives)
                    }
                    MainTab.ANCESTORS -> {
                        AncestorsScreen(
                            ancestors = uiState.ancestors,
                            onBack = { selectedTab = MainTab.STORY }
                        )
                    }
                }
            }
        }
    }

    // Active Scenario Dialog
    uiState.currentScenario?.let { scenario ->
        ScenarioDialog(
            scenario = scenario,
            onChoiceSelected = { choice ->
                viewModel.applyScenarioChoice(choice)
            }
        )
    }
}
