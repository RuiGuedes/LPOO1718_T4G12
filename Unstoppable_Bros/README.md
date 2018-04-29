# LPOO1718_T4G12

Project for the Object Oriented Programming Lab (LPOO) class of the Master in Informatics and Computer Engineering (MIEIC) at the Faculty of Engineering of the University of Porto (FEUP). 


## Team Members 


Rui Jorge Leão Guedes <br>
* Student Number: 201603854
* E-Mail: up201603854@fe.up.pt

César Alexandre da Costa Pinho <br>
* Student Number: 201604039
* E-Mail: up201604039@fe.up.pt 


## UML class diagram 
-----

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/Unstoppable_Bros/Delivery/Diagrama%20UML.png)

## State diagram :: Behavioural aspects
-----

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/Unstoppable_Bros/Delivery/Diagrama%20de%20Estados.png)


## Design Patterns
-----

* Singleton - Do jogo irá apenas existir apenas uma única instância do mesmo.
* Observer - Existem diversas relações entre os diferentes objetos no jogo, desde alancavas a balas, que implicam por sua vez, a necessidade gerir a sua interação com outros objetos.
* Flyweight - Utilizado para representar os vários inimigos existentes no jogo, uma vez que, todos partilham entre si a mesma representação gráfica.
* Strategy - Cada inimigo age de forma diferente sendo assim necessário gerir os diversos tipos de inimigos existentes no jogo.
* Update Method - Os inimigos possuem movimento, daí a necessidade deste padrão de desenho com vista a não bloquear o jogo aquando o movimento destes.
* Component - Os heróis constituem as entidades mais complexas do jogo daí a necessidade de fazer, separadamente, uma gestão das suas respetivas componentes.

## GUI Design 
-----


## Test Design 
-----

A nível de testes, o jogo vai ser testado a nível lógico sendo realizados nomeadamente os seguintes testes:

* o movimento dos heróis 
Testar o comportamento dos vários tipos de imigos
Testar a interação entre heróis e inimigos, nomeadamente o uso de balas
Testar a captura de bónus no jogo
Testar a interação do herói com elementos de teletransporte, plataformas e mecanismos de peso
Testar a interação do herói com a zona de perigo
Testar a finalização do nível. 
