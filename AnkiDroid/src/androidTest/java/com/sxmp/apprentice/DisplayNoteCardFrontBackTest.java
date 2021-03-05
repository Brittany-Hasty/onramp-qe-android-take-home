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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class DisplayNoteCardFrontBackTest {
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
        onView(allOf(withId(R.id.md_buttonDefaultPositive), withText("OK"), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.fab_expand_menu_button), withContentDescription("Add"), isDisplayed())).perform(click());
        onView( allOf(withId(R.id.add_note_action), withContentDescription("Add"), isDisplayed())).perform(click());
        onView(withId(R.id.note_type_spinner)).perform(scrollTo(), click());
    }

    @Test
    public void cardWithPlainTextInFrontDisplaysPlainTextInFrontTest() {
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Front"), isDisplayed())).perform(replaceText("Hi"), closeSoftKeyboard());
        onView(allOf(withId(R.id.action_preview), withContentDescription("Preview"), isDisplayed())).perform(click());
        onView(allOf(withText("Hello"),  isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void cardWithPlainTextInBackDisplaysPlainTextInBackTest() {
        onView(allOf(withId(R.id.id_note_editText), withContentDescription("Back"), isDisplayed())).perform(replaceText("Hello"), closeSoftKeyboard());
        onView(allOf(withId(R.id.action_preview), withContentDescription("Preview"),  isDisplayed())).perform(click());
        onView(allOf(withId(R.id.preview_buttons_layout), isDisplayed())).perform(click());
        onView(allOf(withText("Hi"), isDisplayed())).check(matches(withText("Hi")));
    }

//    @Test
//    public void cardWithBoldTextInFrontDisplaysBoldTextInFrontTest() {
//
//    }
//
//    @Test
//    public void cardWithBoldTextInBackDisplaysBoldTextInBackTest() {
//
//    }
//
//    @Test
//    public void cardWithItalicTextInFrontDisplaysItalicTextInFrontTest() {
//
//    }
//
//    @Test
//    public void cardWithItalicTextInBackDisplaysItalicTextInBackTest() {
//
//    }
//
//    @Test
//    public void cardWithUnderlinedTextInFrontDisplaysUnderlinedTextInFrontTest() {
//
//    }
//
//    @Test
//    public void cardWithUnderlinedTextInBackDisplaysUnderlinedTextInBackTest() {
//
//    }
//
//    @Test
//    public void cardWithLineBreakInFrontDisplaysLineBreakInFrontTest() {
//
//    }
//
//    @Test
//    public void cardWithLineBreakInBackDisplaysLineBreakInBackTest() {
//
//    }
//
//    @Test
//    public void cardFrontWithAllHeadersDecreasingRendersProperlyTest() {
//
//    }
//
//    @Test
//    public void cardBackWithAllHeadersDecreasingRendersProperlyTest() {
//
//    }
//
//    @Test
//    public void cardFrontWithAllFontsDecreasingRendersProperlyTest() {
//
//    }
//
//    @Test
//    public void cardBackWithAllFontsDecreasingRendersProperlyTest() {
//
//    }
//
//    @Test
//    public void cardWithImageInFrontDisplaysImageInFrontTest() {
//
//    }
//
//    @Test
//    public void cardWithImageInBackDisplaysImageInBackTest() {
//
//    }
//
//    @Test
//    public void cardWithAudioClipInFrontPlaysAudioInFrontTest() {
//
//    }
//
//    @Test
//    public void cardWithAudioClipInBackPlaysAudioInBackTest() {
//
//    }
//
//    @Test
//    public void cardReflectsStyleChangesTest() {
//        // change background color and see if reflected in card display
//    }
//
//    @Test
//    public void cardFrontNewLineInFormRendersNewLineTest() {
//
//    }
//
//    @Test
//    public void cardBackNewLineInFormRendersNewLineTest() {
//
//    }
//
//    @Test
//    public void cardFrontFormulaInFormRendersFormulaTest() {
//
//    }
//
//    @Test
//    public void cardToolbarItemInFormRendersTest() {
//
//    }
//
//    @Test
//    public void cardToolbarItemInFormRendersFormulaTest() {
//
//    }
//
//    @Test
//    public void cardBackFormulaInFormRendersFormulaTest() {
//
//    }
}
