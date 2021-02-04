package nl.badminton.competition.badminton.competition.service;

import java.sql.SQLDataException;
import java.util.List;
import java.util.Optional;

public interface ServiceFunctions<A, B> {
    List<B> getAll();
    Optional<B> findById(long id);
    Optional<B> findByName(String name);
    B saveEntity(B input) throws SQLDataException;
    B updateEntity(B input) throws SQLDataException;
    void deleteEntity(B input) throws SQLDataException;
}
