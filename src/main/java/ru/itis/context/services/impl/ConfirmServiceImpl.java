package ru.itis.context.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.context.models.Link;
import ru.itis.context.models.User;
import ru.itis.context.models.enums.Status;
import ru.itis.context.repo.LinkRepo;
import ru.itis.context.repo.UsersRepo;
import ru.itis.context.services.ConfirmService;

import java.util.Optional;

@Component
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private LinkRepo linkRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public void setConfirm(String key) {
        Optional<Link> linkCandidate = linkRepo.findByKey(key);
        if (linkCandidate.isPresent()) {
            Link link = linkCandidate.get();
            Optional<User> userCandidate = usersRepo.find(link.getUser());
            if (userCandidate.isPresent()) {
                User user = userCandidate.get();
                user.setStatus(Status.CONFIRMED);
                usersRepo.updateStatus(user);
                linkRepo.delete(link.getId());
            } else {
                throw new IllegalStateException("User didn't find");
            }
        } else {
            throw new IllegalStateException("Link didn't exist");
        }
    }
}
