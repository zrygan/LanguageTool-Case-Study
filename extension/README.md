# LanguageTool Extension File

By Zhean Ganituen, Stephen Borja, and Justin Ching.

This is a subdirectory in [Language Tool Case Study by Borja, Ching, and Ganituen](https://github.com/zrygan/LanguageTool-Case-Study) that creates an Extension `.oxt` file for [OpenOffice](https://www.openoffice.org/) and
[LibreOffice](https://www.libreoffice.org/).

This uses LanguageTool v6.4-STABLE. Unlike the entirity of the repository which uses LanguageTool v6.6 this subdirectory uses v6.4.

## Changing the Grammar to the Experimental Grammars

Simply replace the `grammar.xml` in `../extension/org/rules/tl` to the grammar file of your chosing.

To the same for `tagset.txt` and `tagalog.dict` in `../extension/org/resource/tl`.

## Creating the Extension from the Source

Simply clone the entire repository (or this sub-directory only), compress the `extensions/src` folder. Then, change the extension of the resulting `extension/src` ZIP file to `.oxt`.

| As per [daleif (2016)](https://tex.stackexchange.com/a/313399) on StackExchange, `oxt` files are essentially ZIP files that have a different extension (in this case `oxt`). 

## Using the Extension File

If you have OpenOffice or LibreOffice already, simply double click the `languagetool-extension.oxt` file.