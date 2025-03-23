@echo off

rem by Zhean Ganituen
rem Required: a filename for dictionary (vocaulary) file
rem Usage: LT_DECODE.bat <filename>

rem Run Java commands
java -jar morfologik-stemming.jar fsa_dump -d tagalog.dict -x > tagalog.txt

echo Processing complete.
