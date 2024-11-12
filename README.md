# Programming Workshop Lab Semester Project - Article Analysis and Comparison Application

## Developer Contact:
- **Paul Ujlaky** - paul.ujlaky@student.fairfield.edu (GitHub: @elucid503)
- **James Doyle** - james.doyle@student.fairfield.edu (GitHub: @jdoyle2735)
- **Ronny Cuji** - ronny.cuji@student.fairfield.edu (GitHub: @ronnyc077)

## Project Statement
This software application performs basic text analysis with the goal of comparing articles about the same topic/news. The application can read multiple articles on a specific topic, analyze their content for word frequency, unique vocabulary, and sentiment.

## Features
- Input articles that share the same topic.
- Remove stop words and punctuation for cleaner analysis.
- Calculate unique word counts and total word counts.
- Sort and display words by frequency of occurrence.
- Calculate sentiment ranking based on a predefined lexicon.
- Log actions with severity levels to assist debugging and analysis.

## Overview of Classes

### `Main`
- **Purpose**: The main entry point for the application.
- **Functionality**: Loads each article, processes its content, performs analysis, and logs the sentiment score.

### Util Folder

#### **Files Subfolder**:
1. **`DirReader`**
   - **Utility Class**: Reads a specified directory and lists all file names.
   - **Fields:**
     - `path`: Path to the directory to be read.
   - **Methods:**
     - `read(String path)`: Reads the directory and returns a list of file names.
       - **Exceptions**: Throws `Exception` if the directory cannot be read or if it does not exist, with detailed error messages for directory access issues.

2. **`FileReader`**
   - **Utility Class**: Reads content from files, either as a whole string or as a list of individual words.
   - **Fields:**
     - `Path`: File path to the document.
   - **Methods:**
     - `readAsString()`: Reads the entire content of the file as a single string.
       - **Exceptions**: Throws `Exception` if the file cannot be read, with a custom message detailing the file reading error.
     - `readAsStringList(boolean individualWords)`: Reads file content and returns it as a list of strings, split either by line or into individual words if `individualWords` is `true`.
       - **Exceptions**: Throws `Exception` if the file cannot be read. Provides a custom error message for readability issues.

3. **`PathResolver`**
   - **Utility Class**: Resolves relative file paths into absolute paths, ensuring compatibility across different systems.
   - **Fields:**
     - `Path`: The relative path to resolve.
   - **Methods:**
     - `resolve(String path)`: Returns the absolute path based on the provided relative path. Uses system properties to ensure the path is compatible across different operating systems.

#### **Misc Subfolder**:
1. **`Sorting`**
   - **Utility Class**: Sorts a list of objects based on a specified integer property.
   - **Methods:**
     - `sortByObjectPropertyCount(ArrayList<T> list, String prop)`: Sorts a list of objects by the specified integer field, such as `timesSeen`, in descending order.
       - **Exceptions**: Handles `IllegalAccessException` and `NoSuchFieldException` to ensure compatibility if the specified property does not exist in the objects.

2. **`Logging`**
   - **Utility Class**: Logs messages in a standardized format with severity levels (DEBUG, WARNING, ERROR).
   - **Fields:**
     - `DEBUG`, `WARNING`, `ERROR`: Constants representing logging levels.
   - **Methods:**
     - `log(int level, String message)`: Logs messages with timestamps and severity levels.
       - **Exceptions**: Throws `IllegalArgumentException` if an invalid logging level is provided, detailing acceptable values.

#### **Strings**:
1. **`SentenceManager`**
   - **Utility Class**: Manages text by splitting sentences into individual words for analysis.
   - **Methods:**
     - `convertSentencesToWords(ArrayList<String> sentences)`: Takes an `ArrayList` of sentences and splits them into individual words.

### Articles Folder

#### **`Article`**
- **Purpose**: Represents an article with fields for raw and processed content, unique words, and sentiment analysis tools.
- **Fields:**
  - `filePath`: Path to the article file.
  - `plainTextContents`: Raw content of the article.
  - `arrayListContents`: Content split into individual words.
  - `punctuationRemovedContents`, `stopWordRemovedContents`: Processed content versions.
  - `uniqueWords`: List of unique words in the article.
  - `wordManager`, `sentimentRanker`: Utility objects for word management and sentiment ranking.
