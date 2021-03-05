package com.sxmp.apprentice;

import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.R;
import com.ichi2.ui.FixedEditText;

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
import static androidx.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.instanceOf;

public class AddNoteCardDeckTest {

    private ActivityTestRule<DeckPicker> activityRule =
            new ActivityTestRule<>(DeckPicker.class, false, false);

    private GrantPermissionRule grantRule =
            GrantPermissionRule.grant(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE);

    private void navigateToNewDeckForm() {
        // The new deck form must be open
        onView(withId(R.id.fab_expand_menu_button)).perform(click());
        onView(withId(R.id.add_deck_action)).perform(click());
    }

    private void createTestDeck() {
        navigateToNewDeckForm();
        onView(allOf(instanceOf(FixedEditText.class), isDisplayed()))
                .perform(scrollTo(), replaceText("testing"), closeSoftKeyboard());
        onView(allOf(withId(R.id.md_buttonDefaultPositive), withText("OK"), isDisplayed()))
                .perform(click());
    }

    @Rule
    public RuleChain ruleChain = RuleChain.outerRule(grantRule).around(activityRule);

    @Before
    public void setup() {
        activityRule.launchActivity(null);
    }

    @Test
    public void plusButtonAppearsOnHomeScreenTest() {
        // '+' button exists
        onView(allOf(withId(R.id.fab_expand_menu_button), withContentDescription("Add")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void plusButtonIsClickableTest() {
        // click '+' button
        onView(withId(R.id.fab_expand_menu_button)).perform(click());
    }

    @Test
    public void plusOptionsContainsCreateDeckTest() {
        // 'create deck' appears in list
        onView(allOf(withText("Create deck"), withParent(withId(R.id.add_content_menu))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void plusOptionsContainsFolderIconTest() {
        // folder icon appears in list
        onView(withId(R.id.add_deck_action)).check(matches(isDisplayed()));
    }

    @Test
    public void folderIconIsClickableTest() {
        // click folder icon
        onView(withId(R.id.add_deck_action)).perform(click());
    }

    @Test
    public void formExistsAfterNewDeckClickTest(){
        // form exists after click
        navigateToNewDeckForm();
        onView(withId(R.id.md_root)).check(matches(isDisplayed()));
    }

    @Test
    public void createDeckTextInNewDeckFormTest() {
        // 'create deck' text is on form
        navigateToNewDeckForm();
        onView(allOf(withId(R.id.md_title), isDisplayed()))
                .check(matches(anyOf(withText("Create deck"))));
    }

    @Test
    public void newDeckFormContainsTextInputTest() {
        // text input form appears
        navigateToNewDeckForm();
        onView(allOf(withParent(withParent(withId(R.id.md_customViewFrame))), isDisplayed()))
                .check(matches(isDisplayed()));
    }

    @Test
    public void newDeckFormTextInputIsEmptyTest() {
        // text form is blank
        navigateToNewDeckForm();
        onView(allOf(instanceOf(FixedEditText.class), isDisplayed()))
                .check(matches(withText("")));
    }

    @Test
    public void newDeckFormContainsCancelButtonTest() {
        // 'cancel' button appears
        navigateToNewDeckForm();
        onView(allOf(withId(R.id.md_buttonDefaultNegative), isDisplayed()))
                .check(matches(withText("CANCEL")));
    }

    @Test
    public void newDeckFormContainsOKButtonTest() {
        // 'ok' button appears
        navigateToNewDeckForm();
        onView(allOf(withId(R.id.md_buttonDefaultPositive), isDisplayed()))
                .check(matches(withText("OK")));
    }

    @Test
    public void newDeckTextInputTakesTextTest() {
        // type 'testing' into form
        navigateToNewDeckForm();
        onView(allOf(instanceOf(FixedEditText.class), isDisplayed()))
                .perform(scrollTo(), replaceText("testing"), closeSoftKeyboard());
    }

    @Test
    public void newDeckTextInputContainsTypedTextTest() {
        // check that form contains the text 'testing'
        navigateToNewDeckForm();
        onView(allOf(instanceOf(FixedEditText.class), isDisplayed()))
                .perform(scrollTo(), replaceText("testing"), closeSoftKeyboard())
                .check(matches(withText("testing")));
    }

    @Test
    public void newDeckFormOKButtonIsClickableTest() {
        // click ok button
        navigateToNewDeckForm();
        onView(allOf(instanceOf(FixedEditText.class), isDisplayed()))
                .perform(scrollTo(), replaceText("testing"), closeSoftKeyboard());
        onView(allOf(withId(R.id.md_buttonDefaultPositive), withText("OK"), isDisplayed()))
                .perform(click());
    }

    @Test
    public void newDeckElementAppearsAfterCreationTest() {
        // check that a new deck appears and has the text 'testing'
        createTestDeck();
        onView(allOf(withId(R.id.deckpicker_name), withText("testing"))).check(matches(isDisplayed()));
    }

    @Test
    public void newDeckContainsNoCardsInFirstColumnTest() {
        // check that the new deck has no cards listed in column 1
        createTestDeck();
        onView(allOf(withId(R.id.deckpicker_name), withText("testing")))
                .check(selectedDescendantsMatch(withId(R.id.deckpicker_new), withText("0")));
    }

    @Test
    public void newDeckContainsNoCardsInSecondColumnTest() {
        // check that the new deck has no cards listed in column 2
        createTestDeck();
        onView(allOf(withId(R.id.deckpicker_name), withText("testing")))
                .check(selectedDescendantsMatch(withId(R.id.deckpicker_lrn), withText("0")));
    }

    @Test
    public void newDeckContainsNoCardsInThirdColumnTest() {
        createTestDeck();// check that the new deck has no cards listed in column 3
        onView(allOf(withId(R.id.deckpicker_name), withText("testing")))
                .check(selectedDescendantsMatch(withId(R.id.deckpicker_rev), withText("0")));
    }

    @Test
    public void newDeckEntryIsClickableTest() {
        // click the new 'testing' deck
        createTestDeck();
        onView(allOf(withId(R.id.files), hasDescendant(allOf(withId(R.id.deckpicker_name), withText("testing")))))
                .perform(click());
    }
}
