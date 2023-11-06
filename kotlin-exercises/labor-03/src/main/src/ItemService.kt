class ItemService(private val itemRepository: ItemRepository) {
    fun selectRandomItems(numberOfQuestions: Int): List<Item> {
        val allItems = itemRepository.getAllItems()

        if (numberOfQuestions >= allItems.size) {
            return allItems
        }
        val selectedQuestions = mutableSetOf<Item>()
        while (selectedQuestions.size < numberOfQuestions) {
            val randomItem = allItems.random()
            selectedQuestions.add(randomItem)
        }
        return selectedQuestions.toList()
    }
}