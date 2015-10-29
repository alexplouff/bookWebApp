
package edu.wctc.asp.bookwebapp.repository;

import edu.wctc.asp.bookwebapp.entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author alex
 */
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {
    
}
