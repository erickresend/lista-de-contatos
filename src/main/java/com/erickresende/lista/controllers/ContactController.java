package com.erickresende.lista.controllers;

import com.erickresende.lista.models.Contact;
import com.erickresende.lista.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id){
        Contact obj = this.contactService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contact>> findAll(){
        List<Contact> contacts = this.contactService.findAll();
        return ResponseEntity.ok().body(contacts);
    }

    @PostMapping
    public ResponseEntity<Contact> create(@RequestBody Contact obj){
        obj.setId(null);
        this.contactService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@RequestBody Contact obj, @PathVariable Long id){
        obj.setId(id);
        this.contactService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http://localhost:8080/contact")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.contactService.delete(id);
        return ResponseEntity.ok().build();
    }
}
