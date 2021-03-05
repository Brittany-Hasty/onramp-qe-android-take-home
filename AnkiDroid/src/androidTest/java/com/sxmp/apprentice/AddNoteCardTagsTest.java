package com.sxmp.apprentice;

import android.view.View;

import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.R;

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
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class AddNoteCardTagsTest {
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

        // There must be a selection of tags to choose from during tests

    }


    @Test
    public void newCardFormHasTagManagerButtonTest() {
        onView(allOf(withId(R.id.CardEditorTagText), withText("Tags: "),
                withParent(allOf(withId(R.id.CardEditorTagButton),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                isDisplayed())).check(matches(withText("Tags: ")));
    }


    @Test
    public void newCardFormTagManagerIsClickableTest() {
        onView(withId(R.id.CardEditorTagButton)).perform(scrollTo(), click());
    }


    @Test
    public void newCardTagsAreSelectableTest() {
        onView(withId(R.id.tags_dialog_tags_list)).perform(actionOnItemAtPosition(4, click()));
    }


    @Test
    public void multipleNewCardTagsAreSelectableTest() {
        onView(withId(R.id.tags_dialog_tags_list)).perform(actionOnItemAtPosition(4, click()));
        onView(withId(R.id.tags_dialog_tags_list)).perform(actionOnItemAtPosition(9, click()));
    }


    @Test
    public void selectAllTagsButtonIsClickableTest() {
        onView(allOf(withId(R.id.tags_dialog_action_select_all), withContentDescription("Check/uncheck all tags")
                , isDisplayed())).perform(click());
    }


    @Test
    public void selectAllTagsDoubleClickDeselectsAllTagsTest() {
        onView(allOf(withId(R.id.tags_dialog_action_select_all)
                , withContentDescription("Check/uncheck all tags"), isDisplayed())).perform(click());
    }
//
//    @Test
//    public void selectAllTagsButtonSelectsAllTagsTest() {
//
//    }
//    @Test
//    public void tagFormAddTagIconIsClickableTest() {
//
//    }
//
//    @Test
//    public void addTagIconBringsUpNewTagFormTestTest() {
//
//    }
//
//    @Test
//    public void addTagIconBringsUpAddTagTextTest() {
//
//    }
//
//    @Test
//    public void newTagFormHasCancelButtonTest() {
//
//    }
//
//    @Test
//    public void newTagFormHasOkButtonTest() {
//
//    }
//
//    @Test
//    public void newTagFormCancelButtonIsClickableTest() {
//
//    }
//
//    @Test
//    public void newTagFormCancelButtonClickReturnsToTagListTest() {
//
//    }
//
//    @Test
//    public void newTagFormOkButtonIsClickableTest() {
//
//    }
//
//    @Test
//    public void newTagFormTextInputFieldExistsTest() {
//
//    }
//
//    @Test
//    public void newTagFormTakesInputTest() {
//
//    }
//
//    @Test
//    public void newCardTagIsAddedToFormTest() {
//
//    }
//
//    @Test
//    public void tagFormOKButtonIsValidWithNoTagsSelectedTest() {
//
//    }
//
//    @Test
//    public void tagFormOkButtonUpdatesTagsFromSelectedToNotSelectedTest() {
//
//    }
//
//    @Test
//    public void newCardTagFieldReflectsSelectedTagsTest() {
//
//    }
//
//    @Test
//    public void newCardTagFieldIsEmptyWhenNoTagsAreSelectedTest() {
//
//    }
//
//    @Test
//    public void newCardReflectsAddedTagInputTest() {
//        // Better suited for DisplayNoteCardFrontBackTest?
//    }
//
//    @Test
//    public void newCardReflectsEmptyTagInputTest() {
//        // Better suited for DisplayNoteCardFrontBackTest?
//    }
}
