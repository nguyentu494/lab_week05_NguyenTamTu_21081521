package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.backend.dto.JobSuggestionDTO;
import vn.edu.iuh.fit.backend.dto.MissSkillDTO;
import vn.edu.iuh.fit.backend.models.Job;

import java.util.List;


public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findJobByCompanyId(long id);
    Page<Job> findJobByCompanyId(Pageable pageable, long id);
    Page<Job> findJobByCompany_Email(Pageable pageable, String email);
    Page<Job> findAll(Pageable pageable);
    @Query("""
        SELECT new vn.edu.iuh.fit.backend.dto.JobSuggestionDTO(
            j.id, j.jobName, j.jobDesc, j.company.compName, COUNT(js.skill.id)
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

    @Query("""
    SELECT DISTINCT new vn.edu.iuh.fit.backend.dto.MissSkillDTO(j.id,  js.skill.skillName
    ) 
    FROM Job j
    JOIN j.jobSkills js
    JOIN j.company c
    WHERE j.id IN (
        SELECT DISTINCT jb.id
        FROM Job jb
        JOIN jb.jobSkills jbs
        JOIN CandidateSkill cs ON jbs.skill.id = cs.skill.id
        WHERE cs.id.canId = :candidateId
    )
    AND NOT EXISTS (
        SELECT 1
        FROM CandidateSkill cs
        WHERE cs.skill.id = js.skill.id
        AND cs.id.canId = :candidateId
    )
""")
    List<MissSkillDTO> findJobSkillsForCandidate(long candidateId);
}