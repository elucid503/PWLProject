# Programming Workshop Lab Semester Project - Article Analysis and Comparison Application

## Developer Contact:
- **Paul Ujlaky** - paul.ujlaky@student.fairfield.edu (GitHub: @elucid503)
- **James Doyle** - james.doyle@student.fairfield.edu (GitHub: @jdoyle2735)
- **Ronny Cuji** - ronny.cuji@student.fairfield.edu (GitHub: @ronnyc077)

## Project Statement
This software application performs advanced text analysis with the goal of comparing articles about the same topic/news. The application can read multiple articles on a specific topic, analyze their content for word frequency, unique vocabulary, and sentiment.

## Features
- Input articles that share the same topic. There are three ways to do this:
  1. Provide a URL to an article of your choosing.
  2. Provide the absolute path to a `.txt` file containing the article.
  3. Manually place your articles into respective topic folders inside the `ExternalFiles/Topics` directory.
- Remove stop words and punctuation for cleaner analysis.
- Calculate unique word counts and total word counts.
- Sort and display words by frequency of occurrence.
- Calculate sentiment ranking based on a predefined lexicon.
- Log actions with severity levels to assist debugging and analysis.
- Scrape articles directly from URLs.

## Overview of Classes

### **`Main`**
- **Purpose**: The main entry point for the application.
- **Functionality**: Launches the CLI for managing topics, articles, and sentiment analysis.

---

### **CLI Package**

#### **`Interactions`**
- **Purpose**: Handles user interaction through a command-line interface.
- **Key Methods**:
  - `startUI()`: Launches the user interface.
  - `createTopic()`, `addArticle()`, `runSentimentAnalysis()`: Allow topic and article management, as well as sentiment analysis.

#### **`Logging`**
- **Purpose**: Logs user and system actions with severity levels and custom styles.
- **Key Methods**:
  - `logInternal(level, message)`: Logs system-level messages.
  - `logUI(message, styles)`: Displays styled console messages for the user interface.

---

### **Articles Package**

#### **`Article`**
- **Purpose**: Represents an article and manages its content, including text processing and metadata.
- **Key Methods**:
  - `read(lower)`: Reads the article from a file, optionally converting content to lowercase.
  - `process()`: Removes stop words and punctuation, and identifies unique words.

#### **`SentimentRanker`**
- **Purpose**: Calculates a sentiment score for an article based on a predefined lexicon.
- **Key Methods**:
  - `rank()`: Assigns a sentiment score by matching article words with lexicon entries.

#### **`WordManager`**
- **Purpose**: Handles word-level operations for articles.
- **Key Methods**:
  - `getUniqueWords()`: Extracts unique words from the article.
  - `removeStopWords(contents)`: Filters out stop words.
  - `removePunctuation(contents)`: Removes punctuation using regex.

---

### **Articles.Words Package**

#### **`ArticleWord`**
- **Purpose**: Represents a word in an article and tracks its frequency.
- **Key Methods**:
  - `incrementTimesSeen()`: Increments the count of how often the word appears.

#### **`LexiconWord`**
- **Purpose**: Represents a word from the lexicon with a sentiment ranking.
- **Key Methods**:
  - `split()`: Splits the lexicon word into its components (word and ranking).

---

### **Topics Package**

#### **`Topic`**
- **Purpose**: Represents a topic containing grouped articles.
- **Key Methods**:
  - `load()`: Loads articles from the topic's directory.
  - `process()`: Processes all articles in the topic.

#### **`ArticleManager`**
- **Purpose**: Provides statistical analysis tools for articles within a topic.
- **Key Methods**:
  - `getArticleWithRichestVocab()`: Finds the article with the highest unique word count.

---

### **Util Package**

#### **Files Subpackage**
1. **`Directories`**
   - **Purpose**: Manages directory operations.
   - **Key Methods**:
     - `create(path)`: Creates a directory.
     - `delete(path)`: Deletes a directory.
     - `read(path)`: Reads file names in a directory.
     - `listChildDirectories(source)`: Lists child directories.

2. **`Files`**
   - **Purpose**: Handles file operations.
   - **Key Methods**:
     - `readAsString(path)`: Reads file content as a string.
     - `readAsStringList(path, individualWords)`: Reads file content as a list of strings or words.
     - `write(path, contents)`: Writes content to a file.
     - `move(oldPath, newPath)`: Moves a file.
     - `delete(path)`: Deletes a file.

3. **`Util`**
   - **Purpose**: Resolves relative paths into absolute paths for cross-platform compatibility.
   - **Key Methods**:
     - `resolvePath(path)`: Converts a relative path into an absolute path.

#### **Strings**
- **Purpose**: Provides text-processing utilities.
- **Key Methods**:
  - `convertSentencesToWords(sentences)`: Splits sentences into individual words.

#### **Misc Subpackage**
1. **`Sorting`**
   - **Purpose**: Dynamically sorts objects by a specified property.
   - **Key Methods**:
     - `sortByObjectPropertyCount(list, prop)`: Sorts a list of objects in descending order by the given property.

#### **Scraping Subpackage**
1. **`ArticleScraper`**
   - **Purpose**: Downloads article content from URLs using the JSoup library.
   - **Key Methods**:
     - `detailsAndText(url)`: Extracts the text and domain of an article from a URL.

---

## How to Run
1. **Input Articles**:
   - You can input articles in one of three ways:
     1. Navigate to **Manage All Topics > Select a Topic > Add an Article**:
        - Provide a URL to scrape the article content.
        - Or input the absolute path to a `.txt` article file.
     2. Manually place `.txt` article files into the respective topic folders inside the `ExternalFiles/Topics` directory.
         
2. **Compile and Execute**:
   ```bash
   javac Main.java
   java Main
