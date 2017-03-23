package hcmue.gst.off.services;

import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.RoleRepository;
import hcmue.gst.off.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import org.thymeleaf.util.StringUtils;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Service
public class UserServiceImpl extends BaseCommand implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Result<User> save(User user) {
        // valid username existinc
        if (user.getId() == null) {
            if (userRepository.findByUsername(user.getUsername()) != null) {
                return Fail("Username already exists");
            }
        }
        if(StringUtils.isEmptyOrWhitespace(user.getUsername()) || user.getUsername() == null){

            return Fail("Please enter Username");
        }
        if(StringUtils.isEmptyOrWhitespace(user.getPassword()) || user.getPassword() == null){
            return Fail("please enter Password");
        }

        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return Success(userRepository.save(user));

    }

    @Override
    public Result<Iterable<User>> findAll() {
        return Success(userRepository.findAll());
    }

    @Override
    public Result<User> findOne(Long id) {
        return Success(userRepository.findOne(id));
    }

    @Override
    public Result delete(Long id) {
        userRepository.delete(id);
        return Success();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public PageableResult<User> search(User model, Pageable p) {
        return Success(userRepository.search(model, new PageRequest(p.getPageNumber(),PAGESIZE,p.getSort())));
    }

    @Override
    public Result<Iterable<User>> search(User model) {
        return Success(userRepository.search(model));
    }
}
