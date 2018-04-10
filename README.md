# 3rd-Year-Project

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

### Minimum Functional Requirments

- System must allow users to log into the system with a username and password.
- System must allow people to send a broadcast message to everyone connected to the server on the system.
- System must give people the option to send a private message to specific people on the system. 
- Users must have the option to delete themselves from the system.

### Non Functional Requirments

- Login page must be very simple and easy to use.
- Chat section of the app must be basic enough that somone who has never used a computer before should be able to use this.
- Develop the database to 3rd Normal Form for the implementation.

## Technology Used and Why

### Java
Java is a general purpose programming language that is designed to have as few implementation dependecies as possible. It is intended to allow developers to "Write once, Run anywhere", meaning that complied java code can run on all platforms without the need for recompilation. Java is complied to bytecode that can run on any Java Vitural Machine (JVM). As of 2016 Java is the most popular programming language in the world with a reported 9 million developers worldwide. Java is largely used for server client based applications used by both Google and Facebook receiving 1.6 and 1.1 billion visitors respectively monthly. As a group we collectively decided that we needed to write in a language that we were mostly familiar with. We all agreed to use java for its versatility and large amount of online resources available. It is by far the programming language that we are all very comfertable using and having completed multiple projects during our time in GMIT eg. Porta cipher, database interfaces, Document simiality API.

### MySQL
MySQL is an open-source relational database management system. Its name is a combination of "My", the name of co-founder Michael Widenius's daughter, and "SQL", the abbreviation for Structured Query Language. At the time of writing of this README MySql is the second most popular database languaging for relational databases (https://db-engines.com/en/ranking). We picked MySql for its reliability and its popularlity amomng the programming community, it is also very easy to understand and has an abundance of resoucres both online and locally.

### Google Cloud
Google Cloud Platform, offered by Google, is a suite of cloud computing services that runs on the same infrastructure that Google uses internally for its end-user products, such as Google Search and YouTube.
Google Cloud Platform, offered by Google, is a suite of cloud computing services that runs on the same infrastructure that Google uses internally for its end-user products, such as Google Search and YouTube.

### JavaScript
JavaScript is a scripting or programming language that allows you to implement complex things on web pages — every time a web page does more than just sit there and display static information for you to look at — displaying timely content updates, interactive maps, animated 2D/3D graphics, scrolling video jukeboxes, etc. — JavaScript is more than likely involved. It is the third layer of the layer cake of standard web technologies.

## Architecture of the Solution
What is Solution Architecture? According to Mike Rosen, chief scientist at Wilton Consulting Group, the definition for solutions architecture is still emerging, but it generally refers to the process and art of developing solutions that fit within the project architecture in terms of information architecture, integration requirements and so on. 

In this project we had to decide how to arrange the parts of this project to be understandable. We discussed exactly how the project would work and how it would run aided by the use of hand made diagrams and YouTube tutorial ideas. We wanted to make sure that it would be user friendly, not complicated and productive. Once we understood and agreed how we were to tackle this app, we wanted to also make sure that the user is not confused in any shape or form. We have the interface well labelled and easy to follow so any first-timer should have no difficulties using it. Finally for any programmer reading through the code, we have commented as often as possible to help the programmer understand how the code works and how it is compiling.

As for the integration of the project, aligning eachothers code and putting it together was the main priority but one of the more difficult aspects was connecting the working database (written in sql) to the main code of the app (primarily written in java). 



## Design Methodology
When creating this project, we were keeping some of the main agile principles in mind as we know that they are key principles that go into the vast majority of successful software projects in industry today. We achieved this by putting extreme emphisis and focus on adaptive planning, evolutionary development, early delivery, and continual improvement. We knew that the project had to posess the ability to be able to be flexible in the event of a change in the design or implementation of the system. This ment that our project had to be extremely responsive to change. This is much easier said than done, and took up a big portion of our planning of the project. How can we make this project flexible and responsive to change? I thought the best way to achieve this would be to tackle the harder more critical aspects of the project first. At least that way, if we got that working, it would be easier to make the rest of the requirments fit around this initial design. It also provided us with the critcal foresight that we would not have posessed if we had tackled some of the less crutial, or for lack of a better word "easy" first, then realising that it actually will not fit the system we want to build.

The first part of the system that we decided to tackle was the actual messaging between multiple clients using java sockets. This was not an easy task but thankfully we had done some work on server sockets before in a module we had last year. However, the module we had last year only showed us how to operate an echo server so the real trick was trying to get the server to send messages to a particular client. The key to this was multithreading, but more on that in the wiki. once we got the system working to a stage where, using the console, I could send a message to my friends laptop and he would recieve it and be able to reply. Once we were able to do this, we say fit to move onto the database functionality of the system. This involved opening another port on the server but more on that in the wiki. Once we could successfully log into the system using a database that sat on the server, we moved onto developing the graphical user interface that would sit on top of the system. This proved to be more difficult than we origionally thought it would as it involved learning about JFrame, which is a framework we were very unfirmiliar with prior to developing this project. If you would like to know more about the agile methodology, you can read about it here -

```
https://en.wikipedia.org/wiki/Agile_software_development#The_Agile_Manifesto .
```

## Features of the Implementation
## Limitations
## Known Bugs
## Recommendations for Future Development
## Conclusions


