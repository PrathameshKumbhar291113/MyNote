package com.prathamesh.mynote.repository;

import com.prathamesh.mynote.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);

    void deleteByUserId(Long userId);

    boolean existsByUserId(Long userId);
}