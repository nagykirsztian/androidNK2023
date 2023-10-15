package main

import kotlin.random.Random

fun isPrime(number: Int): Boolean {
  for (i in 2 until number) {
    if (number % i == 0) {
      return false
    }
  }
  return true
}

fun encode(string: String): String {
  val encodedString = StringBuilder()
  for (char in string) {
    encodedString.append(char + 1)
  }
  return encodedString.toString()
}

fun decode(string: String): String {
  val decodedString = StringBuilder()
  for (char in string) {
    decodedString.append(char - 1)
  }
  return decodedString.toString()
}

fun encodeOrDecode(string: String, encodeOrDecodeFn: (String) -> String): String {
  return encodeOrDecodeFn(string)
}

fun printEvenNumbers(list: List<Int>) {
  // Filter the list to only include even numbers.
  val evenNumbers = list.filter { it % 2 == 0 }

  // Print the even numbers.
  evenNumbers.forEach { println(it) }
}

fun main() {
  // 1st
  println("1st")
  val a = 2
  val b = 3
  val template = "$a + $b = ${a + b}"
  println(template)
  println()

  // 2nd
  println("2nd")
  val daysOfWeek = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

  for (day in daysOfWeek) {
    println(day)
  }

  val daysStartingWithT = daysOfWeek.filter { it.startsWith("T") }
  println(daysStartingWithT)

  val daysContainingE = daysOfWeek.filter { it.contains("e") }
  println(daysContainingE)

  val daysOfLength6 = daysOfWeek.filter { it.length == 6 }
  println(daysOfLength6)

  println()

  // 3rd
  println("3rd")
  for (i in 1..100) {
    if (isPrime(i)) {
      println(i)
    }
  }
  println()

  // 4th
  println("4th")
  val string = "Hello, world!"
  val encodedString = encode(string)
  val decodedString = decode(encodedString)

  assert(encodedString != string)
  assert(decodedString == string)

  val message = "This is a secret message."
  val encodedMessage = encodeOrDecode(message, { string -> encode(string) })
  val decodedMessage = encodeOrDecode(encodedMessage, { string -> decode(string) })

  println("Encoded message: $encodedMessage")
  println("Decoded message: $decodedMessage")
  println()

  // 5th
  println("5th")
  val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  printEvenNumbers(list)
  println()

  // 6th
  println("6th")
  val listOfIntegers = listOf(1, 2, 3, 4, 5)

  val doubledList = listOfIntegers.map { it * 2 }
  println(doubledList)

  val capitalizedDaysOfWeek = daysOfWeek.map { it.toUpperCase() }
  println(capitalizedDaysOfWeek)

  val firstCharactersOfDaysOfWeek = daysOfWeek.map { it[0].toUpperCase() }
  println(firstCharactersOfDaysOfWeek)

  val lengthsOfDaysOfWeek = daysOfWeek.map { it.length }
  println(lengthsOfDaysOfWeek)

  val averageLengthOfDaysOfWeek = lengthsOfDaysOfWeek.average()
  println("Average length of days: $averageLengthOfDaysOfWeek")
  println()

  // 7th
  println("7th")

  val mutable
     //7th
    println("7th")


     val mutableDaysOfWeek = daysOfWeek.toMutableList()
    	mutableDaysOfWeek.removeAll { it.contains("n") }
     println(mutableDaysOfWeek)
     val daysOfWeekWithIndex = mutableDaysOfWeek.withIndex()
      for (dayWithIndex in daysOfWeekWithIndex) {
    println("Item at ${dayWithIndex.index} is ${dayWithIndex.value}")
  }

  mutableDaysOfWeek.sort()
  println(mutableDaysOfWeek) 
  println()
  
  //8th
   println("8th")
 
  val randomArray = Array(10) { Random.nextInt(101) }


  println("Random array:")
  randomArray.forEach { println(it) }


  randomArray.sort()


  println("Sorted array:")
  randomArray.forEach { println(it) }

  val containsEvenNumber = randomArray.any { it % 2 == 0 }
  println("Does the array contain any even number? $containsEvenNumber")

  val allEvenNumbers = randomArray.all { it % 2 == 0 }
  println("Are all the numbers even? $allEvenNumbers")

  val average = randomArray.average()
  println("Average of generated numbers:")
  println(average) 
  println() 
