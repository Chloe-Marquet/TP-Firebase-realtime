package com.example.tpfirebaserealtime;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Personne {
    private String identifier;
    private String name;

    public Personne(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public Personne() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
