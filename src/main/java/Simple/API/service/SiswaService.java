package Simple.API.service;

import Simple.API.entity.SiswaEntity;
import Simple.API.repo.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiswaService {

    private final SiswaRepository studentRepository;

    @Autowired
    public SiswaService(SiswaRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<SiswaEntity> getStudents(){
        return studentRepository.findAll();
    }



    //Add Siswa Baru -->
    public void addNewStudent(SiswaEntity student) {
        Optional<SiswaEntity> studentOptional = studentRepository
                .findStudentByNisn(student.getNisn());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Nisn Sudah Terdaftar");
        }
        studentRepository.save(student);
    }

    //Find Siswa Id -->
    public Optional<SiswaEntity> getStudentById(Long id) {
        return studentRepository.findById(id);
    }



    //Update Siswa -->
    public void updateStudent(Long id, SiswaEntity newDataStudent) {
        SiswaEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(" ID Siswa  " + id + " tidak ada"));

        if (newDataStudent.getNama() != null &&
                !newDataStudent.getNama().isEmpty()) {
            student.setNama(newDataStudent.getNama());
        }

        if (newDataStudent.getNisn() != null &&
                !newDataStudent.getNisn().equals(student.getNisn())) {
            Optional<SiswaEntity> studentOptional = studentRepository.findStudentByNisn(newDataStudent.getNisn());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Nisn Sudah Terdaftar");
            }
            student.setNisn(newDataStudent.getNisn());
        }

        studentRepository.save(student);
    }


    //Delete Siswa -- >
    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("ID Siswa " + id + " tidak ada");
        }
        studentRepository.deleteById(id);
    }


}
