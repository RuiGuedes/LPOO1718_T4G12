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

O setup/instalação da aplicação baseia-se somente na instalação do [apk](https://github.com/RuiGuedes/LPOO1718_T4G12/tree/master/Unstoppable_Bros/FinalRelease/apk) entregue. No entanto é necessário ter em atenção as especificações utilizadas para desenvolvimento do projeto de forma a evitar eventuais erros a nível de compilação ou execução do jogo. Seguem-se então as especificações utilizadas:

* Android Studio version = **3.1.2**
* Gradle version = **4.6**
* Gdx version = **1.9.8**
* CompileSdkVersion = **27**
* MinSdkVersion = **16**
* TargetSdkVersion = **27**

## Relevant Design Decisions
-----

O desenvolvimento do jogo, focou-se essencialmente para sistemas android, no entanto este encontra-se funcional para desktop, apesar de tal não ser recomendado uma vez que o jogo utiliza o conceito de multi-touching. Para além deste factor bem como todo o desenvolvimento do jogo em si, existem dois factores que são importantes de realçar:

* **_Networking_** - Inicialmente para desenvolvimento do jogo especificou-se que este implementaria uma parte de networking que por sua vez iria permitir conectar dois jogadores, em que cada um era responsável por controlar um determinado personagem. No entanto após o desenvolvimento do jogo, dada a sua complexidade, não foi possível efetuar a troca de dados entre jogadores uma vez que o networking implementado possui um sistema de troca de informação muito simplista baseado em strings. No entanto o código relativo à implementação de networking encontra-se desenvolvido e totalmente documentado no package _Networking_.

* **_Tiled Maps_** - Ao longo do desenvolvimento deste projecto procurou-se generalizar ao máximo as suas opções, isto é, não restrigir as suas funcionalidades. Assim recorreu-se ao uso de tiled maps para construção do mundo, sendo que se pretendido, é possível criar uma infinidade de mundos para o jogo, pois este adapta-se aos diferentes mundos criados desde que um conjunto de determinadas condições sejam respeitadas. Estas condições encontram-se não só documentadas ao longo do código fonte do projeto mas também no próprio tiled map usado como mundo atual do jogo.
  
## Major Difficulties
-----

Consideramos que todo o desenvolvimento deste projeto foi um constante desafio às nossas capacidades, uma vez que, lidamos com um ambiente de programação totalmente diferente do qual estavamos habituados. No entanto a parte de **_Networking_** foi a mais desafiante uma vez que toda ela consistiu apenas em pesquisa, dado que tal assunto não foi abordado em nenhuma das cadeiras até agora frequentadas.

## Work Distribution - Overall Time Spent 
-----

Na realização deste trabalho ambos os membros do grupo participaram ativamente e cumpriram devidamente as suas tarefas. O grupo optou por distribuir as tarefas uniformemente com vista aumentar a produtividade, no entanto, esta distribuição teve em conta as áreas em que cada elemento se sentia mais à vontade, permitindo assim que a percentagem de esforço de cada elemento fosse igual. 

#### Rui Jorge Leão Guedes <br>

* Horas de trabalho = 120
* Distribuição de tarefas = 50%

#### César Alexandre da Costa Pinho <br>

* Horas de trabalho = 90
* Distribuição de tarefas = 50%

## Online Project Documentation
-----

A documentação do código fonte do presente projecto encontra-se disponível através do seguinte link:

* **_Github Pages_** - https://ruiguedes.github.io/LPOO1718_T4G12/

## Lessons Learned
-----

A edificação deste projeto contribuiu para o desenvolvimento das nossas capacidades a nível de trabalho em equipa, pesquisa pro-ativa, estudo autodidato e, principalmente, para a aprendizagem de Android, Java e Networking.

## User Manual - GUI Design 
-----

  De seguida encontra-se especificado simultãneamente a interface gráfica do jogo desenvolvido bem como o seu manual de utilização, isto é, como proceder para utilização do mesmo. 

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual1.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual2.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual3.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual4.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual5.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual6.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual7.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual8.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual9.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual10.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual11.png)

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/UserManual12.png)


## UML class diagram 
-----

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/Delivery/Diagrama%20UML.png)

## State diagram :: Behavioural aspects
-----

![alt text](https://github.com/RuiGuedes/LPOO1718_T4G12/blob/master/Unstoppable_Bros/FinalRelease/Screenshots/StateDiagram.png)

## Design Patterns
-----
No desenvolvimento do jogo, com vista a aumentar a qualidade e simplicidade do código, optaram-se por usar os seguintes **_design patterns_**:

* **_Singleton_** - Utilizado nas classes _GameController_, _GameModel_ e _GameView_, uma vez que destas só se pretende ter uma instância das mesmas.
* **_Observer_** - Existem diversas relações entre os diferentes objetos no jogo, desde balas a inimigos, personagens, entre outros, que implicam por sua vez, a necessidade gerir a sua interação com os restantes objetos. Este design pattern encontra-se definido na classe _MyContactListener_.
* **_Flyweight_** - Utilizado para representar os vários inimigos existentes no jogo, uma vez que, todos partilham entre si o mesmo tipo de representação gráfica.
* **_Update Method_** - Os inimigos possuem movimento próprio, daí a necessidade deste padrão de desenho com vista a não bloquear o jogo aquando o movimento destes.
