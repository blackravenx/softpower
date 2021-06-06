package softpower.task.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softpower.task.Models.MyFile;

/**
 * @author Чинасил Максат
 * 04/июнь/2021
 */
@Repository
public interface FileRepository extends JpaRepository<MyFile,Integer> {
}
