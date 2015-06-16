
// package blackjack

import scala.collection.mutable.MutableList
import scala.util.Random


class Card(numberc: Char, suitc: Char) {
    val suit = suitc;
    val number = numberc;
    val score = Card.valueMap(number)
    
    override def toString = "<" + number.toString + ", " + suit.toString + ">";
}
object Card {
  val valueMap = Map('2' -> 2, '3' -> 3, '4' -> 4,
                    '5' -> 5, '6' -> 6, '7' -> 7,
                    '8' -> 8, '9' -> 9, 'T' -> 10,
                    'J' -> 10, 'Q' -> 10, 'K' -> 10,
                    'A' -> 11);
}

class Deck() {
    var cards = MutableList[Card]()
    fillDeck()
    shuffle()

    def fillDeck() {
        for ((num, value) <- Card.valueMap) {
            for (suit <- "CDHS".toList){
                cards += new Card(num, suit);
            }
        }
    }

    def shuffle() {
      cards = Random.shuffle(cards)
    }

    def drawCard(): Card = {
      if (!isEmpty) {
        val drawn = cards.last
        cards = cards.init
        println(drawn)
        return drawn
      }
      else {
        println("Empty deck")
        return null
      }
    }

    def addCards(newCards: MutableList[Card]) {
      cards ++= newCards
    }

    def isEmpty = cards.isEmpty
}