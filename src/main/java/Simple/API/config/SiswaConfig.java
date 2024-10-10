package Simple.API.config;

import Simple.API.entity.SiswaEntity;
import Simple.API.repo.SiswaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SiswaConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            SiswaRepository repository){
        return args -> {

            //input data siswa
            SiswaEntity eza = new SiswaEntity(
                    31234567,
                    "Eza",
                    "Blok D4, No.36",
                    "XII RPL 2"
            );

            //Menyimpan Data Siswa
            repository.saveAll(
                    List.of(eza)
            );
        };
    }
}
