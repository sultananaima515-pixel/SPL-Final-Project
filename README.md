1.0	Introduction
Ultimate Tic Tac Toe is an advanced version of the traditional game that introduces multiple interconnected boards and additional strategic elements. Players must not only focus on winning individual boards but also plan their moves carefully to gain control of the overall game. This increases the difficulty, competitiveness, and enjoyment compared to the classic version.
This project presents a desktop-based Ultimate Tic Tac Toe game developed using Java and Java Swing following object-oriented programming principles. The application provides a modern graphical user interface and includes multiple game modes to offer different gameplay experiences. The implemented modes are:
•	Classic Tic Tac Toe (3×3) – the traditional version of the game. 
•	Ultimate Tic Tac Toe (3×3) – where each move determines the opponent's next playable board. 
•	Ultimate Tic Tac Toe (4×4) – a larger version that increases the level of strategy and challenge. 
•	Time Mode (5×5) – a unique timed mode in which players compete within a countdown timer, earn points by winning small boards, and the player with the highest score when time expires is declared the winner. 
To improve the overall user experience, the application also includes player name customization, sound effects, background music, winner highlighting, score tracking, countdown timer, reset functionality, and intuitive menu navigation. These features make the game more interactive, engaging, and enjoyable while demonstrating the practical application of Java GUI development, event handling, object-oriented design, and game logic implementation.

2.0 Problem Statement and Motivation:
2.1 Problem Statement
Although Tic Tac Toe is one of the most well-known strategy games, the traditional 3×3 version has several limitations. Due to its simple gameplay and limited board size, matches between experienced players frequently end in a draw, reducing long-term engagement and strategic depth. As a result, players have few opportunities to experience more challenging and engaging versions of the game.
To address these limitations, I develop an Ultimate Tic Tac Toe application with multiple game modes, including Classic 3×3, Ultimate 3×3, Ultimate 4×4, and a custom Time Mode (5×5). The application provides an intuitive graphical user interface, supports player name customization, audio system, highlights winners, and introduces a countdown timer with score-based competition in Time Mode. These features enhance both the strategic complexity and overall user experience while demonstrating practical applications of Java-based desktop software development.
2.2	Motivation
One of the main motivations behind the development of this project was to demonstrate how a very simple board game could be turned into something more strategic by applying principles of software engineering. Tic Tac Toe is known to all users, thus it is great choice to put into practice such aspects as object-oriented programming, GUI design, event-driven programming and game algorithms.
Another motivation was to create a game that offers greater variety than the traditional version. By introducing multiple game modes and a unique time-based mode with score tracking, the project aims to provide players with different levels of challenge while maintaining an intuitive and enjoyable experience.
From the academic point of view, this project gave the possibility to apply in practice such principles of software engineering and Java programming as modularity, reusing of classes, events management, state maintenance and user interface development. Creating a whole desktop application from scratch helped to improve programming skills and apply theoretical knowledge in practice.

