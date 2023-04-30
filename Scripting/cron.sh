* * * * *
min[0-59] hour[0-24] day[0-31] month[0-12] dayOfWeek[0-7]
crontab -e(edit/add) -r(delete) -l(list)

# Run /home/folder/gfg-code.sh every hour, from 9:00 AM to 6:00 PM, everyday.
00 09-18 * * * /home/folder/gfg-code.sh
# Run /usr/local/bin/backup at 11:30 PM, every weekday.
30 23 * * Mon, Tue, Wed, Thu, Fri /usr/local/bin/backup
Run sample-command.sh at 07:30, 09:30, 13:30 and 15:30.
30 07, 09, 13, 15 * * * sample-command.sh
