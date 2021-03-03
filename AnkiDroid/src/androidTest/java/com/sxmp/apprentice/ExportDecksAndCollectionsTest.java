package com.sxmp.apprentice;

import androidx.test.rule.GrantPermissionRule;

import org.junit.Before;
import org.junit.Test;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ExportDecksAndCollectionsTest {

    // Two permissions needed - file read / write access to save flashcards

    private GrantPermissionRule grantRule =
            GrantPermissionRule.grant(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE);

    @Before
    public void setup() {
        // Add a sample flashcard deck using Mockito

    }

    @Test
    public void longClickFlashcardDeck() {
        // One of many steps to be added later

    }
}
