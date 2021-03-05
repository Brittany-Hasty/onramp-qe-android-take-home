package com.sxmp.apprentice;

import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RemoveNoteCardDeckTest {
    private ActivityTestRule<DeckPicker> activityRule =
            new ActivityTestRule<>(DeckPicker.class, false, false);

    private GrantPermissionRule grantRule =
            GrantPermissionRule.grant(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE);

    @Rule
    public RuleChain ruleChain = RuleChain.outerRule(grantRule).around(activityRule);

    @Before
    public void setup() {
        // App needs to be open
        activityRule.launchActivity(null);

        // A sample deck needs to exist
        onView(withId(R.id.fab_expand_menu_button)).perform(click());
        onView(withId(R.id.add_deck_action)).perform(click());
        onView(withId(R.id.md_customViewFrame))
                .perform(scrollTo(), replaceText("sample"), closeSoftKeyboard());
        onView(withId(R.id.md_buttonDefaultPositive)).perform(click());
    }

    @Test
    public void longClickDeckNameGivesSubmenuTest() {

    }

    @Test
    public void longClickSubmenuIncludesDeleteDeckTest() {

    }

    @Test
    public void deleteDeckIsClickableInSubmenuTest() {

    }

    @Test
    public void emptyDeckDeletesWithoutConfirmationTest() {

    }

    @Test
    public void deckWithCardsBringsUpConfirmationOnDeleteTest() {

    }

    @Test
    public void warningIconAppearsOnDeleteConfirmationTest() {

    }

    @Test
    public void deleteConfirmationIncludesDeleteQuestionTextTest() {

    }

    @Test
    public void deleteConfirmationIncludesDeckNameTest() {

    }

    @Test
    public void deleteConfirmationIncludesNumberOfDeckCardsTest() {

    }

    @Test
    public void deleteConfirmationHasCancelButtonTest() {

    }

    @Test
    public void deleteConfirmationCancelButtonIsClickableTest() {

    }

    @Test
    public void deleteConfirmationCancelButtonReturnsToMenuTest() {

    }

    @Test
    public void deckStillExistsOnDeleteConfirmationCancelButtonClickTest() {

    }

    @Test
    public void deleteConfirmationDeleteButtonIsClickableTest() {

    }

    @Test
    public void deckNoLongerExistsAfterDeleteConfirmationClickTest() {

    }
}
