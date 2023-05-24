
#include <SoftwareSerial.h>
#define RX_PIN 0
#define TX_PIN 1

SoftwareSerial espSerial(RX_PIN, TX_PIN);
String dato;

float temperatura, ECG_lec, Oxigeno_sangre;


void setup(){

  espSerial.begin(9600);
  setup_Oximetro();
  setup_ECG();
  setup_Temperatura();
}

void loop(){

  Ejecutar_temperatura();
  ECG();
  Oximetro();
  dato = String(temperatura)+","+String(ECG_lec)+","+String(Oxigeno_sangre)+",";
  espSerial.println(dato);
  Serial.println(dato);
}
