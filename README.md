# **Word Cloud**

#### Author: Marina Nikonchuk
#### Final Project for the Module "Object Oriented Programming" of the H.Dip in Computer Science in Software Development - GMIT

### Project Description

This program generates a word cloud, which is a visual representation of text. Word cloud is commonly used to display a visual summary of the most important words used on a web page, news forum, or social media website. Each word is represented as a tag, with the font size or color indicating its importance based on occurrence frequency.

The goal of this project was to develop a Java application that can parse a file or URL and generate a PNG file with a word cloud displaying the most prominent words. The resulting word cloud is created with words arranged in decreasing font size, style, and color.

The enclosed wcloud.jar fulfills this requirement by providing a command line interface, allowing the user to:

- Select a file or an URL to parse
- Specify the Number of Words to Display
- Specify the filename for the resulting WordCloud.
The WordCloud presents as a png graphic, with the words displayed in differing font sizes and colours.

With the help of this program a user may parse a file or a URL to generate a PNG file with a word-cloud displaying the most prominent words in decreasing font size, style and colour. With the help of menu the user  may choose option that suits him and create a PNG file from text or URL. Only words that are not in the file ignorewords.txt are added to the PNG file. 

### To Run (In Command Prompt)
Option 1: To execute the JAR use> in WordCloudGenerator: java -cp ./wcloud.jar ie.gmit.dip.Runner

Option 2: Compile & run source. Navigate to the folder ../WordCloudGenerator/src: javac ie/gmit/dip/*.java java ie.gmit.dip.Runner

### References:

1) Lectures on the subject of Advanced-Object Oriented Software Development, Dr. J.Healy  
2) https://www.infoworld.com/article/2076764/image-processing-with-java-2d.html
3) https://www.youtube.com/watch?v=fxQ0B6BkfKo  
4) https://stackoverflow.com/
5) https://github.com/
6) https://learnonline.gmit.ie/pluginfile.php/434663/mod_resource/content/1/javadoc.pdf
7) https://www.albany.edu/faculty/jmower/geog/gog692/ImportExportJARFiles.htm 
8) https://randomcoder.org/articles/building-a-tag-cloud-in-java 
9) https://www.javatips.net/api/kumo-master/src/main/java/com/kennycason/kumo/WordCloud.java 
10) https://idratherbewriting.com/learnapidoc/nativelibraryapis_create_javadoc.html  

