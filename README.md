# Mobile Data Usage Tracker

This is a simple Core Java project I developed to keep track of mobile data usage. Users can enter how much data they have used for different activities and check how much data is remaining from their total limit.

I built this project to practice Java programming, file handling, and object-oriented programming.

## Features

- Add data usage for different activities.
- View the history of recorded usage.
- Check total data used and remaining data.
- View data usage as a percentage of the limit.
- Save usage records in a text file so they are available when the program is restarted.

## Technologies Used

- Java
- ArrayList
- File Handling
- Exception Handling
- Git and GitHub
- Visual Studio Code

## Project Structure

```text
MobileDataUsageTracker/
├── src/
│   ├── Main.java
│   ├── DataUsage.java
│   ├── DataUsageTracker.java
│   └── FileManager.java
├── data/
│   └── usage.txt
├── .gitignore
└── README.md
```

## How to Run

Make sure Java JDK is installed on your computer.

**1. Clone the repository**

```bash
git clone https://github.com/lasyaalavala9/mobile-data-usage-tracker.git
```

**2. Open the project folder**

```bash
cd mobile-data-usage-tracker
```

**3. Compile the Java files**

```bash
javac -d src src/Main.java src/DataUsage.java src/DataUsageTracker.java src/FileManager.java
```

**4. Run the application**

```bash
java -cp src Main
```

## How It Works

When the program starts, the user enters their mobile data limit. The application provides a menu to add data usage, view the usage history, check a summary, or exit.

The usage records are stored in `data/usage.txt`. When the program starts again, the saved records are loaded from the file.

Currently, data usage is entered manually by the user. The application does not automatically track mobile data consumed by the phone.

## What I Learned

While working on this project, I practised creating classes and objects in Java, using collections to store records, handling files, validating input, and using Git and GitHub to manage my code.

## Future Improvements

- Save the data limit so it does not need to be entered every time.
- Add daily and monthly usage reports.
- Add options to search or filter usage records.
- Create a graphical interface for the application.

## GitHub

Repository: https://github.com/lasyaalavala9/mobile-data-usage-tracker