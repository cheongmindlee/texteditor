# CSC 207: Text Editor

**Author**: David Lee

## Resources Used

- David Rhoadese my mentor from mentor sessions, helped me better understand how to implement GapBuffer.
  Specifically conceptualizing what would happen when I moved my cursor to the right and left, as well
  as resizing the char array.

## Changelog

- Implemented the SimpleString Buffer and its test suite
- Added the "analyzing runtime of SimpleStringBuffer" to read me
- Implemented GapBuffer and its test suite
- Implemented TextEditor
- Found some bugs in GapBuffer causing problems in TextEditor, so I debugged GapBuffer and fixed
  the error with the insert function
- Found and fixed the error creating the cursor and character on the same index if I made a new line
  with text to the right of my cursor
- Finished debugging and implementing TextEditor so that it can write to a file and output previous
  text inside the file to the terimnal if it existed.
- sAdded final comments and updated ReadMe.

- Changes done for Resubmission: I fixed all of my formatting errors and made sure no line was longer than 100 characters long
## Analyzing runtime of Insert in SimpleStringBuffer

The input into insert is a character that we will insert into the backing string.
The runtime of insert is T(n) = n + 1 where n is the length of the string.
The critical operations is concatanating the new string from the old string
Since Strings in Java are immutable, our process follows these steps: taking each character in the original string
that comes before the cursor and concatanating them togeather then concatanating it with the character we want to
insert and then concatantaing the result of that to, each char in the old string from the index of the cursor to the
end of the string.
Note: I used substring for this but I think this is what substring is doing behind the scenes
O(n) = n
