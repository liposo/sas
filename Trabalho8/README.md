#Trabalho 8

##Descrição fornecida: [Trabalhos](https://docs.google.com/document/d/1MMbkVkNm2D4nWaywwZyuAqqa_KM9OJOajtUJfy6yk04)
Implementar mecanismo de geração de chaves para a cifra RSA:

xed = x (mod N)

Onde e é a chave de cifragem e d é a chave de decifragem.

Como xφ(N) = 1 (mod N)  (prova em [1]) segue que xφ(N) + 1 = x (mod N) 
Como xφ(N) + 1 = x (mod N) e xed = x (mod N), entao φ(N) + 1 = ed


Aplicando o modulo φ(N) em φ(N) + 1 = ed temos 1 = ed (mod φ(N)) 
Ou seja e e d são um número e seu inverso multiplicativo modulo φ(N)

Para calcular pares de números e e d:
	Escolher quaisquer dois números primos p e q e um numero N sendo pq = N
	Calcular φ(N), usando φ(N) = φ(p)φ(q) (prova em [2])
	Escolher um número qualquer e que seja coprimo a φ(N)
	Calcular d = e-1 (mod φ(N))

##Versão 0.1
![Alt tag](https://raw.githubusercontent.com/liposo/sas/master/Trabalho8/img/main.PNG)
