package com.myxq.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Setter@Getter
public class Role {

    private Long role_id;
    private String role_name;
    private String role_memo;
    private Set<User> users = new HashSet<>();

}
