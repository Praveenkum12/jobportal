package com.jimmy.jobportal.contact.service;

import com.jimmy.jobportal.dto.ContactRequestDto;

public interface IContactService {

    public boolean createContact(ContactRequestDto contactRequestDto);

}
