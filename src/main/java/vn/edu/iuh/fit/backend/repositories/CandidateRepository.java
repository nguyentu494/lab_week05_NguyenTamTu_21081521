package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.backend.dto.CandidateMatchDto;
import vn.edu.iuh.fit.backend.models.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findCandidateById(long id);

    Candidate findByEmail(String email);

    @Query("SELECT new vn.edu.iuh.fit.backend.dto.CandidateMatchDto(c.fullName, c.email, js.job.id, count(cs.skill.id)) " +
            "FROM Candidate c " +
            "JOIN c.candidateSkills cs " +
            "JOIN JobSkill js ON cs.skill.id = js.skill.id " +
            "WHERE js.job.id = :jobId " +
            "GROUP BY c.fullName, c.email, js.job.id " +
            "HAVING COUNT(cs.skill.id) > 0 " +
            "ORDER BY COUNT(cs.skill.id) DESC")
    List<CandidateMatchDto> findCandidatesByJobId(long jobId);
    Candidate findByAccount_Username(String username);
}