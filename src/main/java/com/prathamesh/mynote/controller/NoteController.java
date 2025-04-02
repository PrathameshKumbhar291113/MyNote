package com.prathamesh.mynote.controller;

import com.prathamesh.mynote.model.Note;
import com.prathamesh.mynote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Create Note
    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestParam Long userId,
                                           @RequestParam String title,
                                           @RequestParam String content) {
        Note note = noteService.createNote(userId, title, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    // Get All Notes by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Note>> getUserNotes(@PathVariable Long userId) {
        List<Note> notes = noteService.getUserNotes(userId);
        return ResponseEntity.ok(notes);
    }

    // Get Note by Note ID
    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long noteId) {
        Note note = noteService.getNoteById(noteId);
        return ResponseEntity.ok(note);
    }

    // Update Note
    @PutMapping("/update/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable Long noteId,
                                           @RequestParam String title,
                                           @RequestParam String content) {
        Note updatedNote = noteService.updateNote(noteId, title, content);
        return ResponseEntity.ok(updatedNote);
    }

    // Delete Note
    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable Long noteId) {
        noteService.deleteNote(noteId);
        return ResponseEntity.ok("Note deleted successfully");
    }
}

