/*

TARA = LLEVAR LA BALANZA A CERO
MODE = CALIBRAR LA BALANZA
SON BOTONES DESDE INTERFAZ


*/

//Define las variables globales
byte DT = 33;
byte CLK = 32;
byte modo = 45;
byte tara = 50;
int peso_conocido[5] = {189,500,1000,3000,5000};
long escala;

//Crear el objeto balanza
HX711 balanza;



//Función de Anti-debounce (Evitar el rebote del pulsador)


void anti_debounce(byte boton){
      delay(100);
      while(digitalRead(boton)); //Anti-Rebote
      delay(100);
}

//Función de calibración y ajuste
void calibration(){
  
  int i = 0,cal=1;
  long adc_lecture;
  

  delay(1500);
  balanza.read();
  balanza.set_scale(); //La escala por defecto es 1
  balanza.tare(20);  //El peso actual es considerado Tara.
 
  //Inicia el proceso de ajuste y calibración
  while(cal == 1){
    
    //Busca el peso conocido con el botón tara
    if(digitalRead(tara)){ 
       
      anti_debounce(tara);
      i =(i>2) ? 0:i+1; //if-else en una linea
    }
    
    //Selecciona el peso conocido con el boton modo
    if(digitalRead(modo)){
      
      delay(2000);
      //Lee el valor del HX711
      adc_lecture = balanza.get_value(100);
      //Calcula la escala con el valor leido dividido el peso conocido
      escala = adc_lecture / peso_conocido[i];
      //Guarda la escala en la EEPROM
      EEPROM.put(0,escala );
      delay(100);
      cal = 0; //Cambia la bandera para salir del while
      //lcd.clear();
    }
    
  }
}
void setup_Peso(){
  //Configura la balanza
  balanza.begin(DT, CLK);
  
  //Configura los botones
  pinMode(modo, INPUT_PULLDOWN);
  pinMode(tara, INPUT_PULLDOWN);
  
  //Lee el valor de la escala en la EEPROM
  EEPROM.get(0,escala);
  
  //Pregunta si se desea entrar en calibración
  if(digitalRead(modo) && digitalRead(tara))
    calibration();
    
 
  delay(2000);
  
  balanza.set_scale(escala); // Establecemos la escala
  balanza.tare(20);  //El peso actual es considerado Tara.
  
  delay(1000);
}


void Get_peso(){
  
  float peso;
  
  //Mide el peso de la balanza
  peso = balanza.get_units(10);
  
  delay(5);
  
  //Botón de Tara
    if(digitalRead(tara)){  
      anti_debounce(tara);
      balanza.tare(10);  //El peso actual es considerado Tara.
    }
    
}
