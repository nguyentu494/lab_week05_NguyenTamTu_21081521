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
//    CommandLineRunner initData(){
//        return args -> {
//            Faker faker = new Faker();
//            for(int i = 0; i < 20; i++){
//                Account account = Account.builder()
//                        .username(faker.internet().username())
//                        .password(faker.internet().password())
//                        .role(i%2==0?Role.CANDIDATE:Role.COMPANY)
//                        .build();
//                accountRepository.save(account);
//            }
//        };
//    }
}
