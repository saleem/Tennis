Tennis
======

Simple example to demonstrate the utility of unit tests and mocking.

Synopsis
========

This is a simple example that illustrates the virtues of writing code in a unit-test-driven manner.

The domain is "how to decide the score-call in tennis based on the number of rallies won by the server and receiver".

Requirements
============

1. When the game starts, the score call is "Love All"
2. When server wins first rally, score call is "Fifteen Love"
3. When receiver wins first rally, score call is "Love Fifteen"
4. When server wins second rally and receiver has not won any rallies, score call is "Thirty Love".
5. When both server and receiver have won three rallies, score call is "Deuce"
6. When server wins four rallies and receiver has won two rallies, score call is "Game Server"
7. Once game is over, no more rallies can be scored.
