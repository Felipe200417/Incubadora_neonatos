void envio_JAVA(String i){
  Serial.println(i);
}


void recepcion_JAVA(){
  if (Serial.available() > 0) {
    //bandera_java = atoi(Serial.readString().c_str());
    
    inputString = Serial.readStringUntil('\n'); // Leer una línea completa de datos del puerto serial
    char* token = strtok(const_cast<char*>(inputString.c_str()), ","); // Obtener el primer token
    int i = 0;
    while (token != NULL && i < NUM_DATOS_JAVA) {
      Valores_JAVA[i] = atoi(token); // Convertir el token actual a un valor entero y almacenarlo en el arreglo
      token = strtok(NULL, ","); // Obtener el siguiente token
      i++;
    }
  }
}