3.0 Objectives and Scope Limitation
3.1 Objectives
The main aim of the project is to develop an interactive desktop based Ultimate Tic Tac Toe game through Java Swing. The game is intended to have different modes along with a pleasing graphical user interface, which shows practical implementation of the concepts of Object-Oriented Programming and Software Engineering.
The specific objectives of this project are:
a)	Develop a Multi-Mode Tic Tac Toe Game
Create a complete Tic Tac Toe application that offers multiple gameplay modes, including Classic 3×3, Ultimate 3×3, Ultimate 4×4, and Ultimate 5×5 Time Mode. Allow players to choose different game styles from a single application.
b)	Enhance Strategic Thinking
Introduce Ultimate Tic Tac Toe rules where players must think ahead before making a move. Encourage planning, prediction, and strategic decision-making instead of relying only on simple gameplay.
c)	Implement Time-Based Gameplay
Develop a special Time Mode with a countdown timer. Challenge players to capture as many small boards as possible before the timer expires, creating a faster and more competitive gaming experience.
d)	Improve User Experience
Design an attractive graphical user interface using Java Swing and AWT. Provide smooth navigation, visually highlighted active players, winner animations, sound effects, and responsive controls to make gameplay more engaging.
e)	Implement Core Game Development Concepts
Develop the game using Object-Oriented Programming principles such as encapsulation, abstraction, and modular class design. Implement game logic, move validation, winner detection, timers, event handling, and board management efficiently.
f)	Provide Interactive Audio and Visual Feedback
Enhance the gaming experience by adding background music, button click sounds, victory sounds, highlighted winning boards, active board indicators, and winner dialogs.
g)	Practice Java Programming Concepts
Demonstrate practical implementation of Java Swing, event-driven programming, timers, file organization, object-oriented programming, arrays, loops, and custom game logic through a real-world software project.
3.2 Scope 
The scope of Ultimate Tic Tac Toe is to develop a desktop-based strategic board game using Java. The project demonstrates GUI development, object-oriented programming, and game logic while providing multiple gameplay modes. The scope includes:
a) Gameplay Scope:
The game includes four different gameplay modes: Classic 3×3, Ultimate 3×3, Ultimate 4×4, and Ultimate 5×5 Time Mode. It supports two-player local multiplayer, follows the official Ultimate Tic Tac Toe rules, detects winners and draws automatically, and includes a countdown timer with score tracking in Time Mode.
b) Functional Scope:
The application provides a complete gaming experience with a front page, main menu, player name input, game mode selection, New Game and Back buttons, winner dialog, live score display, active player highlighting, and background music with sound effects to improve user interaction.
c) Technical Scope:
The project is developed using Java, Swing, and AWT while applying Object-Oriented Programming concepts. It utilizes event handling, timers, multidimensional arrays, custom game logic, and the CardLayout manager to create a modular and interactive desktop application.
d) User Scope:
The game is intended for students, Java learners, and board game enthusiasts who want to enjoy a strategic Tic Tac Toe experience while exploring the practical implementation of Java GUI programming.
e) Future Scope:
The project can be extended by adding an AI opponent, online multiplayer, save/load functionality, leaderboards, customizable themes, additional sound settings, and a mobile version to provide a richer gaming experience.
4.0 System Features Overview
The major features implemented in Ultimate Tic Tac Toe, are described below:
Feature	Description
Multi-Mode Gameplay	Supports four gameplay modes: Classic 3×3, Ultimate 3×3, Ultimate 4×4, and Ultimate 5×5 Time Mode, all accessible from a single main menu.
Active-Board Enforcement	Highlights the currently active board with a gold border while disabling non-playable boards to enforce the Ultimate Tic Tac Toe rules.
Live Win Highlighting	Displays a large translucent overlay (X, O, or Draw) on completed small boards and uses color coding (red for X, blue for O, grey for draw) for easy identification.
Current-Player Indicator	Automatically highlights the active player's information panel after every valid move, making turn tracking simple.
Score Tracking (Time Mode)	Continuously displays the number of small boards captured by each player and updates the scores throughout the game.
Countdown Timer (Time Mode)	Runs a two-minute countdown in the 5×5 Time Mode and automatically ends the match when the timer reaches zero.
Custom Player Names	Allows players to enter custom names from the main menu, which are displayed throughout the selected game mode.
In-App Game Rules	Provides a scrollable rules dialog explaining gameplay objectives, board structure, active-board mechanics, and winning conditions for every game mode.
Sound Settings	Enables players to turn background music and sound effects on or off through a dedicated settings dialog.
Consistent Navigation	Uses Java Swing's CardLayout to switch smoothly between the front page, menu, and all game modes within a single application window.

5.0 Methodology
The development of Ultimate Tic Tac Toe followed an Incremental Development Model, where the system was designed, implemented, tested, and improved in several stages. Instead of developing the entire application at once, each gameplay mode and feature was completed separately before being integrated into the final system. This approach made development more manageable and allowed continuous testing and refinement throughout the project.
5.1 Requirement Analysis
The first step was identifying the functional and non-functional requirements of the game. The primary objective was to develop a desktop-based multiplayer strategy game that provides multiple Tic Tac Toe game modes through an attractive graphical user interface.
Functional Requirements	Non-Functional Requirements
Multiple gameplay modes	User-friendly interface
Player name input	Smooth gameplay
Valid move detection	Fast response time
Winner and draw detection	Modular object-oriented design
Active board management	Maintainable source code
Countdown timer (Time Mode)	Lightweight desktop application
Score tracking	Responsive GUI
Sound effects and background music	Easy navigation
New Game and Reset	Reliable performance

