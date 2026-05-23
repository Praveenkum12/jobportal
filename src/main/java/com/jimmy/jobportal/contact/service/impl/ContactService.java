package com.jimmy.jobportal.contact.service.impl;

import com.jimmy.jobportal.contact.repository.ContactRepository;
import com.jimmy.jobportal.contact.service.IContactService;
import com.jimmy.jobportal.dto.ContactRequestDto;
import com.jimmy.jobportal.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService implements IContactService {

    private final ContactRepository contactRepository;

    @Override
    public boolean createContact(ContactRequestDto contactRequestDto) {
        Contact contact = contactRepository.save(transformDto(contactRequestDto));
        return contact.getId() != null;
    }

    private Contact transformDto(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
        contact.setStatus("NEW");
        return contact;
    }

}
