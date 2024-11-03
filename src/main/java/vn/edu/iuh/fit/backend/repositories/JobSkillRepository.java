package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.JobSkillId;

public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
}