package com.example.graphqlserver;

import java.util.Arrays;
import java.util.List;

public record Project(int id, String name) {

    private static List<Project> projects = Arrays.asList(
            new Project(201, "Deal Tracker"),
            new Project(202, "Tennis Companion"),
            new Project(203, "Match Tracker")
    );

    public static Project getById(int id) {
        System.out.println("Project.getById: " + id);
        return projects.stream()
                .filter(company -> company.id() == id)
                .findFirst()
                .orElse(null);
    }
}
