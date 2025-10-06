# CSC 207: Text Editor

**Author**: David Lee

## Resources Used

- _(TODO: fill me in)_
- ...
- ...

## Changelog

_(TODO: fill me in with a log of your committed changes)_

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
