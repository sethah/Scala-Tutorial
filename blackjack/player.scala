
// package blackjack

// import scala.collection.mutable.MutableList

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

    return score
  }

  def receiveCard(card: Card) {
    cards += card
    size += 1
    handValue = getScore()
    if (card.number == 'A') aceCount += 1
  }

  def getCard(idx: Int): Card = {
    val show = this.cards(idx)
    return show
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

  def clear() {
    cards.clear()
    handValue = getScore()
  }

  override def toString(): String = {
    var msg = cards.mkString("|")
    msg += ", Value: " + handValue.toString
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

  def playHand() {
    while (this.hit) {
      dealCard(this)
    }
  }

  def dealCard(player: Player) {
    val card = deck.drawCard()
    player.receiveCard(card)
  }

  def showCard(): Card = {
    return hand.getCard(1)
  }

  def dealHands(players: List[Gambler]) {
    deck = getDeck
    for (c <- 1 to 2) {
      for (player <- players) {
        dealCard(player)
      }
      dealCard(this)
    }
  }

  def processHits(players: List[Gambler]) {
    for (player <- players) {
      while (player.hit) {
        this.dealCard(player)
        if (player.hand.handValue == 21) {
          println("Nice hand!")
        }
        else if (player.hand.handValue > 21) {
          println("You busted!")
        }
      }
    }
  }

  def evaluateHand(playerScore: Int, dealerScore: Int): String = {
    if (playerScore > 21) {
      return "Lose"
    }
    else if (dealerScore > 21) {
      return "Win"
    }
    else if (playerScore > dealerScore) {
      return "Win"
    }
    else if (playerScore == dealerScore) {
      return "Push"
    }
    else {
      return "Lose"
    }
  }

  def evaluateHands(players: List[Gambler]) {
    for (player <- players) {
      val result = evaluateHand(player.hand.handValue, this.hand.handValue)
      printf("%s: %s\n", player.name, player.hand.toString)
      printf("Dealer: %s\n", this.hand.toString)
      printf("***%s***\n", result)
      player.discard()
    }
    this.discard()
  }
}

class Gambler(val pname: String, val buyIn: Float) extends Player(pname, buyIn) {
  def hit(): Boolean = {
    if (this.hand.handValue > 20) {
      return false
    }
    printf("%s, you have %s\n", this.name, this.hand.toString)
    val hit = scala.io.StdIn.readLine("Do you want to hit? (y/n) ")
    println
    return (hit == "y")
  }

}

class Blackjack() {
  val dealer = new Dealer("Bob", 0)
  val p1 = new Gambler("Seth", 10)
  val p2 = new Gambler("Tom", 9)
  val p3 = new Gambler("Joe", 1)
  val players = List(p1, p2, p3)

  def playRound() {
    dealer.dealHands(players)
    val dealerCard = dealer.showCard
    printf("Dealer has %d, %s turned up\n", dealerCard.score, dealerCard)
    dealer.processHits(players)
    println(dealer.hand)
    dealer.playHand
    dealer.evaluateHands(players)
  }
}
