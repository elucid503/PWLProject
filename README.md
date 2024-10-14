## Programming Workshop Lab Semester Project - Article Anaylsis and Comparision Application

This software application anaylizes articles uns (basic) text analysis with the goal of comparing articles about the same topic/news

## Features
  - input articles with the same topic
  - Find and remove stop words in each article 
  - Find the uniques words count and the total works count
  - Sort and display words by frequency of occurrence

## Overview of Classes

### 'Main' 
  - loads each article and prefroms an analysis the article

### 'Topic' 
  - Manages topic
  - loads article from specified directory
 **Fields**
  - `topicName`: The name of the topic.
  - `articleList`: A list of articles belonging to the topic.
  - `dirPath`: The path to the directory containing articles.
 **Methods**
  - `Load()`: Reads the article files from the directory and loads them into the `articleList`.

### 'Dir Reader'
  - Utility class for reading directories and listing all file names.

  **Fields:**
  - `path`: The path to the directory to be read.
  - 



