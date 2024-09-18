## Features

Initially, the program asks the user to choose between normal user or admin user option.

### Normal users

The main menu for normal user will display the following options:

1. **Exchange Currency**: This feature allowed user to specified the source and desired currency types and the amount that the user wants to convert.
2. **Display popular currencies:** This feature displayed the table of top 4 popular curriencies which decided by the admin user.
3. **Conversion History**: The user can view the history of conversion rates of admnin user between two currencies in a specified time frame. This optionn will display the history of all conversion rates as well as the numerical summary of the history which includes min, max, mean, median and standard deviation.
4. Exit: Turn off the system

### Admin user

You will require to enter the password and username if you want to login as admin user.
The main menu for the admin user will display the following options:

1. **Exchange Currency**: This feature allowed user to specified the source and desired currency types and the amount that the user wants to convert.
2. **Add currency**: The admin user is able to select this option to add new currency to the database.
3. **Update conversion rate**: The admin user is able to select this option to update currency.
4. **Popular currency actions**: The admin user can select this option to update popular currencies or display the table of top 4 popular currencies.
5. **Conversion History**: : The user can view the history of conversion rates of admnin user between two currencies in a specified time frame. This optionn will display the history of all conversion rates as well as the numerical summary of the history which includes min, max, mean, median and standard deviation.
6. Logout: log out from admin user permission.

## How to Run

1. Start the application with the command gradle run --console=plain

## How to test

1. Run all testcases with the command gradle clean test

## Data Structure

- **conversionRate.txt**: A txt file that store all the history of conversions. Each entry will include date, conversion rates, source currency type, target currency type, input amount, result amount and whether the conversion was done by admin user or not.
- **currencies.csv**: A csv file that contains six initial exchange rates.
- **currenciesHistory.txt**: A txt file that store the history of updating exchange currencies.
- **popularCurrencies.txt**: A txt file that store the order of four popular currencies.

