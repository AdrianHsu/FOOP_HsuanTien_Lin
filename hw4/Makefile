JAVAFLAGS = -g -d ./lib/

all: hw4
# lib/*, PlayerXXX.class that you get from your classmate, (thus remove PlayerB03901023.class of my own)
# Player.class and other utility classes that you get from the class website
clean:
	rm ./lib/Dealer.class ./lib/DeckOfCards.class ./lib/PlayerStatus.class ./lib/POOCasino.class ./lib/PlayerB03901023.class
hw4:
	javac -cp ./lib/ $(JAVAFLAGS) src/*java
run:
	java -cp ./lib/ POOCasino 100000 1000000 PlayerB03901023 PlayerB03902039 PlayerB03901023 PlayerB03902039