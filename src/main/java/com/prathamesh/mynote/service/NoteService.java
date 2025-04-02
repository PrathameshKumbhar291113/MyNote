package com.prathamesh.mynote.service;

import com.prathamesh.mynote.model.Note;
import com.prathamesh.mynote.model.User;
import com.prathamesh.mynote.repository.NoteRepository;
import com.prathamesh.mynote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Note
    public Note createNote(Long userId, String title, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = new Note(title, content, user);
        return noteRepository.save(note);
    }

    // Get All Notes by User ID
    public List<Note> getUserNotes(Long userId) {
        return noteRepository.findByUserId(userId);
    }

    // Get Note by Note ID
    public Note getNoteById(Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    // Update Note
    public Note updateNote(Long noteId, String newTitle, String newContent) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setTitle(newTitle);
        note.setContent(newContent);

        return noteRepository.save(note);
    }

    // Delete Note
    public void deleteNote(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        noteRepository.delete(note);
    }
}
