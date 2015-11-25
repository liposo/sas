#Trabalho 7

##Descrição fornecida: [Trabalhos](https://docs.google.com/document/d/1MMbkVkNm2D4nWaywwZyuAqqa_KM9OJOajtUJfy6yk04)

Implementar a troca de chaves usando Diffie-Helman.

Supor  2 interlocutores tentando combinar uma chave secreta, A e B.
Escolher um numero a e um numero N, tal que a e’ uma raiz primitiva modulo N.

	A escolhe um numero p  ZN e A escolhe um numero r  ZN.
	A calcula q = ap (mod N) e envia para B
	B calcula s = ar (mod N) e envia para A
	A calcula sp (mod N)
	B calcula qr (mod N)

Mostrar todos os valores intermediarios.

##Versão 0.1
![Alt tag](https://raw.githubusercontent.com/liposo/sas/master/Trabalho%207/img/main.PNG)
