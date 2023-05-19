#include <Wire.h>
float Sign_AD8232;
float *SingDef = &Sign_AD8232;


void setup_ECG() {

  Serial.begin(9600);
  Serial.println(); // blank line in serial ...
  pinMode(10, INPUT); // Setup for leads off detection LO +
  pinMode(11, INPUT); // Setup for leads off detection LO -
  pinMode(A0, INPUT);

}

float Sing(){
  if ((digitalRead(10) == 1) || (digitalRead(11) == 1)) {
     Sign_AD8232 = 0;

  }
  else {
    Sign_AD8232 = analogRead(A0);
    
  }
  delay(10);

  return Sign_AD8232; 
  
}

void ECG(){
  Sing();
  SingDef = &Sign_AD8232;
  Serial.println(*SingDef);
}
