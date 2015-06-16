// In WorldlyApp.scala
// A singleton object with a main method that allows
// this singleton object to be run as an application
object WorldlyApp {
  def main(args: Array[String]) {
    val wg = new WorldlyGreeter("Hello")
    wg.greet()
  }
}