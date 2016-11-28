import LinkedList.SortedDoublyLinkedList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A demo of SortedDoublyLinkedList using Person and Student objects
 *
 * @author David Luo
 */
public class Demo {


    public static void main(String[] args) {

        Student benson = new Student("Benson", "Chau", 1, convertToDate(3, 13, 1978), "UGA");
        Student frank = new Student("Frank", "Smith", 2, convertToDate(1, 1, 2842), "Harvard");
        Student hawking = new Student("Stephen", "Hawking", 3, convertToDate(3, 2, 2981), "MIT");

        Person grandpa = new Person("Kyle", "Grandpa", 238, convertToDate(1, 1, 1997));
        Person rick = new Person("Rick", "Astley", 3841, convertToDate(2, 6, 1966));

        {
            // studentList is a prefix of personList when sorted by ID
            SortedDoublyLinkedList<Person> peopleList = new SortedDoublyLinkedList<>();
            SortedDoublyLinkedList<Student> studentList = new SortedDoublyLinkedList<>();


            // Regular unsorted arrays for convenience.
            Person[] people = {
                    grandpa, rick, benson, frank, hawking
            };
            Student[] students = {
                    benson, hawking, frank
            };

            System.out.println("Unordered People");
            for (Person p : people) {
                System.out.println(p.toString());
                if (!peopleList.add(p))
                    System.out.println("Failed to add " + p.toString());
            }
            System.out.println("Unordered Students");
            for (Student s : students) {
                System.out.println(s.toString());
                if (!studentList.add(s))
                    System.out.println("Failed to add " + s.toString());
            }

            System.out.println("People");
            peopleList.printList();
            System.out.println("Students");
            studentList.printList();

            System.out.println("Students prefix of People?");
            System.out.println(studentList.isPrefix(peopleList));
        }
        {
            SortedDoublyLinkedList<Student> onlyStudents = new SortedDoublyLinkedList<>();
            SortedDoublyLinkedList<Person> onlyPeople = new SortedDoublyLinkedList<>();

            onlyStudents.add(benson);
            onlyStudents.add(frank);
            onlyStudents.add(hawking);

            onlyPeople.add(grandpa);
            onlyPeople.add(rick);

            System.out.println("Just People");
            onlyPeople.printList();
            System.out.println("Just Students");
            onlyStudents.printList();

            SortedDoublyLinkedList<Person> merged = onlyPeople.merge(onlyStudents);

            System.out.println("Merged");
            merged.printList();
        }
    }

    // This is probably ugly.
    private static Date convertToDate(int month, int day, int year) {
        String date = String.format("%02d-%02d-%04d", month, day, year);

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
