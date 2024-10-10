package Simple.API.controller;

import Simple.API.entity.SiswaEntity;
import Simple.API.service.SiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/simpleAPI")
public class SiswaController {

    private final SiswaService studentService;

    @Autowired
    public SiswaController(SiswaService studentService) {
        this.studentService = studentService;
    }


    //Controller Get -->
    @GetMapping
    public List<SiswaEntity> getStudents() {
        return studentService.getStudents();
    }


    //Controller Get 2 --> Untuk Memanggil Data Siswa Dengan ID
    @GetMapping("/{id}")
    public SiswaEntity getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalStateException("Siswa ID " + id + " Tidak Ada"));
    }


    //Controller Register --> Untuk Mendaftarkan Siswa
    @PostMapping
    public void registerNewStudent(@RequestBody SiswaEntity student) {
        studentService.addNewStudent(student);
    }


    //Controller Update --> Untuk Update Data Siswa
    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody SiswaEntity newDataStudent) {
        studentService.updateStudent(id, newDataStudent);
    }


    //Controller Delete --> Untuk Menghapus Data Siswa
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
