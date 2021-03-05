package com.sxmp.apprentice;

import com.ichi2.anki.DeckPicker;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

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

    }

    @Test
    public void cardWithPlainTextInFrontDisplaysPlainTextInFrontTest() {

    }

    @Test
    public void cardWithPlainTextInBackDisplaysPlainTextInBackTest() {

    }

    @Test
    public void cardWithBoldTextInFrontDisplaysBoldTextInFrontTest() {

    }

    @Test
    public void cardWithBoldTextInBackDisplaysBoldTextInBackTest() {

    }

    @Test
    public void cardWithItalicTextInFrontDisplaysItalicTextInFrontTest() {

    }

    @Test
    public void cardWithItalicTextInBackDisplaysItalicTextInBackTest() {

    }

    @Test
    public void cardWithUnderlinedTextInFrontDisplaysUnderlinedTextInFrontTest() {

    }

    @Test
    public void cardWithUnderlinedTextInBackDisplaysUnderlinedTextInBackTest() {

    }

    @Test
    public void cardWithLineBreakInFrontDisplaysLineBreakInFrontTest() {

    }

    @Test
    public void cardWithLineBreakInBackDisplaysLineBreakInBackTest() {

    }

    @Test
    public void cardFrontWithAllHeadersDecreasingRendersProperlyTest() {

    }

    @Test
    public void cardBackWithAllHeadersDecreasingRendersProperlyTest() {

    }

    @Test
    public void cardFrontWithAllFontsDecreasingRendersProperlyTest() {

    }

    @Test
    public void cardBackWithAllFontsDecreasingRendersProperlyTest() {

    }

    @Test
    public void cardWithImageInFrontDisplaysImageInFrontTest() {

    }

    @Test
    public void cardWithImageInBackDisplaysImageInBackTest() {

    }

    @Test
    public void cardWithAudioClipInFrontPlaysAudioInFrontTest() {

    }

    @Test
    public void cardWithAudioClipInBackPlaysAudioInBackTest() {

    }

    @Test
    public void cardReflectsStyleChangesTest() {
        // change background color and see if reflected in card display
    }

    @Test
    public void cardFrontNewLineInFormRendersNewLineTest() {

    }

    @Test
    public void cardBackNewLineInFormRendersNewLineTest() {

    }

    @Test
    public void cardFrontFormulaInFormRendersFormulaTest() {

    }

    @Test
    public void cardToolbarItemInFormRendersTest() {

    }

    @Test
    public void cardToolbarItemInFormRendersFormulaTest() {

    }

    @Test
    public void cardBackFormulaInFormRendersFormulaTest() {

    }
}
