### Reimplementação do Trabalho 1 utilizando [Apache Commons IO 2.4](https://commons.apache.org/proper/commons-io/ target="_blank")
Implementação em Java de quatro métodos clássicos de criptografia.

Main                       |  Arquivo Selecionado
:-------------------------:|:-------------------------:
![ScreenShot](/Reimplementação%20do%20Trabalho%201/img/main.PNG)|![ScreenShot](/Reimplementação%20do%20Trabalho%201/img/mainSelectedFile.PNG)

#### 1- [Cifra de César](https://pt.wikipedia.org/wiki/Cifra_de_C%C3%A9sar){target="_blank"}:
Tipo de cifra de substituição em que cada caractere é movido um número fixo de vezes.

Criptografar               |  Descriptografar
:-------------------------:|:-------------------------:
![ScreenShot](/Reimplementação%20do%20Trabalho%201/img/cesarEnc.PNG)  |  ![ScreenShot](/Reimplementação%20do%20Trabalho%201/img/cesarDec.PNG)

#####Utilização:
    1-Selecione um arquivo.
    2-Clique no botão César.
    3-Informe a chave e clique no botão habilitado ao lado.

#### 2- [Cifra de Substituição](https://pt.wikipedia.org/wiki/Cifra_de_substitui%C3%A7%C3%A3o){target="_blank"}:
Possui um sistema pré-definido de substituição, seguindo uma tabela, todo vez que um elemento x aparecer ele será susbtituido pelo
elemento correspondente da tabela.

Criptografar               |  Descriptografar
:-------------------------:|:-------------------------:
![ScreenShot](/Reimplementação%20do%20Trabalho%201/img/subsEnc.PNG)  |  ![ScreenShot](/Reimplementação%20do%20Trabalho%201/img/subsDec.PNG)

#####Utilização:
    1-Selecione um arquivo.
    2-Clique no botão Substituição.
    3-Clique na botão habilitado.

#### 3- [Cifra de Transposição](https://pt.wikipedia.org/wiki/Cifra_de_transposi%C3%A7%C3%A3o){target="_blank"}:
Nessa cifra é formada uma matriz onde a quantidade de linhas é igual ao valor da chave fornecida, então é feito a transposição dessa matriz.

Criptografar               |  Descriptografar
:-------------------------:|:-------------------------:
![ScreenShot](/Reimplementação%20do%20Trabalho%201/img/transpEnc.PNG)  |  ![ScreenShot](/Reimplementação%20do%20Trabalho%201/img/transpDec.PNG)

#####Utilização:
    1-Selecione um arquivo.
    2-Clique no botão César.
    3-Informe a chave e clique no botão habilitado ao lado.

#### 4- [Cifra de Vigenère](https://pt.wikipedia.org/wiki/Cifra_de_Vigen%C3%A8re){target="_blank"}:
Semelhante a cifra de César, nessa cifra utiliza-se como senha uma palavra ou frase, essa senha é repetida até ficar igual ao tamanho do texto.

Criptografar               |  Descriptografar
:-------------------------:|:-------------------------:
![ScreenShot](/Reimplementação%20do%20Trabalho%201/img/vigEnc.PNG)|![ScreenShot](/Reimplementação%20do%20Trabalho%201/img/vigDec.PNG)

#####Utilização:
    1-Selecione um arquivo.
    2-Clique no botão César.
    3-Informe a chave e clique no botão habilitado ao lado.
