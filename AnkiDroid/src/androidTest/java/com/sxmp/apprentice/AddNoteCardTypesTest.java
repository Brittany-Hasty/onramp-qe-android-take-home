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

    }

    @Test
    public void manageNoteTypesIsClickableTest() {

    }

    @Test
    public void manageNoteTypesClickDisplaysNoteTypesListTest() {

    }

    @Test
    public void noteTypesListContainsTextManageNoteTypesTest() {

    }

    @Test
    public void noteTypesListContainsTotalNumberOfTypesTest() {

    }

    @Test
    public void noteTypesListContainsTypesTest() {
        // Look for 'Basic', it is a default type that will exist
    }

    @Test
    public void noteTypesListContainsActionMenuTest() {

    }

    @Test
    public void typeListActionMenuDisplayedAsPlusIconTest() {

    }

    @Test
    public void typeListActionMenuIsClickableTest() {

    }

    @Test
    public void typeListActionMenuClickBringsUpNewTypeFormTest() {

    }

    @Test
    public void newTypeFormContainsTextAddNoteTypeTest() {

    }

    @Test
    public void newTypeFormContainsCancelButtonTest() {

    }

    @Test
    public void newTypeFormCancelButtonIsClickableTest() {

    }

    @Test
    public void newTypeFormCancelButtonClickReturnsToTypeListTest() {

    }

    @Test
    public void newTypeFormContainsOkButtonTest() {

    }

    @Test
    public void newTypeFormOkButtonIsClickableTest() {

    }

    @Test
    public void newTypeFormOkButtonClickReturnsToTypeListTest() {

    }

    @Test
    public void newTypeAddedToTypeListOnCreationTest() {

    }

    @Test
    public void newTypeFormContainsCloneDropdownTest() {

    }

    @Test
    public void newTypeFormCloneDropdownDefaultIsBasicTest() {

    }

    @Test
    public void typeListContainsTypesTotalNotesTest() {

    }

    @Test
    public void newlyCreatedTypeCanBeAddedToCardTest() {

    }
}
