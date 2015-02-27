## **EEP** <-- ADB <-- FVS

![Build status](https://travis-ci.org/SEPR-EEP/taxe-game-3.svg?branch=master)



This repo contains EEP's extension of ADB's version of FVS' TaxE, including obstacles, quantifiable goals and scoring.

The .jar file is included in the root of the repo.

For more information and to answer any questions see [http://eurotraingame.co.uk](http://eurotraingame.co.uk) or email aw1067 [at york email]

Project Setup
====================================================================

1. Clone the repo
2. If you will be using eclipse, set your workspace to be the repository root.  You should also install the Gradle IDE from http://dist.springsource.com/snapshot/TOOLS/gradle/nightly.  You may also find the Git plug-in useful if it is not already installed.
3. In Eclipse go to File -> Import -> Gradle -> Gradle Project.  Choose the taxe folder in the repo and then press Build Model.  You should import both Core and Desktop, the taxe project is just a necessary wrapper and will never be used for anything.  Click Import and wait patiently.
4. To set up IntelliJ IDEA, clone the repository into IDEA projects folder (I used terminal for that). Now open up IDEA and select that you'll import a project from a file/folder. Select build.gradle file from FVS2/taxe/core folder. Now IDEA does some importing and downloading.

Setting Up Run Environment
====================================================================
Click Run -> Edit configurations...
Click on + in upper left.
Select Application and use Desktop for the name.
Select ../FVS2/taxe/core/assets as the Working directory
Use classpath of module: desktop
Main class: uk.ac.york.cs.sepr.fvs.taxe.desktop.DesktopLauncher
And it's ready.
You can try clicking on Run now and you should see the sample program work :)
6. Setting up and using Git on IDEA is really intuitive.
