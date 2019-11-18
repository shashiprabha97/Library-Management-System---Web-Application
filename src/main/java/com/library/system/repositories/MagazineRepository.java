package com.library.system.repositories;

import com.library.system.Entity.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineRepository extends JpaRepository<Magazine,String> {
}
