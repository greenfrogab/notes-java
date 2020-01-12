package com.greenfrog.notes.endpoints;

import com.greenfrog.notes.Note;
import com.greenfrog.notes.manager.NotesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/notes")
public class NotesEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesEndpoint.class);

    @Autowired
    private NotesManager notesManager;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Note> createNote(@RequestBody Note dto) throws InvalidRequestException {
        if (dto.getText() == null || dto.getText().length() == 0) {
            throw new InvalidRequestException("Note text can't be null or empty");
        }
        Note note = notesManager.createNote(dto.getText(), dto.isStatus());
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Note> getNote(@PathVariable("id") String id) throws NoteNotFoundException {
        Note note = notesManager.getNote(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity updateNote(@PathVariable("id") String id, @RequestBody Note dto) throws InvalidRequestException {
        throw new InvalidRequestException("Not yet implemented!");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNote(@PathVariable("id") String id) throws NoteNotFoundException {
        notesManager.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Note>> getNotes() {
        List<Note> notes = notesManager.getNotes();
        return new ResponseEntity(notes, HttpStatus.OK);
    }


}
