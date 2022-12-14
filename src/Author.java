public class Author {

    private String lastName;
    private String firstName;
    private String nationality;
    private String yearOfBirth;
    private String yearOfDeath;

    public Author() {
        lastName = "";
        firstName = "";
        nationality = "";
        yearOfBirth = "";
        yearOfDeath = "";
    }

    public Author(String lastName, String name, String nationality, String yearOfBirth, String yearOfDeath) {
        this.lastName = lastName;
        this.firstName = name;
        this.nationality = nationality;
        this.yearOfBirth = yearOfBirth;
        this.yearOfDeath = yearOfDeath;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getYearOfDeath() {
        return yearOfDeath;
    }

    public void setYearOfDeath(String yearOfDeath) {
        this.yearOfDeath = yearOfDeath;
    }

    @Override
    public String toString() {
        return "Author [lastName=" + lastName + ", firstName=" + firstName + ", nationality=" + nationality
                + ", yearOfBirth=" + yearOfBirth + ", yearOfDeath=" + yearOfDeath + "]";
    }
}