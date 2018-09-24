.DEFAULT_GOAL := compile-run
clean:
	rm -rf ./target
compile:
	mkdir -p ./target/classes
	javac -d ./target/classes ./src/main/java/games/Slot.java
run:
	java -cp ./target/classes games.Slot
compile-run: compile run
