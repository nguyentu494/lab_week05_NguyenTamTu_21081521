package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.enums.Country;
import vn.edu.iuh.fit.backend.models.Address;import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import java.time.LocalDate;
import java.util.Random;
@SpringBootApplication
public class LabWeek05NguyenTamTu21081521Application {
    public static void main(String[] args) {
        SpringApplication.run(LabWeek05NguyenTamTu21081521Application.class, args);
    }
//    @Autowired
//    private CandidateRepository candidateRepository;
//    @Autowired
//    private AddressRepository addressRepository;
//    @Bean
//    CommandLineRunner initData(){
//        return args -> {
//            Random rnd =new Random();
//            for (int i = 1; i < 1000; i++) {
//                Address add = new Address("Quang Trung","HCM", Country.VN,
//                        rnd.nextInt(70000,80000)+"", "464646");
//                addressRepository.save(add);
//                Candidate can=new Candidate(LocalDate.of(1998,
//                        rnd.nextInt(1,13),rnd.nextInt(1,29)),"email_"+i+"@gmail.com","Name #"+i,
//                        rnd.nextLong(1111111111L,9999999999L)+"",
//                        add
//                        );
//                candidateRepository.save(can);
//                System.out.println("Added: " +can);
//            }
//        };
//    }
}
