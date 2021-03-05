package com.ichi2.anki;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntentHandlerTest {

    @Rule
    public ActivityTestRule<IntentHandler> mActivityTestRule = new ActivityTestRule<>(IntentHandler.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_EXTERNAL_STORAGE");


    @Test
    public void intentHandlerTest() {
        ViewInteraction mDButton = onView(
                allOf(withId(R.id.md_buttonDefaultPositive), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        mDButton.perform(click());

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

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_note_action), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.add_content_menu),
                                        childAtPosition(
                                                withId(R.id.root_layout),
                                                2)),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction fieldEditText = onView(
                allOf(withId(R.id.id_note_editText), withContentDescription("Front"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                4),
                        isDisplayed()));
        fieldEditText.perform(click());

        ViewInteraction fieldEditText2 = onView(
                allOf(withId(R.id.id_note_editText), withContentDescription("Back"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                4),
                        isDisplayed()));
        fieldEditText2.perform(click());
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
