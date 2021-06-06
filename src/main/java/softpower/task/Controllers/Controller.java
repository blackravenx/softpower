package softpower.task.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import softpower.task.Models.MyFile;
import softpower.task.Services.FileService;

import java.io.IOException;

/**
 * @author Чинасил Максат
 * 04/июнь/2021
 */
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*",allowCredentials = "true")
@RestController
@RequestMapping("api/files")
public class Controller {
    private final FileService fileService;

    public Controller(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public Iterable<MyFile> getAllFiles() {
        return fileService.getAllFiles();
    }

    @PostMapping(value = "save",consumes = "multipart/form-data")
    public ResponseEntity<String> saveFiles(@RequestParam("files") MultipartFile[] multipartFiles) throws IOException {
        if(fileService.save(multipartFiles)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Успешная загрузка!");
        }
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Не удалось завершить загрузку. Возможно один из Ваших файлов пуст.");
    }

}


