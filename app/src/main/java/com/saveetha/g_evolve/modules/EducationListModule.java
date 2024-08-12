package com.saveetha.g_evolve.modules;

public class EducationListModule {
    private String title, description, image, edu_id;

    public EducationListModule(String title, String description, String image, String edu_id) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.edu_id = edu_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getEdu_id() {
        return edu_id;
    }
}
