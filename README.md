# Article Analysis and Comparison Application Documentation

## Developer Contact
- **Paul Ujlaky** - paul.ujlaky@student.fairfield.edu (GitHub: @elucid503)
- **James Doyle** - james.doyle@student.fairfield.edu (GitHub: @jdoyle2735)
- **Ronny Cuji** - ronny.cuji@student.fairfield.edu (GitHub: @ronnyc077)

---

## Project Statement
This software application performs advanced text analysis with the goal of comparing articles about the same topic/news. The application can read multiple articles on a specific topic, analyze their content for word frequency, unique vocabulary, and sentiment and they rank them from most positive to negative.

---

## Features
- Input articles that share the same topic. You can do this via:
  1. Providing a URL.
  2. Inputting an absolute path to a `.txt` file.
  3. Manually placing files in the `ExternalFiles/Topics` directory.
- Sentiment analysis using a predefined lexicon.
- Word frequency analysis.
- CLI interface for managing topics and articles.
- Path normalization for robust file handling.
- Detailed logging for debugging.

---

## Classes Overview

### Main Class

#### **`Main`**
- **Purpose**: The main entry point for the program. Initializes and launches the CLI for interacting with the application.
- **Key Methods**:
  - `public static void main(String[] args)`: Calls `Interactions.startUI()` to launch the CLI.

---

### CLI Package

#### **`Interactions`**
- **Purpose**: Provides the command-line interface (CLI) for user interaction.
- **Key Methods**:
  1. `startUI()`: Launches the main interface.
  2. `createTopic()`: Allows users to create a new topic.
  3. `addArticle()`: Adds an article to a topic from a URL or file.
  4. `runSentimentAnalysis()`: Performs sentiment analysis on articles in a topic.

#### **`Logging`**
- **Purpose**: Handles logging for debugging and user feedback in the CLI.
- **Key Methods**:
  1. `logInternal(level, message)`: Logs messages with levels like `DEBUG`, `WARNING`, or `ERROR`.
  2. `logUI(message, styles)`: Displays styled messages for the user interface.

---

### Articles Package

#### **`Article`**
- **Purpose**: Represents an article, including its content, metadata, and processed text.
- **Key Methods**:
  1. `read(lower)`: Reads the article from a file. Converts content to lowercase if `lower` is true.
  2. `process()`: Removes punctuation, filters stop words, and identifies unique words.

#### **`SentimentRanker`**
- **Purpose**: Calculates sentiment scores for articles based on a predefined lexicon.
- **Key Methods**:
  1. `readLexiconWords()`: Reads the lexicon file containing words and sentiment scores.
  2. `rank()`: Assigns a sentiment score to an article.

#### **`WordManager`**
- **Purpose**: Handles word-level operations for articles.
- **Key Methods**:
  1. `getUniqueWords()`: Extracts unique words from the article.
  2. `removeStopWords(contents)`: Filters out stop words.
  3. `removePunctuation(contents)`: Removes punctuation from text.

---

### Articles.Words Package

#### **`ArticleWord`**
- **Purpose**: Represents a word in an article and tracks its frequency.
- **Key Methods**:
  1. `incrementTimesSeen()`: Increases the count of how often the word appears.

#### **`LexiconWord`**
- **Purpose**: Represents a word from the lexicon with its associated sentiment ranking.
- **Key Methods**:
  1. `split()`: Processes the lexicon entry into its components: word and ranking.

---

### Topics Package

#### **`Topic`**
- **Purpose**: Represents a collection of articles grouped under a topic.
- **Key Methods**:
  1. `load()`: Loads articles from the topic directory.
  2. `process()`: Processes all articles in the topic.

#### **`ArticleManager`**
- **Purpose**: Analyzes articles in a topic, such as finding the one with the richest vocabulary.
- **Key Methods**:
  1. `getArticleWithRichestVocab()`: Finds the article with the most unique words.

---

### Util.Files Package

#### **`Directories`**
- **Purpose**: Manages directory operations such as creation, deletion, and listing.
- **Key Methods**:
  1. `create(path)`: Creates a new directory.
  2. `delete(path)`: Deletes an existing directory.
  3. `read(path)`: Lists files in a directory.
  4. `listChildDirectories(source)`: Lists subdirectories of a given directory.

