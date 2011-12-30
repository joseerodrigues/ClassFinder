ClassFinder
===========

Small utility to scan a JAR, WAR, EAR or directory tree for a given class or package name.

This can be used as a library or standalone.


### Usage as standalone

java -jar ClassFinder.jar [-all] [class or package name] [searchDir01] [searchDir02] ...

Use '-all' to list all found classes, or provide a full or partial class or package name.

Besides searching directories, you can also specify a file to search inside instead (Jar, War, Ear).