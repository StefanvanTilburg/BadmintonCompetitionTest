package nl.badminton.competition.badminton.competition.service;

import java.util.List;
import java.util.Optional;

public interface ServiceFunctions<A, B> {
    List<B> getAll();
    Optional<A> findById(long id);
    A saveEntity(A input);
}
