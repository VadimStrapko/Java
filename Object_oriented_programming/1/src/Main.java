import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Encyclopedia encyclopedia = new Encyclopedia("Большая Советская Энциклопедия", 1926);

        Person person1 = new Person("Лев Толстой", "Писатель", "Автор таких произведений, как 'Война и мир' и 'Анна Каренина'.");
        Person person2 = new Person("Александр Пушкин", "Поэт", "Основоположник современного русского языка и литературы.");
        Person person3 = new Person("Екатерина II", "Императрица", "Сделала много для развития России в XVIII веке.");
        Person person4 = new Person("Михаил Ломоносов", "Учёный", "Основоположник российской науки и образования.");
        Person person5 = new Person("Петр I", "Царь", "Реформировал Россию и открыл её для Европы.");

        encyclopedia.add(person1);
        encyclopedia.add(person2);
        encyclopedia.add(person3);
        encyclopedia.add(person4);
        encyclopedia.add(person5);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("══════════════════════════════════");
            System.out.println("1. Добавить личность.");
            System.out.println("2. Удалить личность.");
            System.out.println("3. Редактировать личность.");
            System.out.println("4. Просмотр всех личностей.");
            System.out.println("5. Выход.");
            System.out.println("══════════════════════════════════");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Добавление личности: ");
                    System.out.println("Введите имя: ");
                    String name = scanner.nextLine();
                    if (name.isEmpty() || !name.matches("[а-яА-Я]+")) {
                        System.out.println("Некорректный ввод имени.");
                    } else {
                        System.out.println("Введите род деятельности: ");
                        String career = scanner.nextLine();
                        if (career.isEmpty()) {
                            System.out.println("Некорректный ввод рода деятельности.");
                        } else {
                            System.out.println("Введите описание: ");
                            String description = scanner.nextLine();
                            if (description.isEmpty()) {
                                System.out.println("Некорректный ввод описания.");
                            } else {
                                encyclopedia.add(new Person(name, career, description));
                                System.out.println("Личность добавлена.");
                            }
                        }
                    }
                    break;

                case "2":
                    System.out.println("Удаление личности: ");
                    System.out.println("Введите имя, по которому будет проводиться удаление: ");
                    String removeName = scanner.nextLine();
                    if (removeName.isEmpty() || !removeName.matches("[а-яА-Я]+")) {
                        System.out.println("Некорректный ввод.");
                    } else {
                        encyclopedia.remove(removeName);
                        System.out.println("Личность удалена.");
                    }
                    break;

                case "3":
                    System.out.println("Редактирование личности: ");
                    System.out.println("Введите имя, по которому будет проводиться редактирование: ");
                    String reductName = scanner.nextLine();

                    if (reductName.isEmpty()) {
                        System.out.println("Некорректный ввод.");
                    } else {
                        System.out.print("Введите новое имя: ");
                        String redName = scanner.nextLine().trim();

                        if (redName.isEmpty()) {
                            System.out.println("Имя не может быть пустым.");
                        } else {
                            System.out.println("Подтвердите ввод нового имени: ");
                            String confirmName = scanner.nextLine().trim();

                            if (redName.equals(confirmName)) {
                                System.out.print("Введите новый род деятельности: ");
                                String redCareer = scanner.nextLine().trim();

                                if (redCareer.isEmpty()) {
                                    System.out.println("Род деятельности не может быть пустым.");
                                } else {
                                    System.out.print("Введите новое описание: ");
                                    String redDescription = scanner.nextLine().trim();

                                    if (redDescription.isEmpty()) {
                                        System.out.println("Описание не может быть пустым.");
                                    } else {
                                        encyclopedia.reductPerson(reductName, redName, redCareer, redDescription);
                                        System.out.println("Пользователь с именем " + reductName + " изменен.");
                                    }
                                }
                            } else {
                                System.out.println("Имена не совпадают.");
                            }
                        }
                    }
                    break;

                case "4":
                    encyclopedia.showPerson();
                    break;

                case "5":
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Некорректный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }
}
