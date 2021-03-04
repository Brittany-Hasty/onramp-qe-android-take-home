package com.sxmp.apprentice;

import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class AddNoteCardHTMLTest {
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

        // A card must be open to the add/edit form
        onView(withId(R.id.fab_expand_menu_button)).perform(click());
        onView(withId(R.id.add_note_action)).perform(click());

        // The form must have type 'Basic (type in the answer)' and belong to the sample deck
        onView(withId(R.id.note_type_spinner)).perform(click());
        onView(allOf(withId(R.id.custom_study_details_text1), withText("Basic (type in the answer)"))).perform(click());
        onView(withId(R.id.note_deck_spinner)).perform(click());
        onView(allOf(withId(R.id.custom_study_details_text1), withText("sample"))).perform(click());
    }

    @Test
    public void newCardFormContainsFrontTextInputTest() {

    }

    @Test
    public void cardFormFrontTextIsEmptyTest() {

    }

    @Test
    public void cardFormFrontTextInputTakesTextTest() {

    }

    @Test
    public void newCardFormContainsBackTextInputTest() {

    }

    @Test
    public void cardFormBackTextIsEmptyTest() {

    }

    @Test
    public void cardFormBackTextInputTakesTextTest() {

    }

    @Test
    public void cardFormDoesNotAcceptEmptyCardTest() {

    }

    @Test
    public void cardFormAcceptsFilledOutCardTest() {

    }

    @Test
    public void cardRendersHTMLTest() {

    }

    @Test
    public void cardFormRejectsInjectedScriptsTest() {

    }

    @Test
    public void newCardShowsUpInDeckTest() {

    }
}
