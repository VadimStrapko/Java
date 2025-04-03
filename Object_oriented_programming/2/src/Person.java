class Person {
    private String name;
    private String career;
    private String description;

    public Person()
    {
        System.out.println("Конструтор класса Person без параметров.");
    }

    public Person(Person other)
    {
        this.name = other.name;
        this.career = other.career;
        this.description = other.description;
        System.out.println("Конструктор класса Person копирования.");
    }

    public Person(String name, String career, String description) {
        this.name = name;
        this.career = career;
        this.description = description;
        System.out.println("Конструктор класса Person с параметрами.");

    }

    public void showIhfo() {
        System.out.println("Имя личности: " + name + ", Род деятельности: " + career + ", Описание: " + description);
    }

    public String getName() {
        return name;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public void changeName(String newName,String confirmName)
    {
        if(newName == confirmName)
            name = newName;
        else System.out.println("Имена не совпадают.");


    }

    public void changeCareer(String newCareer) {
        this.career = newCareer;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }
}