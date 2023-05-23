#include "HX711.h"
#include <Wire.h> 


byte DT=25;
byte CLK=26;
long escala = -174;
boolean estado = false;
//Crear el objeto balanza
HX711 balanza;


void setup_Peso(){
  
    balanza.begin(DT, CLK);
    balanza.set_scale(escala);
    balanza.tare(10);
}


void Get_peso(){
  
  peso = balanza.get_units(10);
  //Botón de Tara
  /*]if(digitalRead(tara)){  
        balanza.tare(10);  //El peso actual es considerado Tara.
  }*/
  
}
