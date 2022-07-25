package br.com.nbagames.game.view

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.test.platform.app.InstrumentationRegistry
import br.com.nbagames.game.R
import br.com.nbagames.game.mapper.LiveGamePresentationMapper
import br.com.nbagames.game.presentation.LiveGameViewModel
import br.com.nbagames.game.view.fake.FakeLoadLiveGames
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class LiveGameListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var liveGameListViewModel: LiveGameViewModel
    private val loadLiveGames = FakeLoadLiveGames()

    @Before
    fun setup() {
        liveGameListViewModel = LiveGameViewModel(
            loadLiveGames = loadLiveGames,
            liveGamePresentationMapper = LiveGamePresentationMapper()
        )
    }

    @Test
    fun live_game_list_is_displayed() {
        composeTestRule.setContent {
            LiveGameList(
                onLiveGameClick = {},
                onClickOpenCalendar = {},
                liveGameViewModel = liveGameListViewModel
            )
        }

        composeTestRule.onRoot().assertIsDisplayed()
    }

    @Test
    fun when_get_empty_live_game_list_see_empty_state() {
        composeTestRule.setContent {
            LiveGameList(
                onLiveGameClick = {},
                onClickOpenCalendar = {},
                liveGameViewModel = liveGameListViewModel
            )
        }

        composeTestRule.onNodeWithText(getStringFromResource(R.string.empty_live_games)).assertIsDisplayed()
        composeTestRule.onNodeWithText(getStringFromResource(R.string.open_calendar)).assertIsDisplayed()
    }

    @Test
    fun when_get_server_error_see_try_again_message() {
        loadLiveGames.throwable = RuntimeException()

        composeTestRule.setContent {
            LiveGameList(
                onLiveGameClick = {},
                onClickOpenCalendar = {},
                liveGameViewModel = liveGameListViewModel
            )
        }

        composeTestRule.onNodeWithText(getStringFromResource(R.string.server_communication_failed)).assertIsDisplayed()
        composeTestRule.onNodeWithText(getStringFromResource(R.string.load_again)).assertIsDisplayed()
    }

    @Test
    fun when_get_unmapped_error_shows_failure_message() {
        loadLiveGames.throwable = Exception()

        composeTestRule.setContent {
            LiveGameList(
                onLiveGameClick = {},
                onClickOpenCalendar = {},
                liveGameViewModel = liveGameListViewModel
            )
        }

        val unknownErrorStringRes = br.com.nbagames.designsystem.R.string.unknown_error_message
        composeTestRule.onNodeWithText(getStringFromResource(unknownErrorStringRes)).assertIsDisplayed()
        composeTestRule.onNodeWithText(getStringFromResource(R.string.load_again)).assertIsDisplayed()
    }

    @Test
    fun when_request_game_list_shows_loading() {
        loadLiveGames.throwable = Exception()

        composeTestRule.setContent {
            LiveGameList(
                onLiveGameClick = {},
                onClickOpenCalendar = {},
                liveGameViewModel = liveGameListViewModel
            )
        }

        val unknownErrorStringRes = br.com.nbagames.designsystem.R.string.unknown_error_message
        composeTestRule.onNodeWithText(getStringFromResource(unknownErrorStringRes)).assertIsDisplayed()
        composeTestRule.onNodeWithText(getStringFromResource(R.string.load_again)).assertIsDisplayed()
    }

    private fun getStringFromResource(@StringRes stringId: Int): String {
        return InstrumentationRegistry.getInstrumentation().targetContext.getString(stringId)
    }
}
