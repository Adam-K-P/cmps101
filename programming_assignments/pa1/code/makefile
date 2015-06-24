#Adam Pinarbasi
#makefile

JAVAC 	= javac -Xlint
JAVASRC 	= $(wildcard *.java)
SOURCES  = $(JAVASRC) makefile README

all: $(JARFILE)

$(JARFILE): $(CLASSES)
		echo Main-class: $(MAINCLASS) > Manifest
	 	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
		chmod +x $(JARFILE)
		rm Manifest

%.class: %.java
		$(JAVAC) $<

clean:
		rm -f *.class $(JARFILE)

submit_pa1:
		submit cmps101-pt.u15 pa1 $(SOURCES)

	
