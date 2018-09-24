.DEFAULT_GOAL := build-run
clean:
	./mvnw clean	
run:
	java -jar ./target/project-lvl1-s420-1.0-SNAPSHOT-jar-with-dependencies.jar 
build-run: build run
build : 
	./mvnw package	
update :
	./mvnw versions:update-properties
	./mvnw versions:display-plugin-updates
