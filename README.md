## IBM-TestAutomation
Test Framework for IBM

## How to Run the Test script?
  # Navigate to > DBS-Demo/src/test/java/testRunner/Runner.java
  # Run as JUnit
  
## Data File  
    DBS-Demo/resources/dataFile
## Report generated
    DBS-Demo/target/cucumber-html-reports.html

## Log file 
    DBS-Demo/log

## Mac Configuration :
Warning message : 
“chromedriver” cannot be opened because the developer cannot be verified. Unable to launch the chrome browser"

Solution : 
  # Open the Terminal 
  # Navigate to Driver Folder > DBS-Demo/Driver
  # Run the command > xattr -d com.apple.quarantine chromedriver