5.2 System Design
After gathering the requirements, the system architecture was designed using Object-Oriented Programming (OOP) principles. The application was divided into several independent modules to improve maintainability and code reusability.
The major modules include:
a) Main Window (MainUI)
b) Front Page
c) Main Menu
d) Classic 3×3 Game Module
e) Ultimate 3×3 Game Module
f) Ultimate 4×4 Game Module
g) Ultimate 5×5 Time Mode Module
h) Game Engine
i) Winner Detection Module
j) Timer and Score Manager
k) Sound Manager
l) Settings and Rules Manager
Each module performs a specific responsibility while interacting with other modules to provide a complete gaming experience.
5.3 Game Development
The game was developed incrementally by implementing one feature at a time.
Step 1: Main Window and Navigation
Created the main application window using Java Swing. Implemented CardLayout to navigate smoothly between the Front Page, Main Menu, and different game modes.
Step 2: Classic Tic Tac Toe
Developed the Classic 3×3 gameplay, including board creation, player turns, winner detection, draw detection, and game reset functionality.
Step 3: Ultimate 3×3 Mode
Implemented Ultimate Tic Tac Toe rules with active-board selection, small-board winner detection, and overall game winner calculation.
Step 4: Ultimate 4×4 Mode
Expanded the gameplay to a 4×4 Ultimate board consisting of sixteen small boards. Added board highlighting and winner overlays.
Step 5: Ultimate 5×5 Time Mode
Implemented the 5×5 Time Mode with a countdown timer, live score tracking, board capture system, and automatic winner declaration when time expires.
Step 6: User Interface
Designed an attractive graphical interface with themed colors, highlighted active players, winner dialogs, overlays, and responsive buttons.
Step 7: Sound Integration
Added background music, button click sounds, board victory sounds, and winner sound effects to improve the overall gaming experience.
Step 8: Settings and Game Rules
Implemented a settings dialog for controlling audio and an in-game rules dialog explaining the gameplay mechanics of all modes.
Step 9: Testing and Bug Fixing
Performed continuous testing after implementing each module and fixed issues related to gameplay logic, sound playback, navigation, and user interface.
________________________________________
5.4 Technologies Used
a) Main Programming Language – Java (JDK 8+)
b) Graphical User Interface (GUI) – Java Swing (JFrame, JPanel, JButton, JLabel, JDialog)
c) Layout Management – CardLayout, GridLayout, BorderLayout, BoxLayout
d) Event Handling – ActionListener and Mouse Events
e) Audio – javax.sound.sampled
f) Timer – javax.swing.Timer
g) Development Environment – IntelliJ IDEA
5.5 Game Logic
Each game mode is controlled by a dedicated Game Engine class responsible for managing player turns, validating moves, detecting winners, handling draws, updating the active board, and maintaining the current game state. In the Ultimate 5×5 Time Mode, a Swing Timer manages the countdown and automatically ends the match when the time limit expires. The user interface communicates with the game engine to update the board, highlight active players, display scores, and present the winner dialog.


5.6 Testing
Each module was tested individually before integrating it into the complete system. The following tests were performed:
a) Navigation between screens
b) Player name input
c) Move validation
d) Winner detection
e) Draw detection
f) Active-board enforcement
g) Timer functionality
h) Score calculation
i) Sound playback
j) Winner dialog display
k) Game reset and menu navigation
After integration, the complete application was tested to ensure correct gameplay, smooth navigation, and stable performance.
5.7 Development Model
 
The Ultimate Tic Tac Toe game was developed using the Incremental Development Model. The project was divided into several small development stages, where each stage introduced new functionality before moving to the next. Development started with the main window and Classic 3×3 gameplay, followed by Ultimate 3×3, Ultimate 4×4, and Ultimate 5×5 Time Mode. Additional features such as sound effects, timer, score tracking, settings, and game rules were implemented in later stages. Each increment was tested independently before integration into the complete application. This approach simplified development, improved software quality, and made debugging and maintenance easier.
5.8 Development Workflow
The overall development process followed these stages:
                Start
                  │
                  ▼
      Requirement Analysis
                  │
                  ▼
          System Design
                  │
                  ▼
        User Interface Design
                  │
                  ▼
    Main Window Development
                  │
                  ▼
      Classic 3×3 Development
                  │
                  ▼
     Ultimate 3×3 Development
                  │
                  ▼
     Ultimate 4×4 Development
                  │
                  ▼
 Ultimate 5×5 Time Mode Development
                  │
                  ▼
     Sound & Settings Integration
                  │
                  ▼
      Timer & Score System
                  │
                  ▼
     Testing & Bug Fixing
                  │
                  ▼
      Final Evaluation
                  │
                  ▼
                 End
6.0 System Implementation and User Manual
6.1 The Front Page 
 
Fig 1: The Front Page.
The Front Page is the first screen we see when we open the application. It displays the game title over a looping ambient background track, and gives us three options:
●	Start Game - proceeds to the Player Setup Menu where we can enter names and choose a game mode.
●	Settings - opens a dialog to turn sound on or off.
●	Exit Game – it helps to exit the application.
6.2 Setting Button
 
