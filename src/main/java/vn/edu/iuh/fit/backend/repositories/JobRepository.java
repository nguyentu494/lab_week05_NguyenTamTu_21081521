package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.backend.dto.JobSuggestionDTO;
import vn.edu.iuh.fit.backend.models.Job;

import java.util.List;


public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findJobByCompanyId(long id);
    Page<Job> findJobByCompanyId(Pageable pageable, long id);
    Page<Job> findJobByCompany_Email(Pageable pageable, String email);
    Page<Job> findAll(Pageable pageable);
    @Query("""
        SELECT new vn.edu.iuh.fit.backend.dto.JobSuggestionDTO(
            j.jobName, j.jobDesc, j.company.compName, COUNT(js.skill.id)
        )
        FROM Job j
        JOIN j.jobSkills js
        JOIN CandidateSkill cs ON js.skill.id = cs.skill.id
        WHERE cs.id.canId = :candidateId
        GROUP BY j.jobName, j.jobDesc, j.company.compName
        HAVING COUNT(js.skill.id) > 0
        ORDER BY COUNT(js.skill.id) DESC
    """)
    Page<JobSuggestionDTO> findJobsByCandidateId(Pageable pageable, long candidateId);
}