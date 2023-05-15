// INTERVALOS DE TEMPERATURA AMBIENTE
int min_Tem_Ambiente = 12;
int max_Tem_Ambiente = 30;

// INTERVALOS DE TEMPERATURA BEBE

int min_Tem_bebe = 35;
int max_Tem_bebe = 37;

// INTERVALO HUMEDAD

int Humedad_Estable = 98;

// INTERVALO OXIPULSIMETRO

int Oxigenacion_Estable = 98;


// ASIGNACION DE PINES LUZ ALARMA

const int Alertas_Activadas = 13;
const int Estado_Alarmas_Optimo = 5;
const int Alarma_Activadas = 27;
const int Sensor_Magnetico = 22; 


void setup_alarmas(){
  pinMode(Alertas_Activadas, OUTPUT); 
  pinMode(Estado_Alarmas_Optimo, OUTPUT); 
  pinMode(Alarma_Activadas, OUTPUT); 
  pinMode(Sensor_Magnetico, INPUT);
  
}



void alarmas(){

  // Alarma por temperatura ambiente
  if(temp < min_Tem_Ambiente || temp > max_Tem_Ambiente){
    bandera_alarmas = 1;
  }

  // Alarma por humedad
  if(hum > Humedad_Estable){
    bandera_alarmas = 1;
  }

  // Alarma por temperatura del bebé
  if(Valores_sensores_ARDUINO[0].toInt() < min_Tem_bebe || Valores_sensores_ARDUINO[0].toInt()  > max_Tem_bebe){
    bandera_alarmas = 1;
  }

  // Alarma por apertura
  if(digitalRead(Sensor_Magnetico) == HIGH){
     bandera_alarmas = 1;
  }

  // Alarma por oximetro 

  if(Valores_sensores_ARDUINO[1].toInt() < Oxigenacion_Estable){
    bandera_alarmas = 1;
  }

}
