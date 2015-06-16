
// package blackjack

import scala.collection.mutable.MutableList

class Hand() {
  var cards = MutableList[Card]()
  var size = 0
  var handValue = 0
  var aceCount = 0

  def getScore(): Int = {
    var score = 0
    var aceCount = 0
    for (card <- cards) {
      score += card.score
      if (card.number == 'A') aceCount += 1
    }

    while (score > 21 & aceCount > 0) {
      score -= 10
      aceCount -= 1
    }

    if (score > 21) score = 0

    return score
  }

  def receiveCard(card: Card) {
    cards += card
    size += 1
    handValue = getScore()
    println(handValue)
    if (card.number == 'A') aceCount += 1
  }

  def discard() {
    size -= 1
    val drawn = cards.last
    cards = cards.init
    handValue = getScore()
    if (drawn.number == 'A') aceCount -= 1
  }

  def softSeventeen(): Boolean = {
    return (handValue == 17 & aceCount == 1 & size == 2)
  }

  def clear() = cards.clear()

  override def toString(): String = {
    var msg = cards.mkString(",")
    msg += "\nValue: " + handValue.toString
    return msg
  }

}

class Player(pname: String, buyIn: Float) {
  val name = pname
  val hand = new Hand()

  def discard () = hand.clear

  def receiveCard(card: Card) = hand.receiveCard(card)
}

class Dealer(val pname: String, val buyIn: Float) extends Player(pname, buyIn){

  var deck = getDeck()
  def getDeck(): Deck = {
    return new Deck()
  }

  def hit(): Boolean = {
    var choice = "stay"
    if (hand.handValue == 0){
      choice = "stay"
    }
    else if (hand.handValue < 17) {
      choice = "hit"
    }
    else if (hand.softSeventeen) {
      choice = "hit"
    }
    else if (hand.handValue == 17) {
      choice = "stay"
    }
    else {
      choice = "stay"
    }
    return (choice == "hit")
  }

  def dealCard(player: Player) {
    val card = deck.drawCard()
    player.receiveCard(card)
  }

  def dealHands(players: List[Player]) {
    for (c <- 1 to 2) {
      for (player <- players) {
       if (player.isInstanceOf[Gambler]){
          dealCard(player)
        }
      }
      dealCard(this)
    }
  }
}

class Gambler(val pname: String, val buyIn: Float) extends Player(pname, buyIn) {
  var x = 3
}

