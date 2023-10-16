import kotlin.collections.TreeSet
import java.time.LocalDate
import kotlin.random.Random

enum class DictionaryType {
    ARRAY_LIST,
    TREE_SET,
    HASH_SET
}

interface IDictionary {
    fun addWord(word: String)
    fun findWord(word: String): Boolean
    fun getSize(): Int
}

class ListDictionary : IDictionary {

    private val words = mutableListOf<String>()

    override fun addWord(word: String) {
        words.add(word)
    }

    override fun findWord(word: String): Boolean {
        return words.contains(word)
    }

    override fun getSize(): Int {
        return words.size
    }
}

class TreeSetDictionary : IDictionary {

    private val words = TreeSet<String>()

    override fun addWord(word: String) {
        words.add(word)
    }

    override fun findWord(word: String): Boolean {
        return words.contains(word)
    }

    override fun getSize(): Int {
        return words.size
    }
}

class HashSetDictionary : IDictionary {

    private val words = HashSet<String>()

    override fun addWord(word: String) {
        words.add(word)
    }

    override fun findWord(word: String): Boolean {
        return words.contains(word)
    }

    override fun getSize(): Int {
        return words.size
    }
}

class DictionaryProvider {

    private val dictionaries = mutableMapOf<DictionaryType, IDictionary>()

    fun createDictionary(type: DictionaryType): IDictionary {
        return dictionaries.computeIfAbsent(type) {
            when (type) {
                DictionaryType.ARRAY_LIST -> ListDictionary()
                DictionaryType.TREE_SET -> TreeSetDictionary()
                DictionaryType.HASH_SET -> HashSetDictionary()
                else -> throw IllegalArgumentException("Unsupported dictionary type: $type")
            }
        }
    }
}

fun String.monogram() {
    println(this.split(" ").map { it[0] }.joinToString(""))
}

fun List<String>.joinToString(separator: String): String {
  val result = StringBuilder()
  for (element in this) {
    result.append(element)
    if (element != this.last()) {
      result.append(separator)
    }
  }
  return result.toString()
}


fun List<String>.longest(): String {
    return this.maxByOrNull { it.length } ?: ""
}

data class Date(val year: Int, val month: Int, val day: Int) {
    constructor() : this(LocalDate.now().year, LocalDate.now().monthValue, LocalDate.now().dayOfMonth)

    fun compareTo(other: Date): Int {
        return if (year < other.year) -1 else if (year > other.year) 1 else 0
    }

    fun isLeapYear(): Boolean {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
    }

    fun isValid(): Boolean {
        return try {
            LocalDate.of(year, month, day)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun toString(): String {
        return "$year-$month-$day"
    }
}


fun main(args: Array<String>) {
    // Test the TreeSetDictionary class.
    val dictionary = DictionaryProvider().createDictionary(DictionaryType.TREE_SET)

    dictionary.addWord("Hello")
    dictionary.addWord("World")

    println("Dictionary size: ${dictionary.getSize()}") 

    println("Does the dictionary contain the word 'Hello'? ${dictionary.findWord("Hello")}") 


    //2

    "John Smith".monogram() 
    val list = listOf("apple", "pear", "melon")
    val joinedString = list.joinToString("#") 
    println(list)

    val longestString = list.longest() 
    println(longestString)

    //3

    val validDates = mutableListOf<Date>()
    val invalidDates = mutableListOf<Date>()

    while (validDates.size < 10) {
        val year = Random.nextInt(1900, 2101)
        val month = Random.nextInt(1, 13)
        val day = Random.nextInt(1, 29 )

        val date = Date(year, month, day)

        if (date.isValid()) {
            validDates.add(date)
        } else {
            invalidDates.add(date)
        }
    }

    println("Invalid dates:")
    invalidDates.forEach { println(it) }

    validDates.sortWith(Comparator { d1, d2 -> d1.compareTo(d2) })

    println("Sorted valid dates:")
    validDates.forEach { println(it) }
}