Fig 2: The Settings dialog with the Sound toggle set to ON.
Click Settings on the Front Page to open the sound control dialog. A single toggle switches all audio click sounds, victory cues, and background ambience on or off at once. Click the toggle to switch it, then Close to return to the Front Page.






6.3 Name Selection and Game Mode Screen
 
Fig 3: The Player Setup Menu — name entry and mode selection.
After clicking Start Game, we arrive at the Player Name Setup and Level Selection Menu. This screen has two parts: 
Customized name entry at the top, and a grid of four mode cards below it.
The Four Mode:
a)	Classic 3×3 (Beginner) — traditional tic-tac-toe on a single board.
b)	Ultimate 3×3 (Intermediate) — nine nested boards, the standard Ultimate ruleset.
c)	Ultimate 4×4 (Advanced) — sixteen nested boards, an extended version of the same rule.
d)	Ultimate 5×5 (Championship) — twenty-five boards played against a two-minute countdown.
In the bottom part, there are two buttons:
Back Button - to return the front page 
Game Rules - opens an in-app reference explaining every mode's rules.
 


6.4 Game Rules Button
 
Fig 4: The Game Rules dialog.
Clicking on the Game Rules Button open a scrollable dialog covering all game modes rules. then Close to return to the Front Page.
6.5 Classic 3x3 Mode (Beginner Level)
 
Fig 5: Classic 3x3 and the active player's name box is highlighted in gold.

The traditional version of tic-tac-toe: a single 3×3 grid. Players alternate placing X or O in any empty cell. The first player to line up three of their own marks — horizontally, vertically, or diagonally — wins. If all nine cells fill with no winner, the match is a draw.
At the bottom of game screen:
●	Reset-clears the current board and starts a fresh match in the same mode, keeping the same player names.
●	Back- returns to the Player Setup Menu, ending the current match.

 
Fig 6: Classic 3×3 — the Winner dialog after a completed match.
When a match ends by a win or a draw a Winner dialog appears automatically with two options: Play Again (resets the board and starts a new match in the same mode) or Menu (returns to the Player Setup Menu).
6.6 Ultimate 3x3 Mode (Intermediate Level)
Nine small tic-tac-toe boards are arranged inside one large 3×3 meta-board. The cell one player play in determines which small board opponent must play in next — this is the active-board rule
The active board rules:
1	Play any cell in any small board on first turn (the first move of the match can go anywhere).
2	The position of that cell within its small board determines which small board opponent must play in next.
3	The required board is highlighted with a gold border; every other board is dimmed and cannot be clicked.
4	If the required board is already finished (won or drawn), opponent may play in any open board instead.
 
Fig 7: Ultimate 3×3 ~ the active-board highlight (gold border) and a completed small board (red X overlay).
At the bottom of game screen:
●	Reset-clears the current board and starts a fresh match in the same mode, keeping the same player names.
●	Back- returns to the Player Setup Menu, ending the current match.
Winning the Match:
Winning three small boards in a row ~ horizontally, vertically, or diagonally, treating each small board's outcome the way a single cell would be treated in Classic mode — wins the entire match. A finished small board displays a large overlay symbol, color-coded per winner (red for X, blue for O). 

 
Fig 8: Ultimate 3×3 ~ The Player O win the match  
When a match ends by a win or a draw a Winner dialog appears automatically with two options: Play Again (resets the board and starts a new match in the same mode) or Menu (returns to the Player Setup Menu).
 
Fig 9: Ultimate 3×3 ~ the Winner dialog after a completed match.
6.7 Ultimate 4x4 Mode (Advanced Level)
The same active-board rule as Ultimate 3×3, scaled up to sixteen small boards arranged in a 4×4 meta-board. Because there are more boards to win, matches run longer and reward planning several moves ahead.
 
Fig 10: Ultimate 4x4 ~ the active-board highlight (gold border) and a completed small board (red X and blue O overlay).
At the bottom of game screen:
●	Reset-clears the current board and starts a fresh match in the same mode, keeping the same player names.
●	Back- returns to the Player Setup Menu, ending the current match.
To win the match ,we have to win 4 small board in row ~ horizontally, vertically, or diagonally.
 
Fig 11: Ultimate 4×4 ~Player O win the match.
When a match ends by a win or a draw a Winner dialog appears automatically with two options: Play Again (resets the board and starts a new match in the same mode) or Menu (returns to the Player Setup Menu).
 
