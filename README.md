# WikipediaRevisionHistory
> CS 222 First Project - WikipediaRevisionHistory

## Description

A CLI that will provide the user and timestamp of the 13 latest revisions of any given Wikipedia article.

## Installation

1. Clone the repo
   ```sh
   git clone https://github.com/bsu-cs222-fall23-dll/first-bakeys-bwilson.git
   ```
2. Install Intellij

3. Install all required Intellij and Gradle dependencies.

## Usage

To use the program in the command line, run the `main` method of the [WikipediaRevisionHistory.CLI.Main](src/main/java/WikipediaRevisionHistory/CLI/Main.java) class;

The program prompts a name from the user for a wikipedia article. The wikipedia article is then tested to see if the article exists, while printing out any url redirects. The program accesses the Wikipedia page and grabs the 13 most recent revisions, printing the usernames and the dates.

In order to access the program from command line: Run the Main  Class

In order to access the program using GUI: use the gradle extension Tasks -> Application -> Run

## Contributors

- [Ben Keys](https://github.com/bkeys818)
- [Ben Wilson](https://github.com/Ben-jaminWilson)
