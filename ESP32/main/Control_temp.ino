hw_timer_t *My_timer = NULL;                                        // Apuntar a una variable de tipo hw_timer_t.     

//#include <DHT.h>                                                  // Librería sensor de temperatura y humedad.
#include <DHT_U.h>
                         
volatile int i=0;                                         // Variable usada por el contador.
volatile boolean cruce_cero=0;                           // Variable que actúa como switch al detectar cruce por cero.
const uint8_t pinInterrupcion = 4;                      // Pin de interrupción de cruce por cero.
const uint8_t Triac = 19;                              // Salida conectada al optoacoplador MOC 3011.                  
               

volatile unsigned long Tiempo_anterior;          // Variable que guarda el tiempo anterior transcurrido en el programa.
unsigned long Tiempo_actual;                    // Variable que guarda el tiempo actual transcurrido en el programa.
volatile double dT;                            // Delta de tiempo.
double kp=20;                                 // Ganancia control proporcional.
double ki=0.1;                               // Ganancia control proporcional.
double kd = 10;                             // Ganancia control derivativo.
double error=0;                           // Variable de error en el sistema de calefacción.
double Error_suma=0;                     // Variable que guarda y suma los errores en un transcurso de tiempo.
double Error_ant=0;                     // Variable que guarda el tiempo anterior después antes de que actualice el tiempo de muestreo.
double Error_der=0;                    // Variable que calcula el error derivativo.

void Dimer(){                      // Función del Dimer, detecta el cruce por cero y da el disparo cuando se llega a los grados eléctricos requeridos.
    if(bandera_modo == 2){                
    if (cruce_cero == true ){
      
      if (i>=dim){
          digitalWrite(Triac, HIGH);
          i=0;
          cruce_cero=false;
      }
      else{
        i++;
      }
    }
    }
}
  
void IRAM_ATTR onTimer(){   // Se ejecuta cada 40 microsegundos...
       
    Dimer();
}

void setup_PID(){
  
    dht.begin();
    
    My_timer = timerBegin(1, 80, true); // Conf del timer. Timer 1/preescalador/conteo ascendente.
    timerAttachInterrupt(My_timer, &onTimer, true);
    timerAlarmWrite(My_timer, 40, true);
    timerAlarmEnable(My_timer); 
    
    pinMode(Triac, OUTPUT);
                               
 
    attachInterrupt(digitalPinToInterrupt(pinInterrupcion), deteccion_Cruce_cero, RISING);

    Tiempo_anterior = 0;
}   
                                                         
void deteccion_Cruce_cero(){    // Si existe un cruce por cero entonces la variable "cruce_cero" cambia a TRUE...
  
    cruce_cero = true;        //...reseteando el valor de "i", y colocando la salida conectada al Triac en estado bajo.
    i=0;                                                 
    digitalWrite(Triac, LOW); 
}   

void PID_Temperatura(){

    Tiempo_actual = millis();                           // Guarda el tiempo que transcurre una vez se ejecuta el programa.

    dT = (double)(Tiempo_actual - Tiempo_anterior); 
      
    if(dT>=1){                                       // Tiempo de muestreo de 1 milisegundo.

            dim = 179 - ((kp*error) + (Error_suma) + (kd*Error_der));
            
            hum = dht.readHumidity();               
            temp = dht.readTemperature();
    
            if(temp>=0 && temp<=Sep_temp+100) error = Sep_temp - temp;  // Condición clave para que esa chimbada funcione.
            Error_der = error - Error_ant;
            Error_suma += (ki*error);
    
            if(dim>=179){    

                dim = 179;
            }
 
             if(dim<=0){
                dim = 0;  
            }
      }
        
        Error_ant = error;
        Tiempo_anterior = Tiempo_actual;
}
