package com.library.member_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository repository;

    @PostMapping
    public Member addMember(@RequestParam String name) {
        return repository.save(new Member(name));
    }

    @GetMapping("/{id}")
    public Optional<Member> getMember(@PathVariable int id) {
        return repository.findById(id);
    }

    @GetMapping("/search")
    public Optional<Member> getMemberByName(@RequestParam String name) {
        return repository.findByName(name);
    }
}
