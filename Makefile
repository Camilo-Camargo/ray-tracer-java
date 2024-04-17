SOURCE=$(wildcard src/**/*.java src/*.java src/**/**/*.java **/**/*.java)

all: compile run

compile:
	rm -rf bin
	mkdir -p bin  
	javac -cp bin/  -d bin/ $(SOURCE) -g

run:
	java -cp bin tracer.Tracer

png:
	convert img.ppm img.png
	#feh img.ppm 

share:
	rm -rf bin
	mkdir -p zip
	zip -r zip/tracer.zip ./ 

clean:
	rm -rf bin
