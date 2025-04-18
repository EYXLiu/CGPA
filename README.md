# University of Waterloo CGPA Calculator
Tech Stack: Java, Spring Framework, Spring Boot, Supabase, JWT

# About
* Since Waterloo conversion to 4.00 GPA Scale is somewhat confusing, as described below, this web app is designed to easily convert from Waterloo % to a corresponding US 4.00 GPA Equivilent
* Wrote backend API endpoints entirely in Sprint Boot and Java, for both Authentication, Database access, and Calculation purposes
* Utilized Spring Security for authentication and endpoint protection 
* Set up and deployed JWT tokens stored in HTTP cookies for Stateless Spring Boot authentication, allowing data to be saved as well as transfered through secure login/logout
* Integrated Supabase for cloud database storage, allowing users to keep previously typed Grades for future reference 
* Followed Spring Boot best practices for clean, efficient, and secure code 

# Grade Conversion
* To convert, each grade is converted to the corresponding GPA value, then the total is averaged up to the GPA value. This method favours consistency over high marks, being very punishing to getting any grad below an 80%. <br/>

| Lower bound | Upper Bound | GPA conversion |
|:------------|:------------|:---------------|
| 90%   | 100%  | 4.00 |
| 85%   | 89%   | 3.90 |
| 80%   | 84%   | 3.70 |
| 77%   | 79%   | 3.30 |
| 73%   | 76%   | 3.00 |
| 70%   | 72%   | 2.70 |
| 67%   | 69%   | 2.30 |
| 63%   | 66%   | 2.00 |
| 60%   | 62%   | 1.70 |
| 56%   | 59%   | 1.30 |

# How to run 
Requires Java 23.0.2 <br/>
Run the command `mvn clean install` or `./mvnw spring-boot:run` to install all dependancies <br/>
Run the command `mvn spring-boot:run` or `./mvnw spring-boot:run` to deploy to a localhost website <br/>
