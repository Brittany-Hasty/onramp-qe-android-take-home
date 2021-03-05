package com.sxmp.apprentice;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.internal.MDRootLayout;
import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.R;
import com.ichi2.ui.FixedEditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
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
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class RemoveNoteCardDeckTest {
    private ActivityTestRule<DeckPicker> activityRule =
            new ActivityTestRule<>(DeckPicker.class, false, false);

    private GrantPermissionRule grantRule =
            GrantPermissionRule.grant(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE);

    private void addCardsToSampleDeck() {
        onView(withId(R.id.fab_expand_menu_button)).perform(click());
        onView(withId(R.id.add_note_action)).perform(click());
        onView(withId(R.id.note_type_spinner)).perform(scrollTo(), click());
        onView(withText("Basic (type in the answer)")).perform(click());
        onView(withId(R.id.note_deck_spinner)).perform(scrollTo(), click());
        onView(withText("sample")).perform(click());
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Front")))
                .perform(replaceText("Test"), closeSoftKeyboard());
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Back")))
                .perform(replaceText("Test"), closeSoftKeyboard());
        onView(allOf(withId(R.id.action_save), withContentDescription("Save"))).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
    }

    private void clickDeleteOnDeckWithCards() {
        addCardsToSampleDeck();
        onView(allOf(withId(R.id.DeckPickerHoriz), hasDescendant(allOf(withId(R.id.deckpicker_name)
                , withText("sample"))))).perform(longClick());
        onView(allOf(withId(R.id.md_contentRecyclerView), childAtPosition(withId(R.id.md_contentListViewFrame)
                ,0))).perform(actionOnItemAtPosition(4, click()));
    }

    @Rule
    public RuleChain ruleChain = RuleChain.outerRule(grantRule).around(activityRule);

    @Before
    public void setup() {
        // App needs to be open
        activityRule.launchActivity(null);

        // A sample deck needs to exist
        onView(withId(R.id.fab_expand_menu_button)).perform(click());
        onView(withId(R.id.add_deck_action)).perform(click());
        onView(allOf(instanceOf(FixedEditText.class), isDisplayed()))
                .perform(scrollTo(), replaceText("sample"), closeSoftKeyboard());
        onView(allOf(withId(R.id.md_buttonDefaultPositive), withText("OK"), isDisplayed()))
                .perform(click());
    }

    @Test
    public void longClickDeckNameGivesSubmenuTest() {
        onView(allOf(withId(R.id.DeckPickerHoriz), hasDescendant(allOf(withId(R.id.deckpicker_name)
                , withText("sample"))))).perform(longClick());

        onView(allOf(instanceOf(MDRootLayout.class), withChild(withId(R.id.md_titleFrame))));
    }

    @Test
    public void longClickSubmenuIncludesDeleteDeckTest() {
        onView(allOf(withId(R.id.DeckPickerHoriz), hasDescendant(allOf(withId(R.id.deckpicker_name)
                , withText("sample"))))).perform(longClick());
        onView(allOf(withId(R.id.md_title), withText("Delete Deck"), isDescendantOfA(allOf(withId(R.id.DeckPickerHoriz)
                , hasDescendant(allOf(withId(R.id.deckpicker_name) , withText("sample")))))));
    }

    @Test
    public void deleteDeckIsClickableInSubmenuTest() {
        onView(allOf(withId(R.id.DeckPickerHoriz), hasDescendant(allOf(withId(R.id.deckpicker_name)
                , withText("sample"))))).perform(longClick());
        onView(allOf(withId(R.id.md_contentRecyclerView), childAtPosition(withId(R.id.md_contentListViewFrame)
                ,0))).perform(actionOnItemAtPosition(4, click()));
    }

    @Test
    public void emptyDeckDeletesWithoutConfirmationTest() {
        onView(allOf(withId(R.id.DeckPickerHoriz), hasDescendant(allOf(withId(R.id.deckpicker_name)
                , withText("sample"))))).perform(longClick());
        onView(allOf(withId(R.id.md_contentRecyclerView), childAtPosition(withId(R.id.md_contentListViewFrame)
                ,0))).perform(actionOnItemAtPosition(4, click()));
        onView(Matchers.allOf(withId(R.id.files), withParent(Matchers.allOf(withId(R.id.deck_picker_content),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void deckWithCardsBringsUpConfirmationOnDeleteTest() {
        clickDeleteOnDeckWithCards();
        onView(allOf(withParent(allOf(withId(android.R.id.content),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void warningIconAppearsOnDeleteConfirmationTest() {
        clickDeleteOnDeckWithCards();
        onView(withId(R.id.md_icon))
                .check(matches(isDisplayed()));
    }

    @Test
    public void deleteConfirmationIncludesDeleteQuestionTextTest() {
        clickDeleteOnDeckWithCards();
        onView(withId(R.id.md_title))
                .check(matches(withText("Delete deck?")));
    }

    @Test
    public void deleteConfirmationIncludesDeckNameAndCardNumberTest() {
        clickDeleteOnDeckWithCards();
        onView(withId(R.id.md_content))
                .check(matches(withText("Delete all cards in 'sample'? It contains 1 card.")));
    }

    @Test
    public void deleteConfirmationHasCancelButtonTest() {
        clickDeleteOnDeckWithCards();
        onView(withId(R.id.md_buttonDefaultNegative))
                .check(matches(withText("CANCEL")));
    }

    @Test
    public void deleteConfirmationCancelButtonIsClickableTest() {
        clickDeleteOnDeckWithCards();
        onView(withId(R.id.md_buttonDefaultNegative))
                .perform(click());
    }

    @Test
    public void deleteConfirmationCancelButtonReturnsToMenuTest() {
        clickDeleteOnDeckWithCards();
        onView(withId(R.id.md_buttonDefaultNegative))
                .perform(click());
//                .check(matches(withId(R.id.files)));
    }

    @Test
    public void deckStillExistsOnDeleteConfirmationCancelButtonClickTest() {
        clickDeleteOnDeckWithCards();
        onView(withId(R.id.md_buttonDefaultNegative))
                .perform(click()).check(matches(withText("sample")));
    }

    @Test
    public void deleteConfirmationDeleteButtonIsClickableTest() {
        clickDeleteOnDeckWithCards();
        onView(allOf(withId(R.id.md_buttonDefaultPositive), withText("Delete")
                , isDisplayed())).perform(click());
    }

    @Test
    public void deckNoLongerExistsAfterDeleteConfirmationClickTest() {
        clickDeleteOnDeckWithCards();
        onView(allOf(withId(R.id.md_buttonDefaultPositive), withText("Delete")
                , isDisplayed())).perform(click());
        onView(allOf(withId(R.id.deckpicker_name)
                , withText("sample"))).check(doesNotExist());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }


            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
