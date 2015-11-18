package com.udacity.gradle.jokes;

public class Joker {
    private static int nextJokeIndex =0;
    private static String jokes[] ={
            "Four out of three parents are poor at math.",
            "I am not a complete idiot, there are some parts still missing.",
            "If you do not get things right the first time, skydiving is not for you.",
            "Since light travels faster than sound, people appear bright until you hear them speak."
    };
    public String getJoke(){
        int jId = nextJokeIndex++;
        nextJokeIndex = nextJokeIndex % jokes.length;
        return jokes[jId];

    }
}
