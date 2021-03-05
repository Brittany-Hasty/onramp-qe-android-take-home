## Features Tested
 1. Adding notecard decks
 2. Removing notecard decks
 3. Displaying the front and back of notecards
 4. Editing notecard type styles
 5. Adding types to notecards
 6. Adding HTML to notecards
 7. Adding images to notecards
 8. Adding tags to notecards
## Why I Selected These Features
## Feature Edge Cases Considered 
## ViewMatchers Used
## ViewActions Used
## ViewAssertions Used
## Views Tested
## Why I Selected These Views
## Problems and Solutions (To be better explained later)
Problem 1:

    I needed a special @Before rule for about a third of my functions, but not all of them
    
    I added a private void function to navigate to the form I needed to test, and called it at the beginning of the necessary tests

Problem 2:
    During coding of 'AddNoteCardDeckTest'
    in function 'newDeckContainsNoCardsInFirstColumnTest'
    got stuck trying to target the first column of the deck containing the text 'testing' and then checking if it equaled 0 - the call originally used 6 instances of the 'withParent' function, which got confusing really quickly
    
    structure problem:
              parent
    name     |    info  <-- I need both of these (information nested deep inside info), so it was ascending and then descending a heavily nested structure
    
    instead, broke the problem into two pieces: selected the deck containing the text 'testing' and then used 'selectedDescendantsMatch' to find the descendant with the correct id and see if it matched

Problem 3:
    
    I was having difficulty finding element ids or identifying information to be used wih my ViewMatchers
    
    I used the XML layout to find ids initially but the volume of those files took a lot of time to sift through to find the information I needed
    
    After looking up if the Android emulator had anything similar to 'Inspect element', I found out about the layout inspector, which solved my problem
     
Problem 4:

    The emulated Android device had the repeating error "system UI is not responding"
    
    After getting a message on Android Studio that my IDE was low on memory, I allocated more memory to it, and this solved the unresponsive emulator as well

Problem 5:

    Having little prior experience with Android Studio, I had to learn Android's file structure and how to implement tests
    
    I spent a while getting familiar with the files in the file explorer, and then some of the features of Android Studio.
    
    After I found the project folder dropdown menu, I was able to filter the files by category, including 'tests'
    
    By process of elimaination, the lint-rules folder was for the code, not the appp, and the api folder already had some rudimentary tests.
    
    That left only the AnkiDroid folder, which had two additional folders. Once again, one of those folders was already filled with numberous tests, so I looked inside the other folder.
    
    This folder had one file only, SampleDeckPickerTest, which had an empty sample function 'oneOfYourTests()'. I came to the conclusion that this was the necessary folder for the tests.
    
    After familiarizing myself with the environment a little more, I looked up the default testing folders and found them to match the conclusions I had made.
    
    