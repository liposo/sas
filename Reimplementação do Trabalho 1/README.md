### Reimplementação do Trabalho 1 utilizando [Apache Commons IO 2.4](https://commons.apache.org/proper/commons-io/)
Implementação em Java de quatro métodos clássicos de criptografia.

Main                       |  Arquivo Selecionado
:-------------------------:|:-------------------------:
![]https://raw.githubusercontent.com/liposo/sas/master/Reimplementa%C3%A7%C3%A3o%20do%20Trabalho%201/img/main.PNG  |  ![]https://raw.githubusercontent.com/liposo/sas/master/Reimplementa%C3%A7%C3%A3o%20do%20Trabalho%201/img/mainSelectedFile.PNG

#### 1- Cifra de César:
Tipo de cifra de substituição em que cada caractere é movido um número fixo de vezes.

Criptografar               |  Descriptografar
:-------------------------:|:-------------------------:
![]https://raw.githubusercontent.com/liposo/sas/master/Reimplementa%C3%A7%C3%A3o%20do%20Trabalho%201/img/cesarEnc.PNG  |  ![]https://raw.githubusercontent.com/liposo/sas/master/Reimplementa%C3%A7%C3%A3o%20do%20Trabalho%201/img/cesarDec.PNG

#####Utilização:
    1-Selecione um arquivo.
    2-Clique no botão César.
    3-Informe a chave e clique no botão habilitado ao lado.

#### 2- Cifra de Substituição:
Possui um sistema pré-definido de substituição, seguindo uma tabela, todo vez que um elemento x aparecer ele será susbtituido pelo
elemento correspondente da tabela.

Criptografar               |  Descriptografar
:-------------------------:|:-------------------------:
![]https://raw.githubusercontent.com/liposo/sas/master/Reimplementa%C3%A7%C3%A3o%20do%20Trabalho%201/img/subsEnc.PNG  |  ![]https://raw.githubusercontent.com/liposo/sas/master/Reimplementa%C3%A7%C3%A3o%20do%20Trabalho%201/img/subsDec.PNG

#####Utilização:
    1-Selecione um arquivo.
    2-Clique no botão Substituição.
    3-Clique na botão habilitado.

#### 3- Cifra de Transposição
Nessa cifra é formada uma matriz onde a quantidade de linhas é igual ao valor da chave fornecida, então é feito a transposição dessa matriz.

Criptografar               |  Descriptografar
:-------------------------:|:-------------------------:
![]https://raw.githubusercontent.com/liposo/sas/master/Reimplementa%C3%A7%C3%A3o%20do%20Trabalho%201/img/transEnc.PNG  |  ![]https://raw.githubusercontent.com/liposo/sas/master/Reimplementa%C3%A7%C3%A3o%20do%20Trabalho%201/img/transpDec.PNG

#####Utilização
    1-Selecione um arquivo.
    2-Clique no botão César.
    3-Informe a chave e clique no botão habilitado ao lado.

#### 4- Cifra de Vigenère
Semelhante a cifra de César, nessa cifra utiliza-se como senha uma palavra ou frase, essa senha é repetida até ficar igual ao tamanho do texto.

Criptografar               |  Descriptografar
:-------------------------:|:-------------------------:
![]https://raw.githubusercontent.com/liposo/sas/master/Reimplementa%C3%A7%C3%A3o%20do%20Trabalho%201/img/vigEnc.PNG  |  ![]https://raw.githubusercontent.com/liposo/sas/master/Reimplementa%C3%A7%C3%A3o%20do%20Trabalho%201/img/vigDec.PNG

#####Utilização
    1-Selecione um arquivo.
    2-Clique no botão César.
    3-Informe a chave e clique no botão habilitado ao lado.
