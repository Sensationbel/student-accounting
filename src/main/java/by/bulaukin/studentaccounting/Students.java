package by.bulaukin.studentaccounting;

public class Students {

    private int id;
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
