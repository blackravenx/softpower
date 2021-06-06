package softpower.task.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Чинасил Максат
 * 04/июнь/2021
 */

//говорим что это будет сущностью и надо добавить ее в БД
@Entity
//Геттеры и сеттеры, так красивее и без boilerplate кода
@Getter
@Setter
@NoArgsConstructor
public class MyFile {
    //уникальный идентификатор для файла с автоинкрементом
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //   Название файла, формат, папка, дата загрузки, и размер
    @NotNull
    private String fileName;
    private String fileFormat;
    private String upload_directory;
    @JsonFormat(timezone = "GMT+06:00")
    @CreationTimestamp
    private Date uploadDate;
    private Long fileSize;
}