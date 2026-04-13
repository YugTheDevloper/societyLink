package org.yug.societylink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.yug.societylink.model.Complaint;

import java.util.List;


public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

   List<Complaint> findByResidentEmail(String Email);

}
