package ie.tudublin;


import java.lang.Math;
import ddf.minim.*;
import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PImage;
import processing.opengl.*;


public class circle1 extends PApplet {
    final int SCREEN_WIDTH = 1920;
    final int SCREEN_HEIGHT = 1080;

    final float phi = ((float)Math.sqrt(5)+1)/2 - 1;

    final float sphereRadius = 10.0f;
    final float strokeWeight = 0.35f;
    final float rotateSpeed = 0.001f;
    final int numberOfPoints = 2000;

    PVector[] points;
    float min = Float.POSITIVE_INFINITY;
    float max = Float.NEGATIVE_INFINITY;

    float angle = 1;
    final float angleIncrement = 0.05f;


    Minim minim;
    AudioPlayer song;
    

    public void settings(){

        fullScreen(P3D);
        size(SCREEN_WIDTH, SCREEN_HEIGHT, P3D);
        //imagebackground = loadImage("/Users/dangjumhan/Desktop/OOPS LAB/OOP-2021-2022/images/WechatIMG136.jpeg");
    }




    public void setup(){


        colorMode(RGB);

        minim = new Minim(this);
        song = minim.loadFile("MusicVisuals/images/AC-DC_-_Touch_Too_Much_Official_Video.mp3", 1024);

        generateFibSphere(numberOfPoints, sphereRadius);

        // find max and min position values for colors
        for (PVector p : points){
            if (p.x > max) {
                max = p.x;
            }
            if (p.x < min) {
                min = p.x;
            }
        }
        song.play();

        smooth();

    }



    public void draw(){
      
      background(30);
      speaker();
      body();
      body2();
      
      

      drawFibSphere(strokeWeight, true);
        //image(imagebackground, 0, 0);
        noStroke();
        fill(0, 5);
        
        pushMatrix();
        translate(width/2, height/2);
        rotate(radians(frameCount % 360 * 5));


      popMatrix();

    }

    void generateFibSphere(int n, float radius) {
      points = new PVector[n];

      for (int i = 0; i < points.length; i++) {
          float longitude = phi * TWO_PI * i;
          longitude /= (2 * PI);
          longitude -= floor(longitude);
          longitude *= TWO_PI;

          if (longitude > PI) {
              longitude -= (2 * PI);
          }

          final float latitude = (float)Math.asin(-1 + 2 * i/(float)n);
          final float cosOfLatitude = (float)Math.cos(latitude);
          points[i] = new PVector(
                  radius * cosOfLatitude * (float)Math.cos(longitude),
                  radius * cosOfLatitude * (float)Math.sin(longitude),
                  radius * (float)Math.sin(latitude)
          );
      }
  }

  void drawFibSphere(float strokeWeight, boolean oscillation) {
    translate((float)width/2, (float)height/5, 0);
    scale(10);
    if (oscillation){
        float oscillatingWeight = strokeWeight * map((float)Math.sin(angle), 2, 1, 2, 2);
        strokeWeight(oscillatingWeight);
    }
    else {
        strokeWeight(strokeWeight);
    }

    pushMatrix();
    rotateX(millis() * rotateSpeed);
    rotateY(millis() * rotateSpeed*0.5f);

    
        
    colorMode(RGB);
    for (PVector p : points) {
        final int r = (int)map(p.x, min, max, 100, 255);
        final int g = (int)map(p.y, min, max, 100, 255);
        final int b = (int)map(p.z, min, max, 100, 255);

        stroke(r, g, b);
        point(p.x, p.y, p.z);
    }
    for(int j = 0; j < 200; j++) {
      
      if(song.mix.get(j)*200 > 50) {
        stroke(56,50,100);
      }
      else {
        stroke(35,35,35);
      }
      
      line(cos(j)*60, sin(j)*60, cos(j)*abs(song.left.get(j))*75 + cos(j)*75, sin(j)*abs(song.right.get(j))*75 + sin(j)*75);
    }
    for(int k = 360; k > 0; k--) {
      
      final int r = (int)map((float) (cos(k)*Math.random()*20), min, max, 100, 255);
      final int g = (int)map((float) (sin(k)*Math.random()*20), min, max, 100, 255);
      final int b = (int)map((float) (tan(k)*Math.random()*20), min, max, 100, 255);

      if(song.mix.get(k)*200 > 25) {
        stroke(r,g,b);

      }
      else {
        stroke(r,g,b);
      }


      line(cos(k)*10, sin(k)*10, cos(k)*abs(song.right.get(k))*100 + cos(k)*25, sin(k)*abs(song.left.get(k))*100 + sin(k)*25);
     
    }

    popMatrix();
    angle += angleIncrement;
  }

  void speaker() {




  

    for(int j = 0; j < 360; j++) {
 

      
      int smalld = 90;
      int larged = 130;
      int flying = 60;
      if(song.mix.get(j)*360 > 60) {
        
        flying ++;
        

      }
      if(song.mix.get(j)*360 > 30) {
         smalld = 90;
        larged = 130;
      }
      else {
        smalld = 60;
        larged = 100;
      
      }
      //speaker1
    fill(40,40,40);
    rect(200, 400, 200, 400 );
    //speaker circle 1
    fill(35,35,35);
    circle(300, 500 , larged);

    noFill();
    
    circle(300,500, flying);

    fill(40,40,40);
    circle(300,500, smalld);
    
    //speaker circle 2
    fill(35,35,35);
    circle(300, 650 , larged);
    noFill();
    
    circle(300,650, flying);

    
    fill(40,40,40);
    circle(300,650, smalld);


    //speaker2
    fill(40,40,40);
    rect(1600, 400, 200, 400);
    //speaker circle 1
    fill(35,35,35);
    circle(1700, 500 , larged);
    
    noFill();
    
    circle(1700,500, flying);
    
    fill(40,40,40);
    circle(1700,500, smalld);
    //speaker circle 2
    fill(35,35,35);
    circle(1700, 650 , larged);
    
    fill(40,40,40);
    circle(1700,650, smalld);

    }

    



    
  }

  void body() {
    int ys = 0;
    for(int j = 0; j < 360; j++) {
      
      if(song.mix.get(j)*200 > 20) {
          ys = 100;
        
      }
      else {
        ys = 0;
      }
    
    int r = 40;
    stroke(70);
    //the head
    circle(640, 480, r*2);

    //the body
    line(640, 650, 640, 500 + r/2);

    //the arms
    line(640, 575, 590, 550);
    line(640, 575, 690, 600);
       
    // the hands
    line(590, 550, 540, 600-ys);
    line(690, 600, 740, 550+ys);
    
    // the legs
    line(640, 650, 590, 750);
    line(640, 650, 690, 750);
    }
  }

  void body2() {
      int r = 40;
      stroke(70);
      //the head
      circle(860, 480, r*2);
  
      //the body
      line(860, 650, 860, 500 + r/2);
  
      //the arms
      line(860, 575, 910, 550);
      line(860, 575, 810, 600);
          
      // the hands
      line(910, 550, 960, 600);
      line(810, 600, 760, 550);
      
      // the legs
      line(860, 650, 810, 750);
      line(860, 650, 910, 750);
  }



}