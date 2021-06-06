package softpower.task.Services;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softpower.task.Models.MyFile;
import softpower.task.Repositories.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

/**
 * @author Чинасил Максат
 * 04/июнь/2021
 */
@Service
public class FileService {
    //    используем значение из application.properties в нашей переменной
//    так удобнее т.к не надо постоянно прописывать папку, если бы оно использовалось в других местах
    @Value("${upload.path}")
    private String uploadDirectory;
    //    или же можно прописать прямо здесь = "uploads";
    private final FileRepository repository;

    //инжектим репозиторий
    public FileService(FileRepository repository) {
        this.repository = repository;
    }


    public boolean save(MultipartFile[] multipartFiles) throws IOException {
//        Если какой-то из файлов будет пустым, то мы не будем дальше выполнять загрузку
        if (Arrays.stream(multipartFiles).anyMatch(MultipartFile::isEmpty))
            return false;
        else {
            for (MultipartFile file : multipartFiles) {
//создаем объект нашего файла, так как нам нужно будем сеттить филды используя методы класса MultipartFile
                MyFile myFile = new MyFile();
//            заменим все пробелы на "_" если они есть
                String fileName = file.getOriginalFilename().replaceAll("\\s", "_");
//            сеттим филды
                myFile.setFileName(fileName);
                myFile.setFileSize(file.getSize());
                myFile.setUpload_directory(uploadDirectory + '/' + fileName);
                myFile.setFileFormat(file.getContentType());
                Path directory = Paths.get(uploadDirectory);
//           Проверяем, существует ли такая папка, если нет, то создаем ее методом createDirectory
                if (!Files.exists(directory)) {
                    Files.createDirectory(directory);
                }
//            Копируем поток в нашу папку, если такой файл с таким именем существует, то заменяем
                Files.copy(file.getInputStream(),
                        directory.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
//            сохраняем в БД
                repository.save(myFile);

            }
            return true;
        }
    }
// вывести список всех файлов
            public List<MyFile> getAllFiles () {
                return repository.findAll();
            }
        }