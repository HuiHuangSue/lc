# sed, 'said', stream editor

# SUBSTITUTE
# replaces the first word “unix” with “linux” in the file
sed 's/unix/linux/' geekfile.txt
# replaces the second occurrence of the word “unix” with “linux” in a line.
sed 's/unix/linux/2' geekfile.txt
sed ‘s/old_pattern/new_pattern/n’ filename 
# Replacing all the occurrence of the pattern in a line
$sed 's/unix/linux/g' geekfile.txt
# replaces the third “unix” word with “linux” word in a line.
$sed 's/unix/linux/3g' geekfile.txt
# replaces the string only on the third line.
$sed 's/unix/linux/p' geekfile.txt
# display only the replaced lines
$sed -n 's/unix/linux/p' geekfile.txt
# And if you wish to print the replaced lines twice, then only use “/p” print flag without “-n” option-  
sed 's/to/TWO/p' a.txt
# replaces the lines with range from 1 to 3
$sed '1,3 s/unix/linux/' geekfile.txt
# replaces the text from second line to last line in the file.
$sed '2,$ s/unix/linux/' geekfile.txt
# Change the first occurrence of the pattern – 
sed 's/life/leaves/' a.txt
# Replacing pattern on a specific line number. Here, “m” is the line number. 
sed ‘m s/old_pattern/new_pattern/’ filename 
sed '3 s/every/each/' a.txt
# If you wish to print only the replaced lines –  
sed -n '3 s/every/each/p' a.txt

# If you wish to replace pattern in order to ignore character case (beginning with uppercase or lowercase), then there are two ways to replace such patterns – 
First, By using “/i” print flag – 
sed ‘s/old_pattern/new_pattern/i’ filename 
sed 's/life/Love/i' a.txt
Second, By using regular expressions –  
sed 's/[Ll]ife/Love/g' a.txt
# To replace multiple spaces with a single space –  
sed 's/  */ /g' filename
@ Replace one pattern followed by the another pattern – 
sed ‘/followed_pattern/ s/old_pattern/new_pattern/’ filename 
sed '/is/ s/live/love/' a.txt
# Replace a pattern with other except in the nth line. 
sed ‘n!s/old_pattern/new_pattern/’ filename 
sed -i '5!s/life/love/' a.txt

# DELETE
# To Delete a particular line say n in this example
$ sed 'nd' filename.txt
$ sed '5d' filename.txt
# To Delete a last line
$ sed '$d' filename.txt
# To Delete line from range x to y
$ sed 'x,yd' filename.txt
$ sed '3,6d' filename.txt
# To Delete from nth to last line
$ sed 'nth,$d' filename.txt
$ sed '12,$d' filename.txt
# To Delete pattern matching line
$ sed '/pattern/d' filename.txt
$ sed '/abc/d' filename.txt
# Delete lines starting from nth line and every 2nd line from there
$sed '3~2d' a.txt # from 3rd line, delete every 2nd line
# Delete the lines which matches the pattern and 2 lines after to that – 
sed ‘/pattern/,+2d’ filename 
sed '/easy/,+2d' a.txt
# Delete blank Lines  
sed '/^$/d' a.txt
# Delete empty lines or those begins with “#” –  
sed -i '/^#/d;/^$/d' a.txt


# VIEW, PRINT
# View/Print the files 
If we want to view content of file, then we use cat command
if we want to view the bottom and the top content of any file, we use tools such as head and tail. 
But what if we need to view a particular section in the middle of any file? Here we’ll discuss, how to use SED command to view a section of any file. 

# Viewing a file from x to y range – 
sed -n ‘x,yp’ filename 
sed -n '2,5p' a.txt
# View the entire file except the given range – 
sed ‘x,yd’ filename 
sed '2,4d' a.txt
# Print nth line of the file – 
sed -n ‘address’p filename 
sed -n '4'p a.txt
# Print lines from xth line to yth line. 
sed -n ‘x,y’p filename 
sed -n '4,6'p a.txt
# Print only the last line – 
sed -n ‘$’p filename 
# Print from nth line to end of file – 
sed -n ‘n,$p’ filename 
sed -n '3,$'p a.txt

# Pattern Printing 
# Print the line only which matches the pattern – 
sed -n /pattern/p filename 
sed -n /every/p a.txt
# Print lines which matches the pattern i.e from input to xth line. 
sed -n ‘/pattern/,xp’ filename 
sed -n '/everyone/,5p' a.txt
# Prints lines from the xth line of the input, up-to the line which matches the pattern. If the pattern doesn’t found then it prints up-to end of the file. 
Syntax: sed -n ‘x,/pattern/p’ filename 
sed -n '1,/everyone/p' a.txt
# Print the lines which matches the pattern up-to the next xth lines – 
sed -n ‘/pattern/,+xp’ filename 
sed -n '/learn/,+2p' a.txt


# Insert one blank line after each line –  
sed G a.txt
# To insert two blank lines –  
sed 'G;G' a.txt
# Delete blank lines and insert one blank line after each line –  
sed '/^$/d;G' a.txt
# Insert a black line above every line which matches “love” –  
sed '/love/{x;p;x;}' a.txt
# Insert a blank line below every line which matches “love” –  
sed '/love/G' a.txt
# Insert 5 spaces to the left of every lines –  
sed 's/^/     /' a.txt

