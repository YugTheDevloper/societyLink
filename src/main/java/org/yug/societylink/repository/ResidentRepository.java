package org.yug.societylink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yug.societylink.model.Resident;

import java.util.Optional;

@Repository
public interface ResidentRepository extends JpaRepository<Resident,Long> {

    Optional<Resident> findByEmail(String email);

}
