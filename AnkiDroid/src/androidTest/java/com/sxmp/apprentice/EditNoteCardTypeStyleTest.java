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
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

public class EditNoteCardTypeStyleTest {
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
        onView(
                allOf(withId(R.id.md_buttonDefaultPositive), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.fab_expand_menu_button), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                2)),
                                3),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.add_note_action), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                2)),
                                2),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.CardEditorCardsText), withText("Cards: Card 2"),
                        childAtPosition(
                                allOf(withId(R.id.CardEditorCardsButton),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0))).perform(scrollTo(), click());

    }

    @Test
    public void canSeeStyleByClickingCardsOnAddNoteFormTest() {
        onView(allOf(withId(R.id.styling_edit), withText(".card {\n font-family: arial;\n font-size: 20px;\n text-align: center;\n color: black;\n background-color: white;\n}"), withContentDescription("Styling"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed())).check(matches(withText(".card {  font-family: arial;  font-size: 20px;  text-align: center;  color: black;  background-color: white; }")));

    }

    @Test
    public void cardStyleIncludesFrontTemplateTextTest() {
        onView(allOf(withText("Front template"),
                        withParent(allOf(withId(R.id.main_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed())).check(matches(withText("Front template")));
    }

    @Test
    public void cardStyleIncludesBackTemplateTextTest() {
        onView(allOf(withText("Back template"),
                        withParent(allOf(withId(R.id.main_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed())).check(matches(withText("Back template")));
    }

    @Test
    public void cardStyleIncludesStylingTextTest() {
        onView(allOf(withText("Styling"),
                        withParent(allOf(withId(R.id.main_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed())).check(matches(withText("Styling")));
    }

    @Test
    public void defaultStylingCanBeReplacedTest() {
        onView(allOf(withId(R.id.styling_edit), withText(".card {\n font-family: arial;\n font-size: 20px;\n text-align: center;\n color: black;\n background-color: black;\n}"), withContentDescription("Styling"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0))).perform(scrollTo(), replaceText(".card {\n font-family: arial;\n font-size: 20px;\n text-align: center;\n color: black;\n background-color: black;\n}"));

    }

    @Test
    public void defaultFrontTemplateExistsTest() {
        onView(allOf(withId(R.id.front_edit), withText("{{type:Back}}"), withContentDescription("Front template"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed())).check(matches(withText("{{type:Back}}")));
    }

    @Test
    public void defaultFrontTemplateCanBeReplacedTest() {
        onView(allOf(withId(R.id.front_edit), withText("{{type:Back}}"), withContentDescription("Front template"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0))).perform(scrollTo(), replaceText("{{type:Back}}\n<hr>"));

    }

    @Test
    public void defaultBackTemplateExistsTest() {
        onView(allOf(withId(R.id.back_edit), withText("{{FrontSide}}\n\n<hr id=answer>\n\n{{Front}}\n\n{{type:Back}}"), withContentDescription("Back template"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed())).check(matches(withText("{{FrontSide}}  <hr id=answer>  {{Front}}  {{type:Back}}")));

    }

    @Test
    public void defaultBackTemplateCanBeReplacedTest() {
        onView(allOf(withId(R.id.back_edit), withText("{{FrontSide}}\n\n<hr id=answer>\n\n{{Front}}\n\n{{type:Back}}"), withContentDescription("Back template"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0))).perform(scrollTo(), replaceText("{{FrontSide}}\n\n<hr id=answer>\n<hr>\n{{Front}}\n\n{{type:Back}}"));

    }

//    @Test
//    public void changingFontFamilyAppearsInCardTest() {
//
//    }
//
//    @Test
//    public void changingFontSizeAppearsInCardTest() {
//
//    }
//
//    @Test
//    public void changingTextAlignmentAppearsInCardTest() {
//
//    }
//
//    @Test
//    public void changingColorAppearsInCardTest() {
//
//    }
//
//    @Test
//    public void changingBackgroundColorAppearsInCardTest() {
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
