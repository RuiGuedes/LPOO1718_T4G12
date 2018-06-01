# LPOO1718_T4G12

Project for the Object Oriented Programming Lab (LPOO) class of the Master in Informatics and Computer Engineering (MIEIC) at the Faculty of Engineering of the University of Porto (FEUP). 


## Team Members 


Rui Jorge Leão Guedes <br>
* Student Number: 201603854
* E-Mail: up201603854@fe.up.pt

César Alexandre da Costa Pinho <br>
* Student Number: 201604039
* E-Mail: up201604039@fe.up.pt 


## Setup/Installation
-----

## Relevant Design Decisions
-----

## Major Difficulties
-----

## Work Distribution - Overall Time Spent 
-----

## User Manual - GUI Design 
-----

De seguida encontra-se especificado simultãneamente a interface gráfica do jogo desenvolvido bem como o seu manual de utilização, isto é, como proceder para utilização do mesmo. 

* ### Main Menu Screen

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/Delivery/MainMenuDesign.png)

* ### Play Game Screen

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/Delivery/PlayGameDesign.png)

* ### Settings Screen

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/Delivery/SettingsDesign.png)

* ### Tutorial Screens

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual1.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual2.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual3.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual4.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual5.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual6.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual7.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual8.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual9.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual10.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual11.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual12.png)

## UML class diagram 
-----

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/Delivery/Diagrama%20UML.png)

## State diagram :: Behavioural aspects
-----

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/Screenshots/StateDiagram.png)

## Design Patterns
-----
De seguida encontram-se os padrões de desenho que serão utilizados no desenvolvimento do jogo:

* Singleton - Do jogo irá apenas existir apenas uma única instância do mesmo.
* Observer - Existem diversas relações entre os diferentes objetos no jogo, desde alancavas a balas, que implicam por sua vez, a necessidade gerir a sua interação com outros objetos.
* Flyweight - Utilizado para representar os vários inimigos existentes no jogo, uma vez que, todos partilham entre si a mesma representação gráfica.
* Strategy - Cada inimigo age de forma diferente sendo assim necessário gerir os diversos tipos de inimigos existentes no jogo.
* Update Method - Os inimigos possuem movimento, daí a necessidade deste padrão de desenho com vista a não bloquear o jogo aquando o movimento destes.
* Component - Os heróis constituem as entidades mais complexas do jogo daí a necessidade de fazer, separadamente, uma gestão das suas respetivas componentes.
