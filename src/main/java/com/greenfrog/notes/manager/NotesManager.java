package com.greenfrog.notes.manager;

import com.greenfrog.notes.Note;
import com.greenfrog.notes.endpoints.NoteNotFoundException;
import com.greenfrog.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NotesManager {
    private NotesRepository repository;

    @Autowired
    public NotesManager(NotesRepository repository) {
        this.repository = repository;
    }

    public Note createNote(String text, boolean status) {
        Note note = new Note(text, status);
        return repository.save(note);
    }

    public Note getNote(String id) throws NoteNotFoundException {
        Optional<Note> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new NoteNotFoundException("No note found by id: " + id);
        }
        return optional.get();
    }

    public Note updateNote(Note note) {
        throw new IllegalStateException("Not yet implemented!");
    }

    public List<Note> getNotes() {
        return repository.findAll();
    }

    public void delete(String id) throws NoteNotFoundException {
        repository.delete(getNote(id));
    }
}
