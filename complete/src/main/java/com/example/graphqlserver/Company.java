package com.example.graphqlserver;

import java.util.Arrays;
import java.util.List;

public record Company (int id, String name) {

    private static List<Company> companies = Arrays.asList(
            new Company(101, "Jay Holdings"),
            new Company(102, "TYS Investments"),
            new Company(103, "Yang & Sons")
    );

    public static Company getById(int id) {
        System.out.println("Company.getById: " + id);
        return companies.stream()
                .filter(company -> company.id() == id)
                .findFirst()
                .orElse(null);
    }
}
