# 195: print 10th line of file.txt
sed -n "10p" a.txt
tail -n+10 file.txt | head -1 # tail -n+k, start from kth to end
awk 'NR==10' file.txt OR awk 'NR==10 {print}' file.txt


# 193 valid phont num
grep -P "^(\(\d{3}\) |\d{3}-)\d{3}-\d{4}$" file.txt #^(\(\d{3}\) |\d{3}-) \d{3}-\d{4}$


# 192 Write a bash script to calculate the frequency of each word in a text file words.txt.
:' only unique lowercase characters and space ' ' characters.
Words are separated by one or more whitespace characters.
Example:
Assume that words.txt has the following content:
the day is sunny the the
the sunny is is
Your script should output the following, sorted by descending frequency:
the 4
is 3
sunny 2
day 1

Could you write it in one-line using Unix pipes? '
# https://leetcode.com/problems/word-frequency/solutions/849332/one-line-with-pipe-with-explanation/
cat words.txt | tr -s ' ' '\n' | sort | uniq --count | sort -r | awk '{print $2 " " $1}'
:'truncate substitute; uniq --count; sort -reverseToDescending'


# 194 Transpose file
" 'Given a text file file.txt, transpose its content.
Each row has the same number of columns, and each field is separated by the ' ' character.
Example: If file.txt:
name age
alice 21
ryan 30
Output the following:
name alice ryan
age 21 30'






