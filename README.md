
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
Form input validation has always been particularly interesting to me due to potentially malicious actions by the user. In addition, the user may also use these fields to handle unexpected values, whether they are provided as empty fields in forms, a field value of 'null', or an incorrect file type during upload.

These features were also chosen due to the relative frequency of their use. Since notecard manipulation is the main focus of this application, more tests should be carried in this area for the greatest impact on user satisfaction.

## How Testing Was Used In Each Feature
Adding notecard decks: To verify the new deck form worked and contained appropriate input elements
Removing notecard decks: To verify that decks contained a deletion option and it functioned correctly
Displaying the front and back of notecards: To verify that card displays were not empty and reflected content
Editing notecard type styles: To verify that styles could be edited and reflected new HTML/CSS
Adding types to notecards: To verify that new types could be added to cards for custom displays
Adding HTML to notecards: To verify that HTML can be added to cards, renders properly, and isn't malicious
Adding images to notecards: To verify that images can render after upload through the gallery or camera
Adding tags to notecards: To verify that new and existing tags can be added in any quantity to notecards
 
## Feature Edge Cases Considered 
 - HTML script injection in notecard displays
 - Empty fields on form submission
 - Discrepancies between similar UI elements within the same form

## ViewMatchers Used  
- hasDescendant()
- isDescendantOfA()
- isDisplayed()
- withChild()
- withContentDescription()
- withId()
- withParent()
- withText()
- allOf()
- anyOf()
- instanceOf()
- withClassName()
- is()

## ViewActions Used  
- click()
- closeSoftKeyboard()
- longClick()
- replaceText()
- scrollTo()
- actionOnItemAtPosition()

## ViewAssertions Used  
- matches()
- selectedDescendantsMatch()
- doesNotExist()

## Views Tested  

 - New deck creation form
 - New card creation form
 - New tag creation form
 - New notecard type creation form
 - Notecard style form
 - Notecard display

## Why I Selected These Views  
These views were selected for the same reasons that I selected my features for testing: form validation and their frequency of use. Each of these views, aside from the notecard display itself, is a form that might encounter interesting edge cases and bugs if used incorrectly. While the notecard display is not a form, it makes up for it in the sheer amount of times that a user will see it compared to any other view on the app! That makes it the most important - and most in need of testing - to me.

## Problems and Solutions

 1. "Where do I start?"

> Having little prior experience with Android Studio, I had to learn
> Android's file structure and how to implement tests. I spent a
> while getting familiar with the files in the file explorer, and then
> some of the features of Android Studio.   After I found the project
> folder dropdown menu, I was able to filter the files by category,
> including 'tests'. By process of elimination, the lint-rules folder
> was for the code, not the app, and the api folder already had some
> rudimentary tests. That left only the AnkiDroid folder, which had two
> additional folders. Once again, one of those folders was already
> filled with numerous tests, so I looked inside the other folder. This
> folder had one file only, SampleDeckPickerTest, which had an empty
> sample function 'oneOfYourTests()'. I came to the conclusion that this
> was the necessary folder for the tests. After familiarizing myself
> with the environment a little more, I looked up the default testing
> folders and found them to match the conclusions I had made.

 2. "I really need to simplify this family tree."

> While coding the 'newDeckContainsNoCardsInFirstColumnTest'  function
> inside 'AddNoteCardDeckTest', I encountered some difficulty trying to
> target the first column of the deck containing the text 'testing' and
> then checking if it equaled 0.  In order to access this data, I needed
> to target a row with some identifying text, then ascend its family
> tree, right before descending once again to target the next data I
> required. Due to the nesting of this tree, the call originally used 6
> instances of the 'withParent' function, which got confusing really
> quickly. 

> While researching different ViewMatchers, I came across the
> 'selectedDescendantsMatch' function, which removed all but one of the
> previous 'withParent' calls. Now, I look for the parent containing a
> text element with the deck name I need, and then use the
> 'selectedDescendantsMatch' function in order to find the value of its
> first empty column.

  
3. "How do I inspect this?"

> While getting started with ViewMatchers for the first time, I did not
> know where tutorials were finding the ids, classnames, or attributes
> of items on the screen. Luckily, due to my previous exploration of
> the various project folders, I knew about the folder containing the
> XML layouts for the different views in the app. I used these XML files
> at first, however, the volume of those files took a lot of time to
> sift through to find the information I needed. After looking up if the
> Android emulator had anything similar to 'Inspect element', I found
> out about the layout inspector, which ended up faster, more
> descriptive, and wasn't view specific.

     
4. "The Android emulator keeps going idle."

> This problem did not stop me from making progress on test writing, but
> did cause a frequent annoying popup on the emulated Android device.
> The repeating error "system UI is not responding" would appear if I
> did not interact with the emulator for a while, and had to be
> dismissed before my tests could run. If I was not dismissed prior to
> test runs, it would end with incorrect results or it would crash. I
> found the solution to this by coincidence - after getting a message on
> Android Studio that my IDE was low on memory, I allocated more memory
> to it, also removing the emulator error.

  
5. "I need a setup to happen before some functions, but not all."

> I wanted to make each of my test functions runnable in isolation, and
> this required their setups to be included at the beginning of each of
> them. Some rules were fine within the @Before function because every
> function ran them, but I often ran into the same mass of repeating
> lines for around a third of my functions. In the interest of keeping
> my code dry, I instead added a private void function to navigate to
> the form I needed to test, and called it at the beginning of these
> functions.

