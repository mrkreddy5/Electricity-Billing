# Electricity-Billing- Desk
Java Code For Electricity Billing Desk Project

Electricity Billing Desk Project

This project involves developing an Electricity Billing Desk using Java with JDBC for data storage and retrieval, and AWT and Swing for the graphical user interface (GUI). The application provides an interface for users and service providers to manage electricity billing efficiently.

Key Features:

User Management:

Sign Up: Users can sign up if they are not already registered. The system allows new users to create an account by entering necessary details.
Login: Registered users can log in to check for any pending bills associated with their service ID. The login process includes verification of credentials to ensure security.
Service Provider Login:

Authentication: Service providers must log in with verified credentials. Their access is restricted to ensure that only authorized personnel can manage bills.
Bill Management: Service providers can add bills based on the service ID of users. They can input details such as usage type and units used to update the billing records.
Billing System:

Usage Types: The system supports two types of billing:
Household Usage
Industrial Usage
Pricing Structure: Each usage type has a different pricing structure. Basic prices are applied up to a specified limit, and any additional units beyond this limit are billed at a different rate.
Calculation: The billing calculation takes into account the type of usage and the number of units consumed, applying the appropriate rates to compute the total bill.
Technologies Used:

JDBC (Java Database Connectivity): For database connectivity and operations such as data storage and retrieval.
Java GUI (AWT & Swing): For creating the user interface, including forms for user registration, login, and bill management.
The application ensures a user-friendly experience while maintaining robust security measures for both users and service providers. It effectively integrates Java’s GUI capabilities with JDBC to provide a comprehensive solution for managing electricity billing.

