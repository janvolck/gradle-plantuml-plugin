gradle-plantuml-plugin
======================

Generates UML diagrams using the PlantUML syntax.

At this point in time the diagrams must be defined in a comment block of a java file defined like :
[http://plantuml.sourceforge.net/javadoc.html](http://plantuml.sourceforge.net/javadoc.html)

Running 'gradle javadoc' will then go through the java files and generate the UML diagrams ready for
import.

When running the plantuml plugin, the plugin will add the following options to the javadoc task

    javadoc {
        options.addStringOption('sourcepath', project.plantuml.sourcePath)
        options.docFilesSubDirs = true
    }

This is necessary because by default the javadoc process does not process the doc-files folders.


How To
======
To use this plugin add a buildscript dependency of the gradle-plantuml-plugin to your build.grade script
e.g.:

    buildscript {
          dependencies {
              classpath 'be.jlr-home.gradle:plantumlPlugin:0.1.+'
          }
      }

Once the buildscript is added, apply the plantuml plugin:
e.g.: apply plugin: 'plantuml'

run gradle javadoc to generate the javadoc


Troubleshooting
===============
* !@#$%^ I'm not using the default src/main/java structure and now the doc-files folder is not recognized by javadoc.

   No problem. You can change the javadoc sourcepath, so that the folders are still found and integrated in your javadoc.
   Just overload plantuml.sourcepath and the magic will happen again.
   e.g.:

```groovy
    plantuml {
       sourcePath = 'A/src/main/java' +
               ':B/src/main/java' +
               ':C/src/main/java'
    }
```

Simple Example
==============

    buildscript {
        dependencies {
            classpath 'be.jlr-home.gradle:plantumlPlugin:0.1.+'
        }
    }

    apply plugin: 'java'
    apply plugin: 'plantuml'
    apply plugin: 'groovy'
    apply plugin: 'maven'

    sourceCompatibility = 1.6
    targetCompatibility = 1.6

    version = '0.0.1-SNAPSHOT'
    group = 'be.jlr-home.gradle'
    description = 'Gradle PlantUML example'

    dependencies {

    }

