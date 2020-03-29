package ru.itis.context.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.context.models.User;
import ru.itis.context.repo.UsersRepo;

import java.util.Optional;

@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userCandidate = usersRepo.findByUserName(username);
        if (userCandidate.isPresent()) {
            return new UserDetailsImpl(userCandidate.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
