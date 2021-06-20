package ru.shcherban.springapplication.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shcherban.springapplication.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }


    public Person show(int id_of_required_person) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id_of_required_person},
                new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }


    public void save(Person personToBeSavedInDB) {
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)",
                personToBeSavedInDB.getName(), personToBeSavedInDB.getAge(), personToBeSavedInDB.getEmail());
    }


    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name = ?, age = ?, email = ? WHERE id = ?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }


    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);

    }
}