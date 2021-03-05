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

import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class AddNoteCardTypesTest {
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

        // Open the action menu
    }

    @Test
    public void manageNoteTypesAppearsInActionMenuTest() {
        onView(allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        4),
                                1),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.title), withText("Manage note types"),
                        withParent(withParent(withId(R.id.content))),
                        isDisplayed())).check(matches(withText("Manage note types")));
    }

    @Test
    public void manageNoteTypesIsClickableTest() {
        onView(allOf(withId(R.id.title), withText("Manage note types"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed())).perform(click());
    }

    @Test
    public void manageNoteTypesClickDisplaysNoteTypesListTest() {
        onView(allOf(withId(R.id.note_type_browser_list),
                        withParent(withParent(withId(R.id.root_layout))),
                        isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void noteTypesListContainsTextManageNoteTypesTest() {
        onView(allOf(withText("Manage note types"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed())).check(matches(withText("Manage note types")));
    }

    @Test
    public void noteTypesListContainsTotalNumberOfTypesTest() {
        onView(allOf(withText("20 note types available"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed())).check(matches(withText("20 note types available")));
    }

    @Test
    public void noteTypesListContainsTypesTest() {
        // Look for 'Basic', it is a default type that will exist
        onView(allOf(withId(R.id.model_list_item_1), withText("Basic"),
                        withParent(withParent(withId(R.id.note_type_browser_list))),
                        isDisplayed())).check(matches(withText("Basic")));
    }

    @Test
    public void noteTypesListContainsActionMenuTest() {
        onView(allOf(withId(R.id.action_add_new_note_type), withContentDescription("Add note type"),
                        withParent(withParent(withId(R.id.toolbar))),
                        isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void typeListActionMenuIsClickableTest() {
        onView(allOf(withId(R.id.action_add_new_note_type), withContentDescription("Add note type"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        4),
                                0),
                        isDisplayed())).perform(click());
    }

    @Test
    public void typeListActionMenuClickBringsUpNewTypeFormTest() {
        onView(allOf(withId(R.id.md_title), withText("Add note type"),
                        withParent(allOf(withId(R.id.md_titleFrame),
                                withParent(withId(R.id.md_root)))),
                        isDisplayed())).check(matches(withText("Add note type")));
    }

    @Test
    public void newTypeFormContainsCancelButtonTest() {
        onView(allOf(withId(R.id.md_buttonDefaultNegative), withText("CANCEL"),
                        withParent(allOf(withId(R.id.md_root),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed())).check(matches(withText("CANCEL")));
    }


    @Test
    public void newTypeFormOkButtonIsClickableTest() {
        onView(allOf(withId(R.id.md_buttonDefaultPositive), withText("OK"),
                        withParent(allOf(withId(R.id.md_root),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed())).check(matches(withText("OK")));
    }

    @Test
    public void newTypeFormContainsCloneDropdownTest() {
        onView(allOf(withId(R.id.dropdown_deck_name), withText("Add: Basic"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed())).check(matches(isDisplayed()));
    }

//    @Test
//    public void newTypeFormCancelButtonIsClickableTest() {
//
//    }
//
//    @Test
//    public void newTypeFormCancelButtonClickReturnsToTypeListTest() {
//
//    }
//
//    @Test
//    public void newTypeFormContainsOkButtonTest() {
//
//    }
//    @Test
//    public void newTypeFormOkButtonClickReturnsToTypeListTest() {
//
//    }
//
//    @Test
//    public void newTypeAddedToTypeListOnCreationTest() {
//
//    }
//    @Test
//    public void typeListContainsTypesTotalNotesTest() {
//
//    }
//
//    @Test
//    public void newlyCreatedTypeCanBeAddedToCardTest() {
//
//    }

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
