package main

import ItemController
import ItemRepository
import ItemService

fun main(args: Array<String>) {
    val itemRepository = ItemRepository()
    val itemService = ItemService(itemRepository)
    val itemController = ItemController(itemService)

    val allItems = itemRepository.getAllItems()
    val randomItem = itemRepository.randomItem()



    
    //println(allItems)
    //println(randomItem)
    //itemRepository.save(Item("In Kotlin, what is the purpose of the `when` expression?", mutableListOf("A. To declare a new class", "B. To handle exceptions", "C. To replace the `if-else` statement for conditional branching", "D. To define a loop"), 3) )

    val selectedQuestions = itemService.selectRandomItems(5)
    println("Selected Questions:")
    selectedQuestions.forEach { item ->
        println("Question: ${item.question}")
        println("Answers: ${item.answers.joinToString("\n")}")
        println("Response: ${item.response}\n")
    }

    itemController.quiz(5)

}