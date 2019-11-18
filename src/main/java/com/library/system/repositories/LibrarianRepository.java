package com.library.system.repositories;

import com.library.system.Entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian,String> {
}
