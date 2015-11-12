
package edu.wctc.asp.bookwebapp.repository;

import edu.wctc.asp.bookwebapp.entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author alex
 */
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {
    
    @Query("SELECT a FROM Author a JOIN FETCH a.bookCollection WHERE a.authorID = (:id)")
    public abstract Author findAuthorAndBookCollection(@Param("id") Integer id);
}