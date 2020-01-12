package com.greenfrog.notes;

import com.greenfrog.notes.endpoints.NoteNotFoundException;
import com.greenfrog.notes.manager.NotesManager;
import com.greenfrog.notes.repository.NotesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NoteManagerTest {
    @Mock
    private NotesRepository notesRepository;
    @InjectMocks
    private NotesManager notesManager;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createNote() {
        notesManager.createNote("someText", true);
        verify(notesRepository).save(any(Note.class));
    }

    @Test
    public void getNote() throws NoteNotFoundException {
        String id = UUID.randomUUID().toString();
        Optional<Note> optional = Optional.of(mock(Note.class));
        when(notesRepository.findById(id)).thenReturn(optional);

        notesManager.getNote(id);
        verify(notesRepository).findById(id);
    }
}
