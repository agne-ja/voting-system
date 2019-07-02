# voting-system
Voting system project for oop class


Program window components and their functionality:


• Log in panel. 
It's where the user logs in to vote by inputting their ID. 
If the user writes a wrong ID or any other form of incorrect input, an error message pops up.

• Date panel. 
Displays the current time of the system and allows the user to simulate time flow by pressing buttons(add 1 day, week or month to the current time). 

• Voting panel. 
It is visible only when a voter is logged in and a poll is happening. 
If at least one of those conditions is not met, the panel title displays the date of the next available poll. 
When both conditions are true, the panel shows the name of the current poll, its start and end dates, and the list of candidates for which the user can vote. 
Also, it has a button that allows users to cast their votes if they selected a candidate and hadn't voted in the poll before.

• Result list. 
Displays the results of any polls that happened before the current date of the system 
(results may be inconclusive if more than one candidate got the same amount of votes). 
