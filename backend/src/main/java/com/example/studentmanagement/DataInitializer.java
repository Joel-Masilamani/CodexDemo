// package com.example.studentmanagement;

// import com.example.studentmanagement.entity.Course;
// import com.example.studentmanagement.entity.Department;
// import com.example.studentmanagement.entity.Enrollment;
// import com.example.studentmanagement.entity.Instructor;
// import com.example.studentmanagement.entity.Student;
// import com.example.studentmanagement.repository.CourseRepository;
// import com.example.studentmanagement.repository.DepartmentRepository;
// import com.example.studentmanagement.repository.EnrollmentRepository;
// import com.example.studentmanagement.repository.InstructorRepository;
// import com.example.studentmanagement.repository.StudentRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// /**
//  * DataInitializer populates the application with sample data when Spring Boot starts.
//  * This is useful for learning and testing the relationships between entities.
//  */
// @Component
// public class DataInitializer implements CommandLineRunner {

//     private final DepartmentRepository departmentRepository;
//     private final InstructorRepository instructorRepository;
//     private final CourseRepository courseRepository;
//     private final StudentRepository studentRepository;
//     private final EnrollmentRepository enrollmentRepository;

//     public DataInitializer(DepartmentRepository departmentRepository,
//                            InstructorRepository instructorRepository,
//                            CourseRepository courseRepository,
//                            StudentRepository studentRepository,
//                            EnrollmentRepository enrollmentRepository) {
//         this.departmentRepository = departmentRepository;
//         this.instructorRepository = instructorRepository;
//         this.courseRepository = courseRepository;
//         this.studentRepository = studentRepository;
//         this.enrollmentRepository = enrollmentRepository;
//     }

//     @Override
//     public void run(String... args) {
//         // Create departments
//         Department cs = departmentRepository.save(new Department("Computer Science"));
//         Department business = departmentRepository.save(new Department("Business"));

//         // Create instructors and assign them to departments
//         Instructor alice = instructorRepository.save(new Instructor("Alice", "Johnson", cs));
//         Instructor bob = instructorRepository.save(new Instructor("Bob", "Stewart", business));

//         // Create courses in departments with assigned instructors
//         Course algorithms = courseRepository.save(new Course("Algorithms", "CS101", cs, alice));
//         Course dataStructures = courseRepository.save(new Course("Data Structures", "CS102", cs, alice));
//         Course marketing = courseRepository.save(new Course("Marketing Fundamentals", "BUS101", business, bob));

//         // Create students
//         Student jane = studentRepository.save(new Student("Jane", "Doe", "jane.doe@example.com"));
//         Student john = studentRepository.save(new Student("John", "Smith", "john.smith@example.com"));
//         Student emma = studentRepository.save(new Student("Emma", "Lopez", "emma.lopez@example.com"));

//         // Create enrollments connecting students to courses
//         enrollmentRepository.save(new Enrollment(jane, algorithms, "Fall 2026", "A"));
//         enrollmentRepository.save(new Enrollment(jane, marketing, "Fall 2026", "B+"));
//         enrollmentRepository.save(new Enrollment(john, dataStructures, "Fall 2026", "A-"));
//         enrollmentRepository.save(new Enrollment(emma, algorithms, "Spring 2027", "B"));
//     }
// }
