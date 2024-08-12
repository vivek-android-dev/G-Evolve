package com.saveetha.g_evolve.responses;

import java.util.ArrayList;

public class AddEducationResponse {
    String status;
    String message;
    ArrayList<education> education;



    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<education> getEducation() {
        return education;
    }

    public class education {
        String title;
        String description;
        String image;
        String edu_id;

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
}
