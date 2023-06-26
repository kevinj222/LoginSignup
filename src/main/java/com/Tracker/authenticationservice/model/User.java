package com.screenspot.authenticationservice.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data

public class User
{

    @Id
    private String username;
    private String name;
    private String email;
    private String userPassword;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;
}