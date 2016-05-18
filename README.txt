10 prime coding challenge.

Solution:
1. Generate the first N prime numbers (achievable in O(N * (logN + loglogN)))
2. Multiply all said N numbers between themselves (O(N^2/2))
Total time complexity: O(N^2) which is the theoretical lower bound since we need to print N^2 elements.

Setting up:
Make sure the jdk in build.proprieties points to the correct location on your machine.

Running:
Run: "ant" to build and compile all.

Run: "ant run" to run the program with 10 as default value

Run: "ant run -Dnumber=X" to run the program with N's value set to X 
