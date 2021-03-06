# 3rd-Year-Project

## Table of contents:
* Introduction
* System reguirments
* Installation
* How to run


## Introduction 

This is the 3rd year group project developed by David Clarke, Gary Connelly and Eoghan O'Connor. It is a group messenger app. Users can create a username and password and send private messages or send out a status in the form of a broadcast message for anyone to see. The clients can send a message to the server and the server sends it back out. The server is in a virtual machine in Google Cloud.

## System Requirements

### Purpose
The purpose of the System Requirements document is to specify the overall system requirements that will govern the development and implementation of the system.  The document will also establish initial security, training, capacity and system architecture requirements, as well as, system acceptance criteria agreed upon be the project sponsor and key stakeholders.

### Major System Conditions
- System uses eclipse
- System uses a virtual machine for the server
- System requires normal internet connection

### System Interface

System uses JFrame Java Platform that adds support for the JFC/Swing component architecture

## Installation

If you are downloading this project from get hub, you will need a desktop/ laptop and a java IDE.
- In command prompt, navigate to wherever you would like the project to be save on your machine. 
- Type in the following command: 

```
https://github.com/davidclarke1995/3rd-Year-Project.git
```
Alternatively you can select this green button

![oops](https://github.com/davidclarke1995/3rd-Year-Project/blob/master/MessengerClient/Images/GitClone.PNG)

- Open the project is your preffered java IDE.
- Navigate to the client project, and then to StartUpWindow class. 
- Click run. 

It will load this page

![oops](https://github.com/davidclarke1995/3rd-Year-Project/blob/master/MessengerClient/Images/LandingPage.png)

* If you cannot connect to the server this may be because my google cloud server has expired. However you can make your own server with the code in the server project, but you will need to create your own database locally. All the code for that can also be found on this repository under the DatabaseInterface folder.

## How to run 

- User must be on the StartUpWindowPractice.java and select the green arrow on the right hand side

![oops](https://github.com/davidclarke1995/3rd-Year-Project/blob/master/MessengerClient/Images/GreenArrow.PNG)

- The initial start up window will appear and show the login page as seen above

![oops](https://github.com/davidclarke1995/3rd-Year-Project/blob/master/MessengerClient/Images/LandingPage.png)

- Selecting the the Set Up button brings you to this page 

![oops](https://github.com/davidclarke1995/3rd-Year-Project/blob/master/MessengerClient/Images/LoginPage.png)

- The user can enter in their name and then choose what they want their username and password
- Next the user clicks on the 'Sign me up!' button, they have now created an account that the database stores
- The user can exit this window and select the 'Login' button to go to their account that they have just created and it will bring up this page 

![oops](https://github.com/davidclarke1995/3rd-Year-Project/blob/master/MessengerClient/Images/SocialPage.png)

- The user can enter their details (username and password) and select the 'Login' button
- This will then show the messaging page itself which the user can enter in its commands and messages for the other clients

![oops](https://github.com/davidclarke1995/3rd-Year-Project/blob/master/MessengerClient/Images/message.png)

- The user can see a list of commands if they enter in '#HELP' 
- Finally in the login page there is a 'Delete me from system' and when clicked the user can enter in the details of the account which they wish to delete which would be the username and password again. This completely deletes the account from the database.

![oops](https://github.com/davidclarke1995/3rd-Year-Project/blob/master/MessengerClient/Images/Password.png)
