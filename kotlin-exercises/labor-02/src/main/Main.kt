package main

/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */
fun main(){
	val dict: IDictionary = ListDictionary()
	println("Number of words: ${dict.size()}")
	var word: String?
	while(true){
		print("What to find? ")
		word = readLine()
		if( word.equals("quit")){
			break
		}
	println("Result: ${word?.let { dict.find(it) }}")
	}
}

class ListDictionary : IDictionary{
    private var words = mutableListOf<String>()
    
    init{
        //file(IDictionary.DICTIONARY_NAME).forEachLine{add(it)}
        var i = 0
        for (i in range 1..100){
            word.add("test" + "$i")
        }
    }
    
    override fun add(word: String) = words.add(word)
    override fun find(word: String) = words.find{it == word} != null
    override fun size() = words.size()
}

interface IDictionary{
    fun add(word: String): Boolean
    fun find(word: String) : Boolean
    fun size(): Int
    
   // companion object{
        //const val DICTIONARY_NAME = path
   // }
}
