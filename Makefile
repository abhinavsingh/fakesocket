.PHONY: src test

default: test

src:
	javac -d classes -cp deps/junit-4.11.jar:deps/hamcrest-core-1.3.jar src/*.java

test: src
	javac -d classes -cp deps/junit-4.11.jar:deps/hamcrest-core-1.3.jar:src test/*.java

client:
	java -cp classes RedisClient

client-test:
	java -cp classes:deps/junit-4.11.jar:deps/hamcrest-core-1.3.jar TestSuite
