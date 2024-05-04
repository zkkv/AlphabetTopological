# Solution to a JetBrains Internship Assigment - Topological Sorting of Alphabet

## Description
Alex heard that the list of names in publications is sorted in lexicographical order. Alex is very vain, he tries to figure the alphabet in which his name will appear first in the publication. Help Alex to write a program that would calculate an alphabet in which a given list of names would be lexicographically sorted or determine that this is impossible.

Programs are accepted in Java, Kotlin, Groovy. As an answer, please send a link to Github with the solution.

**Input**:

Sent to standard input. The first line contains an integer n (1 ≤ n ≤ 100), the number of names.

Each of the next n lines contains one word name%i , denoting the i-th name. Each name contains only lowercase letters of the Latin alphabet, no more than 100 characters. All names are different.

**Output**:

Sent to standard output.
If there is an order of letters such that the names in the given list appear in lexicographical order, print any such order as a permutation of the characters 'a'–'z' (in other words, print the first letter of the modified alphabet first, then the second, and so on) .

Otherwise, print the single word "Impossible" (without quotes).

## How to Build
1. Start by cloning the repository and navigating to the root folder.
2. Open the terminal.
3. Run one of the following.
- Mac/Linux:
```
./gradlew build
```
- Windows:
```
gradlew build
```
Alternatively, use an IDE in which you can execute Gradle `build` configuration.

## How to Run
Similarly to `build`, execute `run` and then input the values into standard in. You can also execute `test` to see all unit tests passing.

## Solution Summary
1. We will build a directed graph with a character `x` pointing to another character `y` if `x` has to come before `y` in the alphabet.
2. Loop over all adjacent pairs of names.
3. Compare the names character by character.
4. As soon as two characters are different make the character from the earlier name point to the character in the subsequent name in the graph and move onto the next pair.
5. Figure out if the graph is acyclic by looking for cycles from each possible node in a DFS fashion. If the graph has cycles, there's no topological sorting, i.e. the result is `Impossible`.
6. If the graph is acyclic, return the topological sorting.

## Developer
Developed by zkkv, 05/2024