- **Methods:**
  - `getName()`: Extracts and returns the article’s file name from its path. Splits the `filePath` by "/" and retrieves the last element.
  - `read(boolean lower)`: Reads file content and, if `lower` is true, converts it to lowercase. Uses caching to avoid repeated reads.
    - **Exceptions**: Throws `Exception` if the file cannot be read. Detailed error messages include the path and specific reading issues.
  - `process()`: Processes article content by removing punctuation, stop words, and identifying unique words. Calls methods in `WordManager` to handle punctuation and stop words.
    - **Exceptions**: Throws `Exception` if file reading or processing dependencies are missing.

#### **`SentimentRanker`**
- **Purpose**: Calculates sentiment ranking for an article based on a lexicon of words with sentiment values.
- **Fields:**
  - `relatedArticle`: Reference to the article being analyzed.
  - `rawLexiconWords`, `parsedLexiconWords`: Data required for sentiment analysis.
  - `sentimentRank`: Calculated sentiment score for the article.
- **Methods:**
  - `readLexiconWords()`: Reads sentiment lexicon from a specified file, using `FileReader`.
    - **Exceptions**: Throws `Exception` if the lexicon file cannot be read. Error handling includes detailed logging to assist with debugging file access issues.
  - `parseLexiconWords()`: Converts raw lexicon words into structured objects for analysis, using the `LexiconWord` class. Each word is split and stored in `parsedLexiconWords`.
  - `rank()`: Calculates sentiment score by comparing article words to lexicon entries. Matches words in `stopWordRemovedContents` with `LexiconWord` entries to calculate `sentimentRank`.
    - **Exceptions**: Throws `Exception` if lexicon data is missing or unreadable.

#### **`WordManager`**
- **Purpose**: Manages text processing operations, including stop word removal, punctuation handling, and unique word tracking.
- **Fields:**
  - `stopWordPath`: Path to the stop words file.
  - `stopWords`: List of stop words.
  - `uniqueWordCount`: Count of unique words in the article.
- **Methods:**
  - `getWordCount()`: Calculates and returns the total word count in the article.
  - `getUniqueWords()`: Identifies and lists unique words. Uses a hybrid approach of a `HashMap` and `ArticleWord` instances to track and cache unique words.
  - `getMostUsedUniqueWords(int timesSeen)`: Returns words that appear at least `timesSeen` times. Uses `Sorting` utility to order by frequency.
  - `removePunctuation(ArrayList<String> contents)`: Removes punctuation from the article content using regex, ensuring non-alphanumeric characters are removed. Updates `punctuationRemovedContents`.
  - `removeStopWords(ArrayList<String> contentsToUse)`: Removes stop words from the article content using the loaded list from `StopWords.txt`.
    - **Exceptions**: Throws `Exception` if the stop words file is missing or unreadable, logging errors to inform about file access issues.

### Articles/Words Subfolder

1. **`ArticleWord`**
   - Represents a single word within an article and tracks its frequency.
   - **Fields:**
     - `contents`: The word itself.
     - `timesSeen`: An integer tracking how often the word appears in the article.
   - **Methods:**
     - `incrementTimesSeen()`: Increases the `timesSeen` count by 1.

2. **`LexiconWord`**
   - Represents a word from the lexicon with a sentiment ranking.
   - **Fields:**
     - `contents`: The lexicon word.
     - `ranking`: The sentiment score associated with the word.
   - **Methods:**
     - `split()`: Processes the word into its components, storing each component for easy access during sentiment analysis.

## How to Run:
1. Add your article text files into the respective `Topic` folders within the project directory.
2. Execute the main program. It will automatically load the articles, remove stop words, and analyze word frequencies for comparison.

## Diagram
![Diagram](https://raw.githubusercontent.com/elucid503/PWLProject/refs/heads/main/Docs/Milestone1/UMLDiagram.png)

