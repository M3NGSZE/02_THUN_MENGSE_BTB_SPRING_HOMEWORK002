# Database Schema

## **Create Tables**
```sql
-- Create students table
CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    student_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL
);

-- Create instructors table
CREATE TABLE instructors (
    instructor_id SERIAL PRIMARY KEY,
    instructor_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL
);

-- Create courses table
CREATE TABLE courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(50) NOT NULL,
    description VARCHAR(250),
    instructor_id INT,
    FOREIGN KEY (instructor_id) REFERENCES instructors(instructor_id) 
    ON UPDATE CASCADE ON DELETE CASCADE
);

-- Create student_course table
CREATE TABLE student_course (
    id SERIAL PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id) 
    ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) 
    ON UPDATE CASCADE ON DELETE CASCADE
);
