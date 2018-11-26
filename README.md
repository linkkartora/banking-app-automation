# banking-app-automation
The main purpose of this project is to test the following function of Banking App:
URL: http://demo.guru99.com/v4/

Function to test:
- Verify new customer can be created. Method: testCreateCustomer().
- Verify to create new account based on the customer just created above. Method: testCreateAccount().
- Verify deposit function work fine with the account just created. Method: testAddDeposit().

Technology: This project is using Selenium version 2.50 + Java. This was tested on Chrome version 70.0.3538.110 and the ChromeDriver version 2.44

Structure:
- src/pages package: contains list of target pages. Each of these files contains the defined object, data and the nessesary methods.
- src/tests package: contains the test file which used for automation test.

Limited: The email for create new Customer is currently hardcoded. In order to run the scripts, please change the email for valid one. You can use the email with format: automationteam_xxx@yopmail.com where xxx is the number from 200 to 800.

App limitation: the email for create new Customer and the description field for add new deposit is limited with 30 characters for email field and 10 chars for the description field.

Issue: No issue found so far.
