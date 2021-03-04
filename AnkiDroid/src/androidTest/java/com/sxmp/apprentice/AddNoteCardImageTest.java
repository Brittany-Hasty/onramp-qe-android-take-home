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

public class AddNoteCardImageTest {
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

    }

    // Steps (??? is used to denote unknown working behavior):
    // - Click link image next to 'Front'
    // - Click 'Add image'
    // - Click 'Gallery' or 'Camera'

    // - Path 1 (Gallery):
    // - ???

    // - Path 2 (Camera):
    // - Click camera icon
    // - Click check mark
    // - Click 'no' or 'ok' on crop prompt

    // - If 'ok' was clicked on crop prompt, ???

    // - Click 'Crop image'
    // - ???

    // - Check that form is filled with an img tag

    @Test
    public void newCardFormFrontContainsMediaLinkTest() {

    }

    @Test
    public void newCardFormBackContainsMediaLinkTest() {

    }

    @Test
    public void frontMediaLinkListContainsImageInputTest() {

    }

    @Test
    public void backMediaLinkListContainsImageInputTest() {

    }

    @Test
    public void frontMediaListAddImageIsClickableTest() {

    }

    @Test
    public void backMediaListAddImageIsClickableTest() {

    }

    @Test
    public void frontAddImagePullsUpGalleryOrCameraFormTest() {

    }

    @Test
    public void backAddImagePullsUpGalleryOrCameraFormTest() {

    }

    @Test
    public void addImageFromGalleryIsClickableTest() {

    }

    @Test
    public void addImageFromCameraIsClickableTest() {

    }

    @Test
    public void addImageFromGalleryBringsUpGalleryTest() {

    }

    @Test
    public void addImageGalleryViewContainsSelectPhotoTextTest() {

    }

    @Test
    public void addImageGalleryPhotoIsClickableTest() {

    }

    @Test
    public void addImageGalleryUploadBringsUpMenuTest() {

    }

    @Test
    public void addImageFromCameraBringsUpCameraTest() {

    }

    @Test
    public void addImageFromCameraCanTakePhotoOnClickTest() {

    }

    @Test
    public void addImageFromCameraUploadBringsUpMenuTest() {

    }

    @Test
    public void imageUploadMenuAsksAboutCroppingTest() {
        // Is this camera specific?
    }

    @Test
    public void imageUploadCroppingPopupBringsUpCroppingMenuTest() {

    }

    @Test
    public void cropImageAppearsOnImageUploadMenuTest() {

    }

    @Test
    public void uploadedImagePreviewAppearsOnImageUploadMenuTest() {

    }

    @Test
    public void uploadedImageFileSizeAppearsOnImageUploadMenuTest() {

    }

    @Test
    public void galleryButtonStillAppearsOnMenuAfterImageUploadTest() {

    }

    @Test
    public void cameraButtonStillAppearsOnMenuAfterImageUploadTest() {

    }

    @Test
    public void cardFormFrontImageInputTakesCameraImageTest() {

    }

    @Test
    public void cardFormBackImageInputTakesCameraImageTest() {

    }

    @Test
    public void cardFormFrontImageInputTakesGalleryImageTest() {

    }

    @Test
    public void cardFormBackImageInputTakesGalleryImageTest() {

    }

    @Test
    public void cardFormDoesNotAcceptInvalidImagesTest() {

    }

    @Test
    public void cardFormAcceptsFilledOutImageCardTest() {

    }

    @Test
    public void cardRendersImageTest() {
        // Better suited for DisplayNoteCardFrontBackTest?
    }

    @Test
    public void newImageCardShowsUpInDeckTest() {

    }
}
