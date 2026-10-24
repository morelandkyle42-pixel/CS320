# CS320
CS 320 Software Testing and Automation

This repository contains my contact service code and JUnit tests from Project One and my summary and reflections report from Project Two.

How can I ensure that my code, program, or software is functional and secure?

I can start by testing my code against the requirements and checking invalid/valid inputs. In my contact service, I tested null values, field limits, and phone numbers that contained letters. Running the tests after changes helped me check that existing features still worked. These tests do not prove the software is secure, so I would also need code reviews and security testing before using it in a larger application.

How do I interpret user needs and incorporate them into a program?

I break the requirements into smaller rules that I can write code and tests for. For example, the contact phone number had to contain exactly 10 digits. I used that requirement to decide which values the program should accept and which it should reject. If a requirement is unclear, I would ask questions before building something that might not meet the user’s needs.

How do I approach designing software?

I start with the requirements and work on one part at a time. For this project, the contact class handled the contact information and validation while the service handled adding, updating, and deleting contacts. Keeping those responsibilities separate made the code easier for me to follow and test. I would use the same approach in future projects and keep testing as I make changes.
