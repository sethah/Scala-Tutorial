val dealer = new Dealer("Bob", 0)
val seth = new Gambler("Seth", 0)
val joe = new Gambler("Joe", 0)
val tom = new Gambler("Tom", 0)
val players = List(dealer, seth, joe, tom)
dealer.dealHands(players)