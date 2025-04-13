# University of Waterloo CGPA Calculator
This Spring Boot application is designed to calculate the Cumulative Grade Point Average (CGPA) for students at the University of Waterloo. Since Waterloo has a percentage grading system, different from US schools, we have a conversion from percentages to GPA.
This web app does just that. It allows you to enter an optional course name, the grade in percentage, and calculaates the CGPA based off of that and the following table. To calculate, all grades are converted into GPA and then averaged. 
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

# API
Created a backend API in Springboot that takes the percentages, runs the calculations, and returns the Cumulative GPA. <br/>
The API endpoint is at `http://localhost:8080/cgpa/calculate`. 

# How to run 
Requires Java 23.0.2 <br/>
Run the command `mvn clean install` to install all dependancies <br/>
Run the command `mvn spring-boot:run` or `./mvnw spring-boot:run` to deploy to a localhost website <br/>
