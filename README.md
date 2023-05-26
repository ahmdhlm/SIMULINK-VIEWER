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
### Mohamed Abdel Hamid 2001087
- Line Class.
- BranchedLine Class.
- ConstantBlock Class.
### Ahmed Gamal Helmy 2000082
- Block Class.
- Application interface.
- SaturationBlock Class.
