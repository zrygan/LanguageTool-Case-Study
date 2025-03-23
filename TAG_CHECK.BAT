@echo off

rem by Zhean Ganituen
rem Required: a filename for dictionary (vocaulary) file
rem Usage: LT_RUN.bat

rem Run LanguageTool with custom FIlipino Grammar rules
echo TAG_CHECK :: Compiling Java Code (must be Java 17).
javac -cp languagetool.jar -d . Filipino_Language/*.java

echo TAG_CHECK :: Running LanguageTool
java -cp "languagetool.jar;." Filipino_Language/Filipino

