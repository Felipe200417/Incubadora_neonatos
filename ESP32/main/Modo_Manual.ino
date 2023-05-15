// Variables a controlar manualmente
const int ventilador = 19;
const int luz_uv = 21;
const int extractor = 15;


void setup_Manual(){
  
  pinMode(ventilador,OUTPUT);
  pinMode(luz_uv,OUTPUT);
  pinMode(extractor,OUTPUT);
  
}

void modo_manual(){

  if(Valores_JAVA[1] == 11 && Valores_JAVA[0] == 1){
    digitalWrite(ventilador,HIGH);
  }

  if(Valores_JAVA[1] == 10 && Valores_JAVA[0] == 1){
    digitalWrite(ventilador,LOW);
  }

  if(Valores_JAVA[1] == 21 && Valores_JAVA[0] == 1){
    digitalWrite(luz_uv,HIGH);
  }
  
  if(Valores_JAVA[1] == 20 && Valores_JAVA[0] == 1){
    digitalWrite(luz_uv,LOW);
  }
  

}
