package com.greenfrog.notes.repository;

import com.greenfrog.notes.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NotesRepository extends MongoRepository<Note, String> {
}
