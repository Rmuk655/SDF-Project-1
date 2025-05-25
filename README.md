# SDF-Project-1 MyInfArithCalculator
# Arbitrary Precision Arithmetic Library
README
Overview
This library provides support for performing arithmetic operations on arbitrarily
large or small integers and floating-point numbers.
1. Compilation and Setup
The project uses Apache Ant for build management. To compile the project
and generate a runnable JAR file:
1. Ensure Apache Ant is installed and set up.
2. Run the following command in the root directory (where build.xml is
located):
ant jar
3. This will generate a file aarithmetic.jar in the build/jar directory.
2. Running the Program
To run the program using the generated JAR file, use the command:
java -jar build/jar/aarithmetic.jar <type> <operation> <operand1> <operand2>
Parameters:
• <type> — int for integers or float for floating-point numbers
• <operation> — add, sub, mul, or div
• <operand1> — the first number (as a string)
• <operand2> — the second number (as a string)
Example:
java -jar build/jar/aarithmetic.jar float div 25.5 4.2
8
3. Python Wrapper (Optional)
A helper Python script is provided to simplify execution:
python python_script.py <int/float> <add/sub/mul/div> <operand1> <operand2>
Make sure the Python script is placed in the correct directory and Java is
installed on your system. This python script also builds the jar file using the
ant file,
4. Notes
• Floating-point operations are computed up to a precision of 30 decimal
places.
• Inputs must be valid numbers in string format.
• Supported operations: add, sub, mul, div.
