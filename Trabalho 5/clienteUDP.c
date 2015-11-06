#include <stdio.h> 
#include <string.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>
 
#define SERVER "127.0.0.1" 
#define PORTA 32000  
#define BUFFER_LENGTH 1000 

int main(void) {

    struct sockaddr_in si_other;
    
    int s = 0; 
    int i = 0;
    int n = 0; 
    int slen = sizeof(si_other);
    
    char msgEnviada[BUFFER_LENGTH];
    char msgResposta[BUFFER_LENGTH];
    char msgEsperada[40];
    
    strcpy(msgEsperada, "Parabens, voce concluiu o trabalho!!!");
    
    s = socket(AF_INET, SOCK_DGRAM, 0);
 
    bzero(&si_other, sizeof(si_other));
    si_other.sin_family = AF_INET;
    si_other.sin_port = htons(PORTA);
     
    if(inet_aton(SERVER, &si_other.sin_addr) == 0) {
        fprintf(stderr, "inet_aton() failed\n");
        exit(1);
    }
    
    msgEnviada[0] = 'F';
    msgEnviada[1] = 'U';
    msgEnviada[2] = 'N';
    msgEnviada[3] = 'C';
    msgEnviada[4] = 'I';
    msgEnviada[5] = 'O';
    msgEnviada[6] = 'N';
    msgEnviada[7] = 'A';
    msgEnviada[8] = '?';
    msgEnviada[9] = 0;

    for(i = 9; i < 231; i++){
        msgEnviada[i] = ' ';	
    }

    msgEnviada[232] = 0xdc;
    msgEnviada[233] = 0x07;
    msgEnviada[234] = 0x40;
    msgEnviada[235] = 0x00;
    msgEnviada[236] = 0x00;
    msgEnviada[237] = 0x00;
    msgEnviada[238] = 0x00;
    msgEnviada[239] = 0x00;
	
    //printf("\n%d\n", strlen(msgEnviada)); Foi usado para determinar +9 em (strlen(msgEnviada)+9)
	
    sendto(s, msgEnviada, (strlen(msgEnviada)+9), 0, (struct sockaddr*)&si_other, sizeof(si_other));
	
    n = recvfrom(s, msgResposta, 10000, 0, NULL, NULL);
   
    msgResposta[n] = 0;
    
    printf("%s\n", msgResposta);

    return 0;
}