#### **`Files`**
- **Purpose**: Handles file operations, including reading, writing, moving, and deleting.
- **Key Methods**:
  1. `readAsString(path)`: Reads file content as a single string.
  2. `readAsStringList(path, individualWords)`: Reads file content as a list of lines or words.
  3. `write(path, contents)`: Writes content to a file.
  4. `move(oldPath, newPath, absolutePath)`: Moves a file to a new directory.
  5. `delete(path)`: Deletes a specified file.

#### **`Util`**
- **Purpose**: Resolves relative paths into absolute paths for compatibility across platforms.
- **Key Methods**:
  1. `resolvePath(path)`: Converts a relative path to an absolute one.

---

### Util.Misc Package

#### **`Sorting`**
- **Purpose**: Provides utilities for sorting collections based on object properties.
- **Key Methods**:
  1. `sortByObjectPropertyCount(list, prop)`: Sorts objects by the specified integer property.

---

### Util.Strings Package

#### **`Strings`**
- **Purpose**: Provides utility methods for text processing.
- **Key Methods**:
  1. `convertSentencesToWords(sentences)`: Splits sentences into individual words.

---

### Util.Scraping Package

#### **`ArticleScraper`**
- **Purpose**: Scrapes article content from the web using the JSoup library.
- **Key Methods**:
  1. `detailsAndText(url)`: Downloads the article content and returns it along with its domain.

---

## How to Run

1. **Input Articles**:
   - Use one of three methods:
     1. Input a URL via the CLI to scrape the article.
     2. Input the absolute path to a `.txt` file via the CLI.
     3. Manually place `.txt` files into `ExternalFiles/Topics`.

2. **Compile and Execute**:
   ```bash
        javac Main.java
        java Main
   ```

## Reflection

  For this semester, we were tasked with creating a Java application which takes a topic of
articles and compares each article's sentiment, or overall attitude. Through our work, we feel that
we have not only met this goal but exceeded it by creating an interactive app which extends the
original purpose by allowing users to easily create topics and add articles either by file path or
URL. In hindsight, there are some areas we feel could be improved and many lessons which have
been learned.

  Creating a command-line-interface (CLI) proved to be slightly more complex than
originally imagined. This is largely due to how the different user interfaces needed to interact
with each other. A possible solution which improves cohesion within the Interactions class is to
split each different UI into a different class. To allow for code to be reused, these classes could
inherit from a base UI class. This is not necessary, since each UI is split into separate methods
within the Interactions class, however could be useful for maintainability.

  A large improvement we took upon ourselves to make to this project was to add an option
for users to add articles by direct URLs. This was implemented due to the fact that many users
are not familiar with file paths, especially on command-line-interfaces, and it is much easier to
copy an article’s link and paste it rather than select all of its content. We used the JSoup module
to allow us to request a web document, parse the content within its body tag, and then
subsequently write it to a file. On this topic, another large challenge for us was handling file
paths.

  As we knew, different operating systems handle file paths differently. MacOS, built off of
Unix, handles file paths similar to Linux, while Windows has its own approach. Although mostly
developed targeting Windows, we were able to test the application on Linux, MacOS and
Windows, thanks to WSL, and a group member who had a Macbook. Through testing for each
platform, many file-path related bugs needed to be addressed. This was a good experience for
getting to understand how Java handles this very universal topic in programming. By better
understanding the java.nio (new io) package, we were able to implement satisfactory solutions
such as path resolvers to ensure file paths are handled correctly on all platforms.

  As mentioned, the fundamental functionality of this app is to analyze an article’s
sentiment. Although complicated, this task was achieved using a relatively simple method by
taking a list of predefined words with sentiment rankings associated with them (the lexicon
dataset) and matching the cleaned article’s words against this list. When matching, the sentiment
rank of each word was added to a global incrementer, which then was also affected by negative
words. This, although effective, could be done in a more practical way as certain articles may not
be represented correctly. An on-device machine learning model would be apt to do this task.

  In conclusion, this project was a very good way to learn how to create, manage, refactor
and implement new features within an actual codebase in Java. Refactoring many times, our
source code is totally different than when we started… and even almost entirely differently
structured from milestone #1. This was by design, as many lessons were learned through this
process, and a very helpful trait of Java (and object oriented programming) is the ability to keep
the functionality of existing code and easily encapsulate it in a new class. Even with the areas
that could be improved, we are confident that this software and our iterative development of it
exceeds the goals initially presented.
   javac Main.java
   java Main
