# Main code to compile and run the MyInfArith Java file

#Importing the os and sys modules to help us compile and run the java code.
import os
import sys
# Step 1: Building the JAR file
build_command = "ant -f ../../../build.xml jar"
build_status = os.system(f"{build_command} > {os.devnull} 2>&1")
if build_status != 0:
    print("Build failed. Please check your build.xml or Ant setup.")
    exit(1)

# Step 2: run the required tests
args = sys.argv[1:]  # Exclude the script name
if len(args) == 0:
    run1 = f"java -jar ../../../build/jar/aarithmetic.jar"
    result_run = os.system(run1)
    if(result_run != 0):
        print("Error while running the tests")
        exit(1)
    else:
    #Printing the output of the tests
        output = os.popen(run1).read()
        print("The tests completed successfully")
        exit(1)
# Step 3: Checking command-line arguments

if len(args) != 4:
    print("Usage: python_script.py <int/float> <add/sub/mul/div> <operand1> <operand2>")
    exit(1)

# Step 4: Prepare and run Java command
run1 = f"java -jar ../../../build/jar/aarithmetic.jar {' '.join(args)}"
result_run = os.system(run1)
# Step 5: Handle errors or show output
if(result_run != 0):
    print("Error in the java program")
else:
    #Printing the output of the arithmetic operation performed by the java code.
    output = os.popen(run1).read()