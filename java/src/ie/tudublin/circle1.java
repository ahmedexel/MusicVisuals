package ie.tudublin;

import ddf.minim.*;
import processing.core.PApplet;
import processing.core.PImage;


public class circle1 extends PApplet {


    Minim minim;
    AudioPlayer song;
    PImage imagebackground;

    public void settings(){

        fullScreen();
    }




    public void setup(){


        colorMode(RGB);

        minim = new Minim(this);
        song = minim.loadFile("C:/Users/hogar/OneDrive/Desktop/VisualAssignment/MusicVisuals/images/AC-DC_-_Touch_Too_Much_Official_Video.mp3", 1024);


        song.play();

        smooth();

    }



    public void draw(){
        background(30);
        
        //image(imagebackground, 0, 0);
        noStroke();
        fill(0, 5);
        
        pushMatrix();
        //translate(width/2, height/2);
        //rotate(radians(frameCount % 360 * 2));
        body();

  popMatrix();

    
    
    }
    void body() {
    int r = 40;
    stroke(70);
    //the head
    circle(650, 480, r*2);

    //the body
    line(650, 650, 650, 500 + r/2);

    //the arms
    line(650, 575, 600, 600);
    line(650, 575, 700, 600);
       
    // the hands
    line(600, 600, 550, 550);
    line(700, 600, 750, 550);
    
    // the legs
    line(650, 650, 600, 750);
    line(650, 650, 700, 750);
    }
}