class ItemController(private val itemService: ItemService){
    fun quiz(numberOfQuestions: Int){
        val selectedQuestions = itemService.selectRandomItems(numberOfQuestions)
        var correctAnswers = 0

        for ((index,question) in selectedQuestions.withIndex()){
            println("Question ${index + 1}:\n${question.question}")
            println("Answers:\n${question.answers.joinToString("\n")}")

            print("Your Response (1-${question.answers.size}): ")
            val userResponse = readLine()?.toIntOrNull()

            if (userResponse != null && userResponse in 1..question.answers.size) {
                if (userResponse == question.response) {
                    correctAnswers++
                    println("Correct!\n")
                } else {
                    println("Incorrect. The correct response is ${question.response}.\n")
                }
            } else {
                println("Invalid response. Please enter a valid option.\n")
            }
        }
        val totalQuestions = selectedQuestions.size
        println("Quiz Completed")
        println("Correct answers: $correctAnswers/$totalQuestions")
        }
    }
