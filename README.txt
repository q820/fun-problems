10 prime coding challenge.

Solution:
1. Generate the first N prime numbers (achievable in O(N * (logN + loglogN)))
2. Multiply all said N numbers between themselves (O(N^2/2))
Total time complexity: O(N^2) which is the theoretical lower bound since we need to print N^2 elements.

Knows quirks:
1. Printing could be nicer

Setting up:
1. Have $JAVA_HOME set (min jdk1.7)
2. Have ant installed

Running:
- "ant" to build and compile all.
- "ant test" to run the unit tests
- "ant run" to run the program with 10 as default value
- "ant run -Dnumber=X" to run the program with N's value set to X 
