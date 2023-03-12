package org.sarath.repository;

import org.sarath.models.Birds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirdRepository extends JpaRepository<Birds, Long> {
    List<Birds> findByName(String Name);
}
