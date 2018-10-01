.DEFAULT_GOAL := build-run
clean:
	./mvnw clean	
run:
	java -jar ./target/project-lvl1-s420-1.0-SNAPSHOT-jar-with-dependencies.jar 
build-run: clean build run
install : clean
	./mvnw install
build : clean 
	./mvnw package	
update :
	./mvnw versions:update-properties
	./mvnw versions:display-plugin-updates
test :
	./mvnw surefire:test
