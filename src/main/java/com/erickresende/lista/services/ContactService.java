package com.erickresende.lista.services;

import com.erickresende.lista.models.Contact;
import com.erickresende.lista.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public Contact findById(Long id){
        return this.contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Contato não encontrado!"
                ));
    }

    public List<Contact> findAll(){
        return this.contactRepository.findAll();
    }

    public Contact create(Contact obj){
        obj.setId(null);
        return this.contactRepository.save(obj);
    }

    public Contact update(Contact obj){
        Contact newObj = findById(obj.getId());
        newObj.setName(obj.getName());
        newObj.setPhone(obj.getPhone());
        return this.contactRepository.save(newObj);
    }

    public void delete(Long id){
        this.contactRepository.deleteById(id);
    }
}
