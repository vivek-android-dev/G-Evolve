package com.saveetha.g_evolve.responses;

import java.util.ArrayList;

public class ShowAllMessageResponse {

    String status,message;
    ArrayList<Queries> queries;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Queries> getQueries() {
        return queries;
    }

    private class Queries {
        String query_id,user_id,user_name,user_email,message,date_query;

        public String getQuery_id() {
            return query_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public String getMessage() {
            return message;
        }

        public String getDate_query() {
            return date_query;
        }
    }
}
