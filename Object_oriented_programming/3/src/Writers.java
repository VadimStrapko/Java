import java.util.List;

public class Writers extends Person {
    private String genre;
    private int birthYear;
    private String nationality;
    private List<String> famousWorks;
    private Integer deathYear;
    private String biography;
    private List<String> awards;

    public Writers(String name, String career, String description, String genre, int birthYear, String nationality, List<String> famousWorks, Integer deathYear, String biography, List<String> awards) {
        super(name, career, description);
        this.genre = genre;
        this.birthYear = birthYear;
        this.nationality = nationality;
        this.famousWorks = famousWorks;
        this.deathYear = deathYear;
        this.biography = biography;
        this.awards = awards;
        System.out.println("Конструктор класса Writers c параметрами.");
    }

    public Writers()
    {
        System.out.println("Конструктор класса Writers без параметров.");
    }

    public Writers(Writers other) {
        super(other.getName(), other.getCareer(), other.getDescription()); // Копируем поля из родительского класса
        this.genre = other.genre;
        this.birthYear = other.birthYear;
        this.nationality = other.nationality;
        this.famousWorks = List.copyOf(other.famousWorks);
        this.deathYear = other.deathYear;
        this.biography = other.biography;
        this.awards = List.copyOf(other.awards);
        System.out.println("Конструктор класса Writers копирования.");
    }

    public String getGenre() {
        return genre;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getNationality() {
        return nationality;
    }

    public List<String> getFamousWorks() {
        return famousWorks;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public String getBiography() {
        return biography;
    }

    public List<String> getAwards() {
        return awards;
    }

    public static void showWriterInfo(Writers writer) {
        System.out.println("Имя: " + writer.getName());
        System.out.println("Род деятельности: " + writer.getCareer());
        System.out.println("Описание: " + writer.getBiography());
        System.out.println("Жанр: " + writer.getGenre());
        System.out.println("Год рождения: " + writer.getBirthYear());
        System.out.println("Национальность: " + writer.getNationality());
        System.out.println("Известные работы: " + String.join(", ", writer.getFamousWorks()));
        System.out.println("Год смерти: " + (writer.getDeathYear() != null ? writer.getDeathYear() : "Жив"));
        System.out.println("Награды: " + String.join(", ", writer.getAwards()));
        System.out.println("-------------------------------------------------");
    }
}
