# SIMULINK-VIEWER
## Description
It's tool which reads mdl file comes from SIMULINK and draw it.
## What is used?
GUI with javafx.
## Most Important Classes:
### APP:
- The Main class of the program which has pane scene and recieve the mdl file.
### Block:
- Parent class of Blocks in which we detect the type of block , then call the type class.
### Lines:
- Parent class of Lines in which we detect the type of line (Branched or NonBranched) , then call the type class.
- Also , it has 2 static methods : one to draw normal line with our style and the other to draw line with arrow.
## Contributions:
### Yousif Hazim Nazar 2001741
- AddBlock Class.
- nonBranchedLine Class.
- main Application
### Mohamed Abdel Hamid 2001087
- Line Class.
- BranchedLine Class.
- ConstantBlock Class.
### Ahmed Gamal Helmy 2000082
- Block Class.
- Application interface.
- SaturationBlock Class.
### Kirollos George Louis 2000356
- Scope Class
- Unit Delay Class
- main Application

![Interface](https://github.com/ahmdhlm/SIMULINK-VIEWER/assets/111571591/7e98b148-6147-487b-9766-2150cdb3c197)
![Simulink](https://github.com/ahmdhlm/SIMULINK-VIEWER/assets/111571591/898c7968-ed07-435f-9740-c80d3657a282)
