hw_timer_t *My_timer1 = NULL;                                        // Apuntar a una variable de tipo hw_timer_t. 

void IRAM_ATTR tiempo_envioJAVA(){   // Se ejecuta cada 2 segundos
       
    variador_envio++;
}


void setup_JAVA(){

    My_timer1 = timerBegin(0, 80, true); // Conf del timer. Timer 1/preescalador/conteo ascendente.
    timerAttachInterrupt(My_timer1, &tiempo_envioJAVA, true);
    timerAlarmWrite(My_timer1, 1000000, true);
    timerAlarmEnable(My_timer1); 
    
}  


void envio_JAVA(){
    if (Serial.available() <= 0){
      if(variador_envio%2 == 0){
      //Serial.println(String(temp)+","+String(peso)+","+String(dim)+","+Valores_sensores_ARDUINO[0]+","+Valores_sensores_ARDUINO[1]+","+Valores_sensores_ARDUINO[2]+","+String(hum)+","+String(bandera_alarmas)+",");
      Serial.println(String(temp)+","+String(peso)+","+String(dim)+","+"80"+","+"70"+","+"30"+","+String(hum)+","+String(bandera_alarmas)+","+String(Sep_temp)+",");

      variador_envio++;
    }
    }
}




void recepcion_JAVA(){
  if (Serial.available() > 0) {
    
    inputString = Serial.readStringUntil('\n'); // Leer una línea completa de datos del puerto serial
    char* token = strtok(const_cast<char*>(inputString.c_str()), ","); // Obtener el primer token
    int i = 0;
    while (token != NULL && i < NUM_DATOS_JAVA) {
      Valores_JAVA[i] = atoi(token); // Convertir el token actual a un valor entero y almacenarlo en el arreglo
      token = strtok(NULL, ","); // Obtener el siguiente token
      i++;
    }

    if(Valores_JAVA[3] > 0){
      Sep_temp = Valores_JAVA[3];
    }
}
}
