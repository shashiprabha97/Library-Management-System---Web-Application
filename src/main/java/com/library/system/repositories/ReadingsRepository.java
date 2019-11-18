package com.library.system.repositories;

import com.library.system.Entity.Readings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ReadingsRepository extends JpaRepository<Readings, String> {

    @Query(value = "select * from readings as r where r.state = (:state) and r.id = (:id) limit 1",nativeQuery = true)
    Optional<Readings> findById(@Param("state") int state ,@Param("id") String id);

    @Query(value = "select * from readings as r where r.id = (:id) limit 1",nativeQuery = true)
    Optional<Readings> findById(@Param("id") String id);

    @Query(value = "SELECT * FROM readings WHERE category LIKE %:data% AND type = (:type)AND state = (:state)",nativeQuery = true)
    List<Readings> findByCategory(@Param("data")String data,@Param("type")String type,@Param("state")int state);

    @Query(value = "SELECT * FROM readings WHERE title LIKE %:data% AND type = (:type)AND state = (:state)",nativeQuery = true)
    List<Readings> findByTitle(@Param("data")String data,@Param("type")String type,@Param("state")int state);

    @Query(value = "SELECT * FROM readings WHERE id LIKE %:data% AND type = (:type)AND state = (:state)",nativeQuery = true)
    List<Readings> findByYear(@Param("data")String data,@Param("type")String type,@Param("state")int state);

    @Query(value = "SELECT * FROM readings WHERE language LIKE %:data% AND type = (:type)AND state = (:state)",nativeQuery = true)
    List<Readings> findByLanguage(@Param("data")String data,@Param("type")String type,@Param("state")int state);

}
