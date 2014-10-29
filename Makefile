default: all

all:
	javac -d classes -cp deps/junit-4.11.jar:deps/hamcrest-core-1.3.jar src/*.java
