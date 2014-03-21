gradle-plantuml-plugin
======================

Generates UML diagrams using the PlantUML syntax.
At this point in time the diagrams must be defined in a comment block of a java file defined like :
    <a href=http://plantuml.sourceforge.net/javadoc.html/>
Running 'gradle javadoc' will then go through the java files and generate the UML diagrams ready for
import.

When running the plantuml plugin, the plugin will add the following options to the javadoc task
    javadoc {
        options.addStringOption('sourcepath', project.plantuml.sourcePath)
        options.docFilesSubDirs = true
    }

This is necessary because by default the javadoc process does noet process the doc-files folders.

How To
======
To use this plugin add a buildscript dependency of the gradle-plantuml-plugin to your build.grade script
e.g.: buildscript {
          dependencies {
              classpath 'be.jlrhome.gradle:plantumlPlugin:0.0.+'
          }
      }

Once the buildscript is added, apply the plantuml plugin:
e.g.: apply plugin: 'plantuml'

run gradle javadoc to generate the javadoc


Troubleshouting
================

Simple Example
==============

buildscript {
    dependencies {
        classpath 'be.jlrhome.gradle:plantumlPlugin:0.0.+'
    }
}

apply plugin: 'java'
apply plugin: 'plantuml'
apply plugin: 'groovy'
apply plugin: 'maven'

sourceCompatibility = 1.6
targetCompatibility = 1.6

version = '0.0.1-SNAPSHOT'
group = 'be.jlrhome.gradle'
description = 'Gradle PlantUML example'

dependencies {

}