# Mental Health Assistant

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
  - [Prerequisites](#prerequisites)
  - [Set Up the MySQL Database](#set-up-the-mysql-database)
  - [Start the Rasa Chatbot](#start-the-rasa-chatbot)
  - [Build and Run the JavaFX Application](#build-and-run-the-javafx-application)
- [Usage](#usage)
- [Disclaimer](#disclaimer)
- [Contributors](#contributors)

## Overview
The **Mental Health Assistant** is a JavaFX-based desktop application designed to help users track their mental well-being. It integrates a chatbot using **Rasa Open Source** via HTTP, allowing users to log moods, set reminders, chat with an assistant, and access coping mechanisms. The application ensures secure authentication and data storage using **MySQL**.

## Features
- 🌿 **Mood Tracking** – Log, view, and manage mood entries.
- 🛎 **Reminders** – Set personalized reminders for self-care.
- 🤖 **AI Chatbot (Rasa)** – Engage in conversations for mental health support and revisit past conversations in an interactive UI.
- 🔍 **Coping Mechanisms** – View strategies for managing stress and anxiety.
- 🔒 **Secure Authentication** – User login and data protection.
- 📊 **Data Storage** – Persist user data using **MySQL**.
- 🖥 **Modern JavaFX UI** – User-friendly and interactive interface.
- ✅ **Data Validation** – Ensures input correctness and reliability.

## Technologies Used
- **JavaFX** – GUI Development
- **Java** – Core backend logic
- **Rasa Open Source** – AI Chatbot
- **MySQL** – Data storage and management
- **JDBC** – Database connectivity
- **Maven** – Dependency management
- **FXML** – UI design

## Setup Instructions

### Prerequisites
Ensure you have the following installed:
- **Java 17+** installed
- **MySQL** installed and configured
- **Rasa Open Source** installed and running ([Installation Guide](https://rasa.com/docs/getting-started/))
- **Maven** configured in your development environment
- **IntelliJ IDEA** (recommended) or any preferred IDE

### Set Up the MySQL Database
1. Open MySQL and create a new database:
    ```sql
    CREATE DATABASE mentalhealth;
    USE mentalhealth;
    ```
2. Use the provided SQL script below to create the necessary tables.
    ```sql
    -- Create User Table
    CREATE TABLE user (
        user_id VARCHAR(36) PRIMARY KEY,
        username VARCHAR(255) NOT NULL UNIQUE,
        password_hash VARCHAR(255) NOT NULL,
        email VARCHAR(255) UNIQUE NOT NULL
    );

    -- Create Chatbot Table
    CREATE TABLE chatbot (
        chatbot_id INT AUTO_INCREMENT PRIMARY KEY,
        bot_name VARCHAR(255) NOT NULL
    );

    -- Create Conversation Table
    CREATE TABLE conversation (
        conversation_id VARCHAR(36) PRIMARY KEY,
        user_id VARCHAR(36),
        chatbot_id INT,
        conversation_name VARCHAR(255) NOT NULL,
        FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
        FOREIGN KEY (chatbot_id) REFERENCES chatbot(chatbot_id) ON DELETE CASCADE
    );

    -- Create Message Table
    CREATE TABLE message (
        message_id VARCHAR(36) PRIMARY KEY,
        conversation_id VARCHAR(36),
        content TEXT NOT NULL,
        sender_id VARCHAR(36) NOT NULL,
        sender_name VARCHAR(255) NOT NULL,
        timestamp DATETIME NOT NULL,
        FOREIGN KEY (conversation_id) REFERENCES conversation(conversation_id) ON DELETE CASCADE
    );

    -- Create Mood Log Table
    CREATE TABLE mood_log (
        log_id INT AUTO_INCREMENT PRIMARY KEY,
        user_id VARCHAR(36),
        mood_type VARCHAR(50) NOT NULL,
        timestamp DATETIME NOT NULL,
        FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
    );

    -- Create Reminder Table
    CREATE TABLE reminder (
        reminder_id INT AUTO_INCREMENT PRIMARY KEY,
        user_id VARCHAR(36),
        reminder_text TEXT NOT NULL,
        reminder_time DATETIME NOT NULL,
        FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
    );
    ```
3. Open `DatabaseHandler.java` and update the `password` variable with your MySQL password.

### Start the Rasa Chatbot
Run the following command to start the chatbot:
```sh
rasa run --enable-api
```

### Build and Run the JavaFX Application
Using Command Line:
```sh
mvn clean install
mvn javafx:run
```
Using IntelliJ IDEA (Recommended):
- Reload the project to ensure all dependencies are installed.
- Run the application directly from the IDE.

## Usage 
- Login/Register: Secure authentication to access the dashboard.
- Chatbot: Start chatting with the AI assistant.
- Mood Tracking: Log moods and analyze patterns with the Pie Chart.
- Set Reminders: Schedule tasks and events with one-time or recurring options.
- Chat History: Browse and open previous conversations in a modern card layout.
- Explore Coping Mechanisms: Learn self-care techniques and stress-management strategies.

## Disclaimer
🚨 This project is a prototype and should not be used for commercial purposes.
- The chatbot is trained on a limited dataset and can only handle basic interactions.
- Users should consult licensed professionals for serious mental health concerns.

## Contributors
- Mahnoor Zahra ([GitHub Profile](https://github.com/mahra110))
- Bisma Iqbal ([GitHub Profile](https://github.com/bismaiqbal5404))
- Abbas Fakhruddin ([GitHub Profile](https://github.com/Abbas-coding))
