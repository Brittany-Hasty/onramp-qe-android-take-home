package com.sxmp.apprentice;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.R;
import com.ichi2.ui.FixedEditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
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
        onView(allOf(instanceOf(FixedEditText.class)))
                .perform(scrollTo(), replaceText("sample"), closeSoftKeyboard());
        onView(allOf(withId(R.id.md_buttonDefaultPositive), withText("OK"), isDisplayed()))
                .perform(click());

        // A card must be open to the add/edit form
        onView(withId(R.id.fab_expand_menu_button)).perform(click());
        onView(withId(R.id.add_note_action)).perform(click());

        // The form must have type 'Basic (type in the answer)' and belong to the sample deck
        onView(withId(R.id.note_type_spinner)).perform(scrollTo(), click());
        onView(withText("Basic (type in the answer)")).perform(click());
        onView(withId(R.id.note_deck_spinner)).perform(scrollTo(), click());
        onView(withText("sample")).perform(click());
    }

    @Test
    public void newCardFormContainsFrontTextInputTest() {
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Front"),
                        withParent(allOf(withId(R.id.constraint_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void cardFormFrontTextIsEmptyTest() {
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Front"),
                        withParent(allOf(withId(R.id.constraint_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed())).check(matches(withText("")));
    }

    @Test
    public void cardFormFrontTextInputTakesTextTest() {
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Front"), isDisplayed()))
                .perform(replaceText("Test"), closeSoftKeyboard());
        onView(allOf(withId(R.id.id_note_editText), withText("Test"), withContentDescription("Front"),
                        withParent(allOf(withId(R.id.constraint_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed())).check(matches(withText("Test")));
    }

    @Test
    public void newCardFormContainsBackTextInputTest() {
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Back"),
                        withParent(allOf(withId(R.id.constraint_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void cardFormBackTextIsEmptyTest() {
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Back"),
                        withParent(allOf(withId(R.id.constraint_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed())).check(matches(withText("")));
    }

    @Test
    public void cardFormBackTextInputTakesTextTest() {
        // Needs Debugging
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Back"), isDisplayed()))
                .perform(replaceText("Test"), closeSoftKeyboard());
        onView(allOf(withId(R.id.id_note_editText), withText("Test"), withContentDescription("Back"),
                withParent(allOf(withId(R.id.constraint_layout),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                isDisplayed())).check(matches(withText("Test")));
    }

    @Test
    public void cardFormDoesNotAcceptEmptyCardTest() {
        // Needs debugging, make sure to delete sample deck before it runs
        onView(allOf(withId(R.id.action_save), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        4),
                                0),
                        isDisplayed())).perform(click());

        onView(allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.note_editor_layout),
                                                0)),
                                0),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.deckpicker_new), withText("0"),
                        withParent(allOf(withId(R.id.counts_layout),
                                withParent(withId(R.id.DeckPickerHoriz)))),
                        isDisplayed())).check(matches(withText("0")));
    }

    @Test
    public void cardFormAcceptsFilledOutHTMLCardTest() {
        // Needs debugging, sometimes works, sometimes doesn't
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Front"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                4),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.note_editor_toolbar_button_horizontal_rule), withContentDescription("Insert Horizontal Line"),
                        childAtPosition(
                                allOf(withId(R.id.editor_toolbar_internal),
                                        childAtPosition(
                                                withId(R.id.toolbar_scrollview),
                                                0)),
                                3))).perform(click());

        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Back"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                4),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.note_editor_toolbar_button_horizontal_rule), withContentDescription("Insert Horizontal Line"),
                        childAtPosition(
                                allOf(withId(R.id.editor_toolbar_internal),
                                        childAtPosition(
                                                withId(R.id.toolbar_scrollview),
                                                0)),
                                3))).perform(click());

        onView(allOf(withId(R.id.action_save), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        4),
                                0),
                        isDisplayed())).perform(click());

        onView(allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.note_editor_layout),
                                                0)),
                                0),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.deckpicker_new), withText("1"),
                        withParent(allOf(withId(R.id.counts_layout),
                                withParent(withId(R.id.DeckPickerHoriz)))),
                        isDisplayed())).check(matches(withText("1")));
    }

    @Test
    public void cardFormRejectsInjectedScriptsTest() {
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Front"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                4),
                        isDisplayed())).perform(replaceText("<h1 id=\"test-element\">Script?</h1><script>document.getElementById(\"test-element\").innerHTML = \"Script!\";</script>"), closeSoftKeyboard());

        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Back"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                4),
                        isDisplayed())).perform(replaceText("<h1 id=\"test-element\">Script?</h1><script>document.getElementById(\"test-element\").innerHTML = \"Script!\";</script>"), closeSoftKeyboard());

        onView(allOf(withId(R.id.action_preview), withContentDescription("Preview"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        4),
                                1),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.preview_buttons_layout),
                        childAtPosition(
                                allOf(withId(R.id.bottom_area_layout),
                                        childAtPosition(
                                                withId(R.id.front_frame),
                                                5)),
                                1),
                        isDisplayed())).perform(click());

        onView(allOf(withText("Script?"), withParent(withParent(withId(R.id.content)))))
                .check(matches(isDisplayed()));
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
