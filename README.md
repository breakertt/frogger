# <img src="https://i.imgur.com/R0v19Pw.png" alt="pacman logo" width="50"/> frogger

This is a frogger game project written in JavaFX for *[COMP2013 CW2](https://projects.cs.nott.ac.uk/scytg1/frogger/wikis/swm1920)*, using some design patterns like MVC, Singleton and Factory, etc.

## Content

* [Content](#content)
* [Preview](#preview)
    + [Screentshots](#screentshots)
    + [Video](#video)
* [Develop](#develop)
    + [Prerequisite](#prerequisite)
    + [Compiling & Running](#compiling---running)
* [Project Details](#project-details)
    + [Souce File Structure](#souce-file-structure)
    + [Design Patterns](#design-patterns)
    + [Class Diagram](#class-diagram)
* [About G52SWM CW2](#about-g52swm-cw2)
* [Credits](#credits)

## Preview

### Screentshots

![](https://i.imgur.com/l7iTvAh.png)
![](https://i.imgur.com/g1aEJyK.png)

### Video

[[G52SWM CW2] Frogger - Tianyi GAO](https://youtu.be/Zm0Fr1qR_Vs)

## Develop

### Prerequisite

* IntelliJ
* JDK
    * JAVA 10
    * JAVA 11 or later & Maven
    (As [JavaFX](https://openjfx.io/index.html) is dropped from JDK 11, it is recommended that using [Maven](https://maven.apache.org/) to help mange required sources.)

### Compiling & Running

You can launch the app either by running that `frogger.Main.main()` method inside IntelliJ via click <kbd>Run</kbd>  -> <kbd>Run 'Main()'</kbd> (JDK 10 only), or type `mvn clean compile package exec:java`  command  if and maven is installed.

## Project Details

### Unit Test

- The [CI config](https://projects.cs.nott.ac.uk/scytg1/G52SWM_CW2_scytg1/blob/master/.gitlab-ci.yml) of GitLab for this project.
- [Unit Test](https://projects.cs.nott.ac.uk/scytg1/G52SWM_CW2_scytg1/tree/dev/src/test/java/frogger/model/info) is implemented to some models.

### Javadoc

- Javadoc for this project is published via [Gitlab Pages](https://docs.gitlab.com/ee/user/project/pages/) on https://tianyigao.gitlab.io/frogger/
- Complied Javadoc is also provided inside [`docs`](docs) directory of repository

### Versions

- [Version by tags](https://projects.cs.nott.ac.uk/scytg1/G52SWM_CW2_scytg1/-/tags) in git

### Project Management by Trello (In Progress Screenshot)

![trello](https://i.imgur.com/GwF9HS9.png)
 
### Souce File Structure

```
src
├── main
│   ├── java
│   │   └── frogger
│   │       ├── constant
│   │       ├── controller
│   │       ├── model
│   │       │   ├── info
│   │       │   └── selfMovable
│   │       └── util
│   │           ├── score
│   │           └── sound
│   └── resources
│       └── frogger
│           ├── css
│           ├── font
│           ├── image
│           │   ├── background
│           │   ├── frogger
│           │   ├── ground
│           │   └── water
│           ├── music
│           └── view
└── test
    └── java
        └── frogger
            └── model
                └── info
```

### Design Patterns

#### MVC

- [Model](https://projects.cs.nott.ac.uk/scytg1/G52SWM_CW2_scytg1/tree/master/src/main/java/frogger/model)
- [View](https://projects.cs.nott.ac.uk/scytg1/G52SWM_CW2_scytg1/tree/master/src/main/resources/frogger/view)
- [Controller](https://projects.cs.nott.ac.uk/scytg1/G52SWM_CW2_scytg1/tree/master/src/main/java/frogger/controller)

#### Factory

- [Score Board](https://projects.cs.nott.ac.uk/scytg1/G52SWM_CW2_scytg1/blob/master/src/main/java/frogger/controller/ScoreBoardController.java)
- [Theme Music](https://projects.cs.nott.ac.uk/scytg1/G52SWM_CW2_scytg1/blob/master/src/main/java/frogger/util/sound/ThemePlayer.java)

### Singleton

- [Game Manager](https://projects.cs.nott.ac.uk/scytg1/G52SWM_CW2_scytg1/blob/master/src/main/java/frogger/util/GameManager.java)

### Class Diagram

![uml](https://i.imgur.com/dhkpqd3.jpg)

### File Storage

This project stores historical scores in user's home directory: `~/.frogger`.

## About G52SWM CW2

Click [Here](https://projects.cs.nott.ac.uk/scytg1/frogger/wikis/swm1920) for details about requirements of this project.

## Credits

This project is modified from a [Legacy](https://github.com/hirish99/Frogger-Arcade-Game) one.

All assets (images & music) are derived from the internet, sources of assets are listed in [ASSETS](ASSETS.md).

This project adopts MIT license.

Special thanks to:
1. [Song ZHANG](https://projects.cs.nott.ac.uk/psysz4) for open source for his coursework last year, which help me set a goal and bring perfect template for this project.
2. My girlfriend for taking care of my daily life and encouraging me during the month before due.
