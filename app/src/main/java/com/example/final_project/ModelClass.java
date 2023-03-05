
    package com.example.final_project;

    public class ModelClass {
        int GAME_ID,GAME_SCORE;
        String GAME_FULL_NAME,GAME_TIME;

        public ModelClass( int GAME_SCORE, String GAME_FULL_NAME, String GAME_TIME) {
            this.GAME_SCORE = GAME_SCORE;
            this.GAME_FULL_NAME = GAME_FULL_NAME;
            this.GAME_TIME = GAME_TIME;
        }

        public int getGAME_ID() {
            return GAME_ID;
        }

        public void setGAME_ID(int GAME_ID) {
            this.GAME_ID = GAME_ID;
        }

        public ModelClass(int id, String fullName, String dt, int score) {
            this.GAME_ID=id;
            this.GAME_SCORE = score;
            this.GAME_FULL_NAME = fullName;
            this.GAME_TIME = dt;
        }

        public int getGAME_SCORE() {
            return GAME_SCORE;
        }

        public void setGAME_SCORE(int GAME_SCORE) {
            this.GAME_SCORE = GAME_SCORE;
        }

        public String getGAME_FULL_NAME() {
            return GAME_FULL_NAME;
        }

        public void setGAME_FULL_NAME(String GAME_FULL_NAME) {
            this.GAME_FULL_NAME = GAME_FULL_NAME;
        }

        public String getGAME_TIME() {
            return GAME_TIME;
        }

        public void setGAME_TIME(String GAME_TIME) {
            this.GAME_TIME = GAME_TIME;
        }
    }

