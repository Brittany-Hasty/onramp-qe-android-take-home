package com.sxmp.apprentice;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.ichi2.anki.DeckPicker;
import com.ichi2.anki.IntentHandler;
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

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import static android.Manifest.permission.CAMERA;
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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

public class AddNoteCardImageTest {
    private ActivityTestRule<DeckPicker> activityRule =
            new ActivityTestRule<>(DeckPicker.class, false, false);

    private GrantPermissionRule grantRule =
            GrantPermissionRule.grant(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA);

    private void navigateToFrontImageUploadMenu() {
        onView(allOf(withId(R.id.id_media_button), withContentDescription("Attach multimedia content to the Front field"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                1),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.title), withText("Add image"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed())).perform(click());
    }

    @Rule
    public ActivityTestRule<IntentHandler> mActivityTestRule = new ActivityTestRule<>(IntentHandler.class);

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
    public void newCardFormFrontContainsMediaLinkTest() {
        onView(allOf(withId(R.id.id_media_button), withContentDescription("Attach multimedia content to the Front field"),
                        withParent(allOf(withId(R.id.constraint_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void newCardFormBackContainsMediaLinkTest() {
        onView(allOf(withId(R.id.id_media_button), withContentDescription("Attach multimedia content to the Back field"),
                        withParent(allOf(withId(R.id.constraint_layout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void frontMediaLinkListContainsImageInputTest() {
        onView(allOf(withId(R.id.id_media_button), withContentDescription("Attach multimedia content to the Front field"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                1),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.title), withText("Add image"),
                        withParent(withParent(withId(R.id.content))),
                        isDisplayed())).check(matches(withText("Add image")));
    }

    @Test
    public void backMediaLinkListContainsImageInputTest() {
        onView(allOf(withId(R.id.id_media_button), withContentDescription("Attach multimedia content to the Back field"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                1),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.title), withText("Add image"),
                        withParent(withParent(withId(R.id.content))),
                        isDisplayed())).check(matches(withText("Add image")));
    }

    @Test
    public void frontMediaListAddImageIsClickableTest() {
        onView(allOf(withId(R.id.id_media_button), withContentDescription("Attach multimedia content to the Front field"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                1),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.title), withText("Add image"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed())).perform(click());
    }

    @Test
    public void backMediaListAddImageIsClickableTest() {
        onView(allOf(withId(R.id.id_media_button), withContentDescription("Attach multimedia content to the Back field"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                1),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.title), withText("Add image"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed())).perform(click());
    }

    @Test
    public void addImagePullsUpGalleryOrCameraFormTest() {
        navigateToFrontImageUploadMenu();
        onView(allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                        withParent(allOf(withId(R.id.scrollView1),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void addImageFromGalleryIsClickableTest() {
        navigateToFrontImageUploadMenu();
        onView(allOf(withText("Gallery"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                                        childAtPosition(
                                                withId(R.id.scrollView1),
                                                0)),
                                3))).perform(scrollTo(), click());
    }

    @Test
    public void addImageFromCameraIsClickableTest() {
        navigateToFrontImageUploadMenu();
        onView(allOf(withText("Camera"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                                        childAtPosition(
                                                withId(R.id.scrollView1),
                                                0)),
                                4))).perform(scrollTo(), click());
    }

    @Test
    public void addImageFromGalleryBringsUpGalleryTest() {
        navigateToFrontImageUploadMenu();
        onView(allOf(withText("Gallery"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                                        childAtPosition(
                                                withId(R.id.scrollView1),
                                                0)),
                                3))).perform(scrollTo(), click());
        onView(allOf(withText("Select a photo"),
                        withParent(allOf(withId(com.google.android.apps.photos.R.id.toolbar),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed())).check(matches(withText("Select a photo")));
    }

    @Test
    public void addImageFromCameraBringsUpCameraTest() {
        navigateToFrontImageUploadMenu();
        onView(allOf(withText("Camera"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                                        childAtPosition(
                                                withId(R.id.scrollView1),
                                                0)),
                                4))).perform(scrollTo(), click());
//        onView(allOf(withId(com.android.camera2.R.id.progress_overlay),
//                        withParent(withParent(withId(com.android.camera2.R.id.module_layout))),
//                        isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void addImageFromCameraCanTakePhotoOnClickTest() {

    }

    @Test
    public void addImageFromCameraUploadBringsUpMenuTest() {

    }

    @Test
    public void imageUploadMenuAsksAboutCroppingTest() {
        onView(allOf(withId(R.id.md_content), withText("Do you want to crop this image?"),
                        withParent(allOf(withId(R.id.md_contentScrollView),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed())).check(matches(withText("Do you want to crop this image?")));
    }

    @Test
    public void cropImageAppearsOnImageUploadMenuTest() {
        onView(allOf(withText("CROP IMAGE"),
                        withParent(allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                                withParent(withId(R.id.scrollView1)))))).check(matches(isDisplayed()));
    }

    @Test
    public void uploadedImagePreviewAppearsOnImageUploadMenuTest() {
        onView(allOf(withParent(allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                        withParent(withId(R.id.scrollView1)))))).check(matches(isDisplayed()));
    }

    @Test
    public void uploadedImageFileSizeAppearsOnImageUploadMenuTest() {
        onView(allOf(withText("36.38 kB"),
                        withParent(allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                                withParent(withId(R.id.scrollView1)))),
                        isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void galleryButtonStillAppearsOnMenuAfterImageUploadTest() {
        onView(allOf(withText("GALLERY"),
                        withParent(allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                                withParent(withId(R.id.scrollView1)))),
                        isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void cameraButtonStillAppearsOnMenuAfterImageUploadTest() {
        onView(allOf(withText("CAMERA"),
                        withParent(allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                                withParent(withId(R.id.scrollView1)))),
                        isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void cardFormAcceptsFilledOutImageCardTest() {
        onView(allOf(withId(R.id.id_media_button), withContentDescription("Attach multimedia content to the Back field"),
                        childAtPosition(
                                allOf(withId(R.id.constraint_layout),
                                        childAtPosition(
                                                withClassName(is("com.ichi2.anki.FieldEditLine")),
                                                0)),
                                1),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.title), withText("Add image"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed())).perform(click());

        onView(allOf(withText("Camera"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayoutInScrollViewFieldEdit),
                                        childAtPosition(
                                                withId(R.id.scrollView1),
                                                0)),
                                4))).perform(scrollTo(), click());

        onView(allOf(withId(R.id.md_buttonDefaultNegative), withText("No"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed())).perform(click());

        onView(allOf(withId(R.id.multimedia_edit_field_done), withContentDescription("Done"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        4),
                                1),
                        isDisplayed())).perform(click());

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
    }

    @Test
    public void cardRendersImageTest() {
        // Better suited for DisplayNoteCardFrontBackTest?
    }

    @Test
    public void newImageCardShowsUpInDeckTest() {

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
