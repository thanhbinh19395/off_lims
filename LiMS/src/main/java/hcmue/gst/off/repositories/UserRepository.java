package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    //address, birthday(date), idcard, name, phone, roleId, status, username -- lấy theo tên của class, ko phải tên của db nha, vl lắm
    String searchQuery = "SELECT m FROM User m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.address LIKE %:#{#model.address}% OR ISNULL(:#{#model.address}) = true) "
            + "AND ( m.birthday = :#{#model.birthday} OR ISNULL(:#{#model.birthday}) = true) "
            + "AND ( m.idcard LIKE %:#{#model.idcard}% OR ISNULL(:#{#model.idcard}) = true) "
            + "AND ( m.name LIKE %:#{#model.name}% OR ISNULL(:#{#model.name}) = true) "
            + "AND ( m.phone LIKE %:#{#model.phone}% OR ISNULL(:#{#model.phone}) = true) "
            + "AND ( m.roleId = :#{#model.roleId} OR ISNULL(:#{#model.roleId}) = true) "
            + "AND ( m.status = :#{#model.status} OR ISNULL(:#{#model.status}) = true) "
            + "AND ( m.username LIKE %:#{#model.username}% OR ISNULL(:#{#model.username}) = true)"
            ;

    User findByUsername(String username);

    @Query(searchQuery)
    Page<User> search(@Param("model") User model, Pageable page);
    @Query(searchQuery)
    Iterable<User> search(@Param("model") User model);
}
