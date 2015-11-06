# Trabalho 5
Código do cliente: clienteUDP.c
Código do servidor: Programa2.c

Foi passado o código de um programa que recebe e envia mensagens por protocolo UDP.
O programa normalmente responde com a mensagem "Você digitou %s", onde %s e' o string enviado.

Entretanto, o programa tem uma falha de segurança GRAVE. O objetivo do trabalho é explorar essa falha, para que o programa retorne a mensagem "Parabens, voce concluiu o trabalho!!!"

-> IMPORTANTE: Não é permitido alterar os fontes do arquivo. Deve-se fazer o programa exibir esta mensagem apenas enviando mensagens UDP pela rede.


Para isso foi utilizado o comando readelf -s no executavel do servidor para ver os endereços das funções. Calculado a 'distância' entre as duas e então passado uma string com este tamanho + o endereço da função que exibe a mensagem desejada. (Acho que foi isso, teve muito tentativa e erro).

