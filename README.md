# Programming Workshop Lab Semester Project - Article Analysis and Comparison Application

## Developer Contact:
- **Paul Ujlaky** - paul.ujlaky@student.fairfield.edu
- **James Doyle** - james.doyle@student.fairfield.edu
- **Ronny Cuji** - ronny.cuji@student.fairfield.edu

This software application performs basic text analysis with the goal of comparing articles about the same topic/news.

## Features
- Input articles with the same topic.
- Find and remove stop words in each article.
- Find the unique word count and the total word count.
- Sort and display words by frequency of occurrence.

## Overview of Classes

### `Main`
- Loads each article and performs an analysis on the article.

### Util Folder

#### **Files**:
1. **`DirReader`**
   - Utility class for reading directories and listing all file names.
   - **Fields:**
     - `path`: The path to the directory to be read.
   - **Methods:**
     - `Read()`: Reads the directory and returns a list of file names.

2. **`FileReader`**
   - Handles reading content from files, either as a whole string or as a list of individual words.
   - **Fields:**
     - `Path`: The file path to the document being read.
   - **Methods:**
     - `readAsString()`: Reads the entire content of the file as a single string.
     - `readAsStringList(individualWords)`: Reads the content of the file and returns it as a list of strings, either line-by-line or split into individual words if `individualWords` is true.

3. **`PathResolver`**
   - Resolves relative file paths into absolute file paths.
   - **Fields:**
     - `Path`: The relative path to resolve.
   - **Methods:**
     - `Resolve()`: Returns the absolute path based on the provided relative path.

#### **Misc**:
1. **`Sorting`**
   - Utility class for sorting objects based on an integer property using Java reflection.
   - **Methods:**
     - `sortByObjectPropertyCount()`: Sorts a list of objects (like `Word` objects) based on a given integer field, such as `timesSeen`, in descending order.

#### **Strings**:
1. **`SentenceManager`**
   - A utility class for managing text by splitting sentences into individual words.
   - **Methods:**
     - `convertSentencesToWords()`: Takes an `ArrayList` of sentences and splits them into individual words for analysis.

### Classes Folder

#### **`Topic`**
- Manages topics and loads articles from a specified directory.
- **Fields:**
  - `topicName`: The name of the topic.
  - `articleList`: A list of articles belonging to the topic.
  - `dirPath`: The path to the directory containing articles.
- **Methods:**
  - `Load()`: Reads the article files from the directory and loads them into the `articleList`.

#### **`Word`**
- Represents a single word and keeps track of how many times it has been seen during the analysis.
  
- **Fields:**
  - `contents`: The word itself as a string.
  - `timesSeen`: An integer that tracks how many times this word appears across all articles.
  
- **Methods:**
  - `incrementTimesSeen()`: Increases the `timesSeen` count by 1.

## How to Run:
1. Add your article text files into the respective `Topic` folders within the project directory.
2. Execute the main program. It will automatically load the articles, remove stop words, and analyze word frequencies for comparison.
