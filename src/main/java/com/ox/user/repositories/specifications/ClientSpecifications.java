package com.ox.user.repositories.specifications;


import com.ox.user.entities.Client;
import org.springframework.data.jpa.domain.Specification;

public class ClientSpecifications {

    public static Specification<Client> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("companyName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Client> hasAddress(String address) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%");
    }
}