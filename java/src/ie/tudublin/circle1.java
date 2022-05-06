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
        //imagebackground = loadImage("/Users/dangjumhan/Desktop/OOPS LAB/OOP-2021-2022/images/WechatIMG136.jpeg");
    }




    public void setup(){


        colorMode(RGB);

        minim = new Minim(this);
        song = minim.loadFile("C:/Users/hogar/OneDrive/Desktop/VisualAssignment/MusicVisuals/images/AC-DC_-_Touch_Too_Much_Official_Video.mp3", 1024);


        song.play();

        smooth();

    }



    public void draw(){

        //image(imagebackground, 0, 0);
        noStroke();
        fill(0, 5);
        rect(0,0,width,height);
        pushMatrix();
        translate(width/2, height/2);
        rotate(radians(frameCount % 360 * 2));
    for(int j = 0; j < 200; j++) {

      if(song.mix.get(j)*200 > 50) {
        stroke(56,50,100);
      }
      else {
        stroke(123,100,100);
      }

      line(cos(j)*150, sin(j)*150, cos(j)*abs(song.left.get(j))*150 + cos(j)*150, sin(j)*abs(song.right.get(j))*150 + sin(j)*150);
    }
    for(int k = 360; k > 0; k--) {


      if(song.mix.get(k)*200 > 25) {
        stroke(70,100,100);
      }
      else {
        stroke(69,100,100);
      }


      line(cos(k)*50, sin(k)*50, cos(k)*abs(song.right.get(k))*200 + cos(k)*50, sin(k)*abs(song.left.get(k))*200 + sin(k)*50);
    }

  popMatrix();

    }

}