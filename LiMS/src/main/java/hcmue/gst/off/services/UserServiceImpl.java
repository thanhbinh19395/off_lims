package hcmue.gst.off.services;

import hcmue.gst.off.entities.Role;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.RoleRepository;
import hcmue.gst.off.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Service
public class UserServiceImpl extends BaseCommand implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Result<User> save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
}
