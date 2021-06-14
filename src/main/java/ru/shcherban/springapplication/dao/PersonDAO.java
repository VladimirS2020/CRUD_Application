package ru.shcherban.springapplication.dao;

import org.springframework.stereotype.Component;
import ru.shcherban.springapplication.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom@gmail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 52, "bob@gmail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 18, "mike@yahoo.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 34, "katy@gmail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id_of_required_person) {
        return people.stream().filter(person -> person.getId() == id_of_required_person).findAny().orElse(null);
    }

    public void save(Person personToBeSavedInDB) {
        personToBeSavedInDB.setId(++PEOPLE_COUNT);
        people.add(personToBeSavedInDB);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}

