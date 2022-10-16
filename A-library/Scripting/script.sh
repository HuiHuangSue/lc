###############################################################################
SED 
# print: 
    sed -n '10p' file.txt; print 10th line
# substitute: 
    # substitute earch searchStr w/ replaceStr for each line
        sed 's/searchStr/replaceStr/'file.txt; #default applies to all
        sed 's/searchStr/replaceStr/2' file.txt;  # 2th occurrence only
        sed 's/unix/linux/g' file.txt; # g as global, replace all
        sed 's/unix/linux/3g'file.txt; # from 3rd and ongoing
        sed '3 s/unix/linux/' file.txt; # to 3rd line only
        sed '1,3 s/unix/linux/' file.txt; # 1st to 3rd line
        sed '3,$ s/unix/linux/' file.txt; #3rd to end end lines
# delete:
    sed '5d' file.txt; # delete 5th line
    sed '$d' file.txt; # delete last line
    sed '5,12d' file.txt; # delete from 5th to 12th line
    sed '5,$d' file.txt ; #delete 5th to last line
    sed '/pattern/d' file.txt #delete lines with the pattern

###############################################################################
AWK
# print
    awk '{print}' file.txt # print line by line
    awk '/pattern/ {print}' file.txt # only print lines containing pattern
    awk '{print $1, $4}' file.txt # ($1 based), $0 is entire line
    awk 'NR==3, NR==6 {print NR,$0}' file.txt; # Filter & Format on matched lines  line3-6 NR=lineNum
    awk '{print NR "- " $1 }' file.txt  # format
    awk 'length($0) > 10' file.txt; # print lines w/ len char > 10: 
    awk 'END {print NR}' file.txt # print num of lines, must have NR otherwise will print NR lines out
    awk '{if (length($0)>max) max=length($0) } END { print max }' file.txt; #print longest length of line
    awk 'BEGIN { for(i=1;i<=6;i++) print "square of", i, "is",i*i; }'; # print formatted for line range: 

###############################################################################
Grep -i :'ignore case'; -e :'multiple regex'; 

###############################################################################
# 195: print 10th line of file.txt                                            
sed -n "10p" file.txt
# tail -n+k, start from kth to end; canont use head first, as line may < 10
tail -n+10 file.txt | head -1 
# NR==10 as filter, not assignment
awk 'NR==10' file.txt OR awk 'NR==10 {print}' file.txt 

###############################################################################
# 193 valid phont num                                                         
# grep regex (basic); grep -e, extended regex; grep -P regex can use d as digits
# use ^ and $. use \(, \), \d to escape; ^(\(\d{3}\) |\d{3}-) \d{3}-\d{4}$
grep -P "^(\(\d{3}\) |\d{3}-)\d{3}-\d{4}$" file.txt #

###############################################################################
# 192 Calculate the frequency of each word in a text file words.txt.
# https://leetcode.com/problems/word-frequency/solutions/849332/one-line-with-pipe-with-explanation/
:' only unique lowercase characters and space ' ' characters. Words separated by >=1 ' '.
the day is sunny the the
the sunny is is
---> sorted by descending frequency:
the 4
is 3
sunny 2
day 1'
# truncate substitute; uniq --count; sort -reverseToDescending
cat words.txt | tr -s ' ' '\n' | sort | uniq --count | sort -r | awk '{print $2 " " $1}'

###############################################################################
# 194 Transpose file
# NR line_number; NF column_number
# https://leetcode.com/problems/transpose-file/solutions/111382/solution-using-awk-with-explanations/
#// 第一行append所有$1, 其他行append所有$2
: 'Each row has the same number of columns [NF], and each field is separated by ' '.
name age
alice 21
ryan 30
---> 
name alice ryan
age 21 30'

awk ' # this for loop runs through each line, NR 1..last line
    { 
        for( i = 1; i <= NF; i++) { // <=, inclusive
            if (NR == 1) {
                s[i] = $i;   # s[1]=$1=name, s[2]=$2=age
            } else {
                s[i] = s[i] " " $i; #s[1]: "name" --> "name alice"; s[2]: "age" --> "age 21"
            }
        }
    } # this code after END executes after every line finishes. 
    END {
        for ( i = 1; s[i] != ""; i++) { # use s[i] != "" empty line as termination condition
            print s[i];
        }
    }
' file.txt

awk '
    {
        for( i = 1; i <= NF; i++) {
            s[i] = s[i] " " $i;
        }
    }
    END {
        for ( i = 1; s[i] != ""; i++) {
            print s[i];
        }
    }
' file.txt | sed 's/^ *//g' #remove leading space for each line
