# ConsoleBasedInventoryApp

Step 1. The program should display the menu as follows 1. Admin Login
2. Agent Login 3. Exit
Step 2. If 1 is pressed, get the admin login details and validate. On valid login, show the below menu
1. Add Product (Fields :Product Id, Product Name, MinSellQuantity, Price)
2. Display Inventory Details ( Product Id , Product Name, Quantity available,price, total cost)
3. Logout => Should display the menu on Step 1.
Note: Until logout, the menu on Step 2 should be repeated on completion of
Step 2.1 and Step 2.2
Step 3. If 2 is entered, the validate the Agent Login details and on successful login, show the below menu
1. Buy / Sell
a. Ask the user to enter Product Id
b. Ask for option to buy / sell
c. Based on the Product Id show the following details “ Product
Name, Price”.
d. Ask the user to enter “Quantity”.
e. Validate quantity available only if sell against the available
quantity. NOTE: MinSellQuantity has to be validated if
sell.
f. if quantity is available then calculate the “Total Cost” and display and ask for confirm Booking and then display menu on step 3
g. If quantity is not available for sell then show “Product not available” and display menu on step 3.
2. Show History
a. Show All Previously Buy / Sell Transaction on the logged in user
on Latest transaction First. ( Product Id, Product Name, Transaction [ Buy / Sell], Quantity, cost, total cost. ) and then display menu at step 3
3. Logout => Should display the menu on step 1.
Note: Until logout, the menu on Step 3 should be repeated on completion of Step 3.1 and Step 3.2

Instruction:
1. Predefined list of Admin & Agent ( at least 2 users in Admin and 2 in Agent ) with min of these fields ( Id, Name, Password, Mobile No )
2. Accessing the details using Database is preferred.
3. All inputs to be validated and necessary error message has to be printed.
4. MinSellQuantity field is different that the quantity is being sold / bought.
5. The given fields on the questions are the mandatory fields. You are allowed to add additional tables or fields based on your design to meet the above said functionality.
