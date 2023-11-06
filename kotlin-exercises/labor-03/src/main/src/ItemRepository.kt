import java.io.File
import java.io.FileWriter

class ItemRepository {
    private var items = mutableListOf<Item>()

    init {
        val lines = File("C:\Users\nagyk\Documents\androidNK2023\kotlin-exercises\labor-03\src\quiz.txt").readLines()
        for (i in lines.indices step 6) {
            val chunk = lines.subList(i, i + 6)
            items.add(Item(chunk[0], mutableListOf(chunk[1], chunk[2], chunk[3], chunk[4]), chunk[5].toInt()))

        }
        println(items)
    }

    fun randomItem(): Item? {

        if (items.isNotEmpty()) {
            val randomIndex = (0 until items.size).random()
            return items[randomIndex]
        }
        return null
    }

    fun save(item: Item): Unit {
        val file = File("C:\Users\nagyk\Documents\androidNK2023\kotlin-exercises\labor-03\src\quiz.txt")

        try {
            val fileWriter = FileWriter(file, true)
            val itemString =
                "\n${item.question}\n${item.answers[0]}\n${item.answers[1]}\n${item.answers[2]}\n${item.answers[3]}\n${item.response}\n"
            fileWriter.write(itemString)
            fileWriter.write(System.lineSeparator())
            fileWriter.close()
        } catch (e: Exception) {
            println("Error while saving the item:${e.message}")
        }

    }

    fun getAllItems(): MutableList<Item> {
        return items
    }

    fun sizeofItems(): Int {
        return items.size
    }
}