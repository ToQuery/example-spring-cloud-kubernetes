package io.github.toquery.k8s.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {

    private int id;

    private String name;

    private String phone;

    public AccountEntity(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
}
