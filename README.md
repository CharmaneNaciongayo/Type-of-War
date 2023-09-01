This repository contains all the source code and files for our original game, Type of War, a tug-of-war game with a twist! [a Java project]
This project is in fulfillment of a CSCI 22 project.

#  TYPE OF WAR
#  GAME DESCRIPTION
Type-of-War is a digital game that implements a standard Tug-of-War match as the base game wherein each player has to repeatedly press the Spacebar Key to tug on the rope. However, there’s a twist! Aside from the sheer speed shown by each player, an obstacle will pop up every 4 seconds that prompts the user to retype a given word. Until the player types in the word correctly, he or she will not be able to tug on the rope.

#  MECHANICS
Both players shall press the SPACEBAR ONCE to move to the LEFT/RIGHT, depending on where they are on the screen.
This means that the chance of either player winning is primarily dependent on how quickly they can press the spacebar key on their device, and the speed multipliers that their number of clicks in THE LAST SECOND award them.
The following speed tiers are implemented:
  - 0 Clicks in the past 1 second: 0
  - 1 Click in the past 1 second: 2
  - <= 4 Clicks in the past 1 second: 4
  - <= 8 Clicks in the past 1 second: 8

For Player 1 who is on the left side, speeds are multiplied by -1.
In order to add an extra twist to the game, both players will be prompted on screen to type a RANDOM WORD every 4 seconds.
While this prompt is active, players cannot actuate the spacebar or tug on the rope.
The prompt will automatically close after 4 seconds.
In order to close this prompt before the timer, the player has to type the given word correctly. Note that this input is case-sensitive.
If the player fails to input the word before the timer ends, he or she has lost 4 seconds of time for tugging. 
This twist is active while there is no winner yet.
Whichever player sprite goes past the center of the screen first will automatically lose. The game ends here.

# GAME INSTRUCTIONS
Once the game is open, press Start. 
Rapidly press the spacebar key to tug on the rope in your respective direction. Your chances of winning increase the faster you press.
Every 4 seconds, an input prompt will appear on your screen. Input the flashed word into the case-sensitive text field to close the prompt. The input prompt will close itself automatically after 4 seconds. You cannot close this yourself.
Repeat Step 2 and Step 3 until a winner is declared on screen.
