package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import net.datafaker.Faker;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.enums.Role;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.ids.CandidateSkillId;
import vn.edu.iuh.fit.backend.ids.JobSkillId;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.*;

import java.time.LocalDate;
import java.util.Random;
@SpringBootApplication
public class LabWeek05NguyenTamTu21081521Application {
    public static void main(String[] args) {
        SpringApplication.run(LabWeek05NguyenTamTu21081521Application.class, args);
    }
    @Autowired
    private CandidateRepository candidateRepository;
//    @Autowired
//    private AddressRepository addressRepository;
//    @Autowired
//    private CandidateSkillRepository candidateSkillRepository;
    @Autowired
    private CompanyRepository companyRepository;
//    @Autowired
//    private JobRepository jobRepository;
//    @Autowired
//    private SkillRepository skillRepository;
//    @Autowired
//    private JobSkillRepository jobSkillRepository;
    @Autowired
    private AccountRepository accountRepository;

//    @Bean
//    CommandLineRunner initData(SkillRepository skillRepository,
//                               CandidateSkillRepository candidateSkillRepository){
//        return args -> {
//            Faker faker = new Faker();
//            Random random = new Random();
//            for(int i = 1; i < 10; i++){
//                System.out.println(i);
//                Candidate candidate = candidateRepository.findCandidateById((long) i);
//                if(i%3 == 0){
//                    for(int j = 0 ; j < 3; j++){
//                        CandidateSkill ck = new CandidateSkill();
//                        Skill skill = skillRepository.findById((long) random.nextInt(200) + 1);
//                        ck.setCan(candidate);
//                        ck.setSkill(skill);
//                        ck.setId(new CandidateSkillId(candidate.getId(), skill.getId()));
//                        ck.setSkillLevel(SkillLevel.values()[random.nextInt(SkillLevel.values().length)]);
//                        candidateSkillRepository.save(ck);
//                    }
//                }else{
//                    for(int j = 0 ; j < 2; j++){
//                        CandidateSkill ck = new CandidateSkill();
//                        Skill skill = skillRepository.findById((long) random.nextInt(200) + 1);
//                        ck.setCan(candidate);
//                        ck.setSkill(skill);
//                        ck.setId(new CandidateSkillId(candidate.getId(), skill.getId()));
//                        ck.setSkillLevel(SkillLevel.values()[random.nextInt(SkillLevel.values().length)]);
//                        candidateSkillRepository.save(ck);
//                    }
//                }
//
//            }
//        };
//    }
}
