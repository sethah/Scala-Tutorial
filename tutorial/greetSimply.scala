// class SimpleGreeter {
// 	val greeting = "Hello, world!"
// 	def greet() = println(greeting)
// }
// class FancyGreeter(greeting: String) {
// 	if (greeting == null) {
// 		throw new NullPointerException("null greeting")
// 	}
// 	def greet() = println(greeting)
// }
// class RepeatGreeter(greeting: String, count: Int) {
// 	def this(greeting: String) = this(greeting, 1)

// 	def greet() = {
// 		for (i <- 1 to count) {
// 			println(greeting)
// 		}
// 	}
// }

// The WorldlyGreeter class
class WorldlyGreeter(greeting: String) {
  def greet() = {
    val worldlyGreeting = WorldlyGreeter.worldify(greeting)
    println(worldlyGreeting)
  }
}

// The WorldlyGreeter companion object
object WorldlyGreeter {
  def worldify(s: String) = s + ", world!"
}

// val greeting = "Hello"
// val worldlyGreeting = WorldlyGreeter.worldify(greeting)
// worldlyGreeting.greet()