package com.library.system.repositories;

import com.library.system.Entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Authors, String> {
    @Query(value = "select * from authors as a where a.readings_id = (:id)",nativeQuery = true)
    List<Authors> findAllById(@Param("id")String id);

    @Query(value = "DELETE FROM authors WHERE readings_id = (:id)",nativeQuery = true)
    void deleteAllById(@Param("id") String id);
}
