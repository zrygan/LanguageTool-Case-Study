@echo off

rem by Zhean Ganituen
rem Required: a filename for dictionary (vocaulary) file
rem Usage: LT_ENCODE.bat <filename>

rem Run Java commands
java -jar libs/morfologik-stemming.jar tab2morph -i %1 -o tagalog.txt
java -jar libs/morfologik-stemming.jar fsa_build -i tagalog.txt -o tagalog.dict

echo Processing complete.
