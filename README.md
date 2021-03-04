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