Fig 12: Ultimate 4×4 ~the Winner dialog after a completed match.
6.8 Ultimate 5x5 Mode (Championship)
Time Mode uses twenty-five independent small boards with no active-board restriction — one may play in any board that is not yet finished, on any turn. Instead of racing to line up boards in a row, the match is decided by a two-minute countdown: whichever player has captured more small boards when the timer reaches zero wins the match. If both players have captured the same number of boards, the match ends in a draw.
 
Fig 13: Ultimate 5×5 (Time Mode)
When the timer reaches zero, the board locks automatically and a Winner dialog appears automatically with two options: Play Again (resets the board and starts a new match in the same mode) or Menu (returns to the Player Setup Menu). And final Result and score:
 
Fig 14: Ultimate 5×5 (Time Mode)  the Winner dialog showing the final score, X 8 – 7 O.
7.0 Testing and Evaluation
Test ID	Test Case	Result
01	Launch the application	Front page loads successfully with background music and all menu options displayed.
02	Navigate from Front Page to Main Menu	Main menu opens correctly without errors.
03	Start Classic 3×3 mode	Classic 3×3 game board loads correctly.
04	Start Ultimate 3×3 mode	Ultimate 3×3 board opens with all nine small boards displayed.
05	Start Ultimate 4×4 mode	Ultimate 4×4 board loads successfully with sixteen small boards.
06	Start Ultimate 5×5 Time Mode	Time Mode starts with the timer initialized and score displayed.
07	Enter custom player names	Player names are updated and shown correctly in every game mode.
08	Make a valid move	Player symbol (X or O) appears in the selected cell and the turn changes.
09	Attempt an invalid move on an occupied cell	Move is rejected and board remains unchanged.
10	Active-board restriction (Ultimate modes)	Only the required board is enabled and highlighted; all others remain disabled.
11	Win a small board	Winning board displays a large X/O overlay and board color changes accordingly.
12	Draw a small board	Small board is marked as draw and no further moves are allowed on it.
13	Win the overall game	Winner dialog appears with correct winner information and victory sound.
14	Timer countdown (Time Mode)	Timer decreases every second until it reaches zero.
15	Timer expiration	Game stops automatically and winner is decided based on captured boards.
16	Score update (Time Mode)	Player scores increase immediately after capturing a small board.
17	Reset/New Game	Board, timer, scores, and player turns are reset to initial state.
18	Back/Menu button	Returns to the main menu successfully and background music resumes.
19	Sound ON/OFF	Sounds are played or muted according to the selected setting.
20	Background music	Background music loops continuously without interruption.
21	Winner dialog buttons	"Play Again" starts a new game and "Menu" returns to the main menu.
22	Multiple game sessions	Starting another game does not retain previous game data or cause unexpected dialogs.
23	GUI responsiveness	All buttons and screens respond correctly without freezing or lagging.
24	Error handling	No crashes or runtime exceptions occur during normal gameplay.
8.0 Conclusion and Future Work
This project successfully developed an interactive desktop-based Ultimate Tic Tac Toe Game using Java Swing and Object-Oriented Programming principles. The application provides four different gameplay modes: Classic 3×3, Ultimate 3×3, Ultimate 4×4, and a Time Mode (Ultimate 5×5), each offering a unique gaming experience.
The system was designed with a graphical user interface that allows players to navigate easily between game modes, enter player names, play games with sound effects and background music, and restart or return to the main menu whenever required. The project demonstrates the practical implementation of Java GUI programming, event handling, timers, audio management, and modular software design.
Throughout the development process, concepts such as encapsulation, inheritance, polymorphism, and class abstraction were applied to build a structured and maintainable application. Manual testing and unit testing confirmed that the major functionalities of the system operate correctly and provide a smooth user experience.
Overall, the Ultimate Tic Tac Toe Game achieved its primary objectives by delivering a functional, user-friendly, and entertaining desktop game while serving as a practical application of software engineering and object-oriented programming concepts.
Although the current version of the game is fully functional, several enhancements can be added in future versions to improve gameplay and user experience.
Some possible future improvements include: 
a)	Artificial Intelligence (AI) Opponent: Implement different difficulty levels (Easy, Medium, Hard) so that users can play against the computer. 
b)	Game Save and Resume: Allow users to save an unfinished match and continue playing later. 
c)	Enhanced Graphics and Animations: Add smooth animations, transition effects, and improved visual themes for a more engaging experience. 
d)	Custom Themes and Sound Settings: Allow users to change board colors, themes, background music, and sound effects according to their preferences. 
e)	Undo and Replay Features: Provide the ability to undo the previous move and replay completed matches. 
f)	Database Integration: Store player information, scores, and game history permanently using MySQL or SQLite.
