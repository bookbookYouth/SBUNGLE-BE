package com.sbungle.sbunglebe.contact.service;

import com.sbungle.sbunglebe.contact.domain.UserContact;
import com.sbungle.sbunglebe.contact.dto.response.ContactResponse;
import com.sbungle.sbunglebe.contact.repository.UserContactRepository;
import com.sbungle.sbunglebe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final UserService userService;
    private final UserContactRepository userContactRepository;

    @Transactional
    public ContactResponse createContact(String userId, String content) {
        userService.findUserByIdOrThrow(userId);

        UserContact toSave = UserContact.of(userId, content);
        userContactRepository.save(toSave);
        return ContactResponse.from(toSave);
    }

    @Transactional(readOnly = true)
    public List<ContactResponse> getContactsByUserId(String userId) {
        return userContactRepository.findAllByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(ContactResponse::from)
                .toList();
    }
}
