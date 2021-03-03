package com.sxmp.apprentice;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class AddNoteCardDeckTest {

    private ActivityTestRule<DeckPicker> activityRule =
            new ActivityTestRule<>(DeckPicker.class, false, false);

    private GrantPermissionRule grantRule =
            GrantPermissionRule.grant(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE);

    @Rule
    public RuleChain ruleChain = RuleChain.outerRule(grantRule).around(activityRule);

    @Before
    public void setup() {
        activityRule.launchActivity(null);
    }

    @Test
    public void plusButtonAppearsOnHomeScreenTest() {
        // '+' button exists
        ViewInteraction imageButton = onView(
                allOf(withId(R.id.fab_expand_menu_button), withContentDescription("Add"),
                        withParent(allOf(withId(R.id.add_content_menu),
                                withParent(withId(R.id.root_layout)))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }

    @Test
    public void plusButtonIsClickableTest() {
        // click '+' button
        ViewInteraction viewInteraction = onView(
                allOf(withId(R.id.fab_expand_menu_button), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                2)),
                                3),
                        isDisplayed()));
        viewInteraction.perform(click());
    }

    @Test
    public void plusOptionsContainsCreateDeckTest() {
        // 'create deck' appears in list
        ViewInteraction textView = onView(
                allOf(withText("Create deck"),
                        withParent(allOf(withId(R.id.add_content_menu),
                                withParent(withId(R.id.root_layout)))),
                        isDisplayed()));
        textView.check(matches(withText("Create deck")));
    }

    @Test
    public void plusOptionsContainsFolderIconTest() {
        // folder icon appears in list
        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.add_deck_action), withContentDescription("Create deck"),
                        withParent(allOf(withId(R.id.add_content_menu),
                                withParent(withId(R.id.root_layout)))),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));
    }

    @Test
    public void folderIconIsClickableTest() {
        // click folder icon
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_deck_action), withContentDescription("Create deck"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                2)),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());
    }

    @Test
    public void formExistsAfterNewDeckClickTest(){
        // form exists
        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.md_root),
                        withParent(allOf(withId(android.R.id.content),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
    }

    @Test
    public void createDeckTextInNewDeckFormTest() {
        // 'create deck' text is on form
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.md_title), withText("Create deck"),
                        withParent(allOf(withId(R.id.md_titleFrame),
                                withParent(withId(R.id.md_root)))),
                        isDisplayed()));
        textView2.check(matches(withText("Create deck")));
    }

    @Test
    public void newDeckFormContainsTextInputTest() {
        // text input form appears
        ViewInteraction editText = onView(
                allOf(withParent(withParent(withId(R.id.md_customViewFrame))),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));
    }

    @Test
    public void newDeckFormTextInputIsEmptyTest() {
        // text form is blank
        ViewInteraction editText2 = onView(
                allOf(withParent(withParent(withId(R.id.md_customViewFrame))),
                        isDisplayed()));
        editText2.check(matches(withText("")));
    }

    @Test
    public void newDeckFormContainsCancelButtonTest() {
        // 'cancel' button appears
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.md_buttonDefaultNegative), withText("CANCEL"),
                        withParent(allOf(withId(R.id.md_root),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        textView3.check(matches(withText("CANCEL")));
    }

    @Test
    public void newDeckFormContainsOKButtonTest() {
        // 'ok' button appears
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.md_buttonDefaultPositive), withText("OK"),
                        withParent(allOf(withId(R.id.md_root),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        textView4.check(matches(withText("OK")));
    }

    @Test
    public void newDeckTextInputTakesTextTest() {
        // type 'testing' into form
        ViewInteraction fixedEditText = onView(
                childAtPosition(
                        childAtPosition(
                                withId(R.id.md_customViewFrame),
                                0),
                        0));
        fixedEditText.perform(scrollTo(), replaceText("testing"), closeSoftKeyboard());
    }

    @Test
    public void newDeckTextInputContainsTypedTextTest() {
        // check that form contains the text 'testing'
        ViewInteraction editText3 = onView(
                allOf(withText("testing"),
                        withParent(withParent(withId(R.id.md_customViewFrame))),
                        isDisplayed()));
        editText3.check(matches(withText("testing")));
    }

    @Test
    public void newDeckFormOKButtonIsClickableTest() {
        // click ok button
        ViewInteraction mDButton2 = onView(
                allOf(withId(R.id.md_buttonDefaultPositive), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.md_root),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                4),
                        isDisplayed()));
        mDButton2.perform(click());
    }

    @Test
    public void newDeckElementAppearsAfterCreationTest() {
        // check that a new deck element appears
        ViewInteraction linearLayout = onView(
                allOf(withParent(allOf(withId(R.id.DeckPickerHoriz),
                        withParent(withId(R.id.files)))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));
    }

    @Test
    public void newDeckElementContainsTitleTest() {
        // check that the new deck has the text 'testing'
        ViewInteraction textView5 = onView(
                allOf(withId(R.id.deckpicker_name), withText("testing"),
                        withParent(withParent(withId(R.id.DeckPickerHoriz))),
                        isDisplayed()));
        textView5.check(matches(withText("testing")));
    }

    @Test
    public void newDeckContainsNoCardsInFirstColumnTest() {
        // check that the new deck has no cards listed in column 1
        ViewInteraction textView6 = onView(
                allOf(withId(R.id.deckpicker_new), withText("0"),
                        withParent(allOf(withId(R.id.counts_layout),
                                withParent(withId(R.id.DeckPickerHoriz)))),
                        isDisplayed()));
        textView6.check(matches(withText("0")));
    }

    @Test
    public void newDeckContainsNoCardsInSecondColumnTest() {
        // check that the new deck has no cards listed in column 2
        ViewInteraction textView7 = onView(
                allOf(withId(R.id.deckpicker_lrn), withText("0"),
                        withParent(allOf(withId(R.id.counts_layout),
                                withParent(withId(R.id.DeckPickerHoriz)))),
                        isDisplayed()));
        textView7.check(matches(withText("0")));
    }

    @Test
    public void newDeckContainsNoCardsInThirdColumnTest() {
        // check that the new deck has no cards listed in column 3
        ViewInteraction textView8 = onView(
                allOf(withId(R.id.deckpicker_rev), withText("0"),
                        withParent(allOf(withId(R.id.counts_layout),
                                withParent(withId(R.id.DeckPickerHoriz)))),
                        isDisplayed()));
        textView8.check(matches(withText("0")));
    }

    @Test
    public void newDeckEntryIsClickableTest() {
        // click the new 'testing' deck
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.files),
                        childAtPosition(
                                withId(R.id.deck_picker_content),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(3, click()));
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
