package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.Job;

import java.util.List;


public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findJobByCompanyId(long id);
    Page<Job> findJobByCompanyId(Pageable pageable, long id);
    Page<Job> findJobByCompany_Email(Pageable pageable, String email);
    Page<Job> findAll(Pageable pageable);

}