classDiagram
    class Article {
        +String filePath
        +String plainTextContents
        +ArrayList~String~ arrayListContents
        +ArrayList~String~ punctuationRemovedContents
        +ArrayList~String~ stopWordRemovedContents
        +ArrayList~ArticleWord~ uniqueWords
        +WordManager wordManager
        +SentimentManager sentimentRanker
        +Article(String filePath)
        +String getName()
        +void read(boolean lower)
        +void process()
    }

    class ArticleManager {
        +Topic relatedTopic
        +ArticleManager(Topic relatedTopic)
        +Article getArticleWithRichestVocab()
    }

    class ArticleScraper {
        +static HashMap<String, String> detailsAndText(String url)
    }

    class Directories {
        +static boolean create(String path)
        +static boolean delete(String path)
        +ArrayList<String> read(String path)
        +ArrayList<String> listChildDirectories(String source)
    }

    class Files {
        +static String readAsString(String path)
        +static ArrayList~String~ readAsStringList(String path, boolean individualWords)

        +static void write(String path, String contents)
        +static void move(String oldPath, String newPath, boolean isAbsolute)
    }

    class Interactions {
        +static void startUI()
        +static void showMainMenu(Scanner scanner)
        +static void printWelcomeMessage()
        +static void printOptions(String[] options, boolean lineBreakUnder)
        +static void handleMainUI(Scanner scanner)
        +static void handleListTopicsUI(Scanner scanner)
        +static void handleManageTopicUI(Scanner scanner, String topicName)
        +static void addArticle(Scanner scanner, String topicName)
        +static void addArticleFromFile(String topicName, String filePath)
        +static void addArticleFromURL(String topicName, String url)
        +static void runSentimentAnalysis(Topic topic)
        +static void removeTopic(String topicName)
    }

    class SentimentManager {
        +Article relatedArticle
        +ArrayList~String~ rawLexiconWords
        +ArrayList~LexiconWord~ parsedLexiconWords
        +float sentimentRank
        +SentimentManager(Article article)
        +void readLexiconWords()
        +void parseLexiconWords()
    }

    class Sorting {
        +static <T> ArrayList~T~ sortObjectByIntPropertyCount(ArrayList~T~ list, String prop)
        +static <T> ArrayList~T~ sortObjectByFloatPropertyCount(ArrayList~T~ list, String prop)
    }

    class Topic {
        +String topicName
        +ArrayList~Article~ articleList
        +String dirPath
        +ArticleManager articleManager
        +Topic(String topicName)
        +void load()
        +void process()
    }

    class WordManager {
        +Article article
        +String stopWordPath
        +ArrayList~String~ stopWords
        +int uniqueWordCount
        +WordManager(Article article)
        +int getWordCount()
        +void getUniqueWords()
    }

    class Strings {
       +static ArrayList<String> convertSentencesToWords(ArrayList<String> sentences)
    }

    class Util {
        +static String resolvePath(String path)
    }

    class Logging {

        +static int lastMessageLength;

        +static void logInternal(String message)

        +static void logUI(String message)

        +static void horizontalLine()
        +static void horizontalLine(int length)

        +static void smartHorizontalLine()

    }

    class LexiconWord {
        +String lexiconLine 
        
        +String contents
        +float ranking

        +LexiconWord(String lexiconLine)
    }

    class ArticleWord {
        +String contents
        +int timesSeen
        +ArticleWord(String contents)
    }

    Article --> WordManager
    Article --> SentimentManager
    Article --> ArticleWord
    ArticleManager --> Article
    ArticleManager --> WordManager
    ArticleScraper --> Article
    Directories --> Topic
    Files --> Article
    Files --> SentimentManager
    Interactions --> Topic
    Interactions --> Article
    Interactions --> ArticleManager
    Interactions --> ArticleScraper
    SentimentManager --> Article
    SentimentManager --> LexiconWord
    Sorting --> ArticleManager
    Topic --> Article
    Topic --> ArticleManager
    WordManager --> Article
    WordManager --> ArticleWord
    Strings --> Util