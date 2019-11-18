package com.library.system.repositories;

import com.library.system.Entity.Newspaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewspaperRepository extends JpaRepository<Newspaper, String> {
}
