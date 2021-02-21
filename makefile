all: jar

classes:
	javac -sourcepath src -d dist/classes src/Main.java

jar: classes
	jar cfm dist/farkle.jar Manifest.txt -C dist/classes/ .

test: all
	clear; java -jar dist/farkle.jar

clean:
	rm -rf dist/*