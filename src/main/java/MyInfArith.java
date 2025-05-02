/**This class is used to run the main code for performing operations on arbitrarily large/small and precise integers/decimals.**/

//Importing the BigDecimal and BigInteger classes from my package. 
import arbitraryarithmetic.BigDecimal;
import arbitraryarithmetic.BigInteger;

public class MyInfArith {
    //This class is used to perform the required calculations by accepting the input as 4 strings (namely type, operand, value1, value2)
    //with type representing if the calculation is done on an integer or float, operand specifying the operator and the value1 
    //specifying the first number and value2 the second number on which the operation is applied.
    public static String MyInfArithCalc(String type, String operand, String value1, String value2){
        //Checking if the first argument is an integer, so that you can perform BigInteger operations on the 2 numbers.
        if (type.equals("int")) {
            //Create two new instances of the AInteger class - val1, val2 which initialized to value1, value2 respectively,
            //both of which are of the type BigInteger.
            arbitraryarithmetic.AInteger val1 = new arbitraryarithmetic.AInteger(value1);
            arbitraryarithmetic.AInteger val2 = new arbitraryarithmetic.AInteger(value2);
            //We perform a switch case on the operand to determine whether to add, subtract, multiply or divide the two given numbers.
            //We perform an operation on the two vals - val1, val2 and convert the result to an AInteger. Now, we need to use the 
            //toString function to get the value of the resulting BigInteger in String form.
            switch (operand) {
                case "add":
                    return val1.add(val2).toString();
                case "sub":
                    return val1.sub(val2).toString();
                case "mul":
                    return val1.mul(val2).toString();
                case "div":
                //We use the if condition to check for the division by 0 case which is undefined.
                    if(val2.getVal().equals(BigInteger.ZERO)){
                        return  new String("Division by zero error");
                    }
                    else{
                        return val1.div(val2).toString();
                    }
                default:
                //If there is a mistake in the operand (if anything other than add, sub, mul, div is inputted), then we get the output 
                //"Invalid"
                    return "Usage: java MyInfArith <int/float> <add/sub/mul/div> <operand1> <operand2>";
            }
        } 
        //Checking if the first argument is an float, so that you can perform BigDecimal operations on the 2 numbers.
        else if (type.equals("float")) {
            //Create two new instances of the AFloat class - val1, val2 which initialized to value1, value2 respectively,
            //both of which are of the type BigDecimal.
            arbitraryarithmetic.AFloat valu1 = new arbitraryarithmetic.AFloat(value1);
            arbitraryarithmetic.AFloat valu2 = new arbitraryarithmetic.AFloat(value2);
            //We perform a switch case on the operand to determine whether to add, subtract, multiply or divide the two given numbers.
            //We perform an operation on the two vals - val1, val2 and convert the result to an AFloat. Now, we need to use the 
            //toString function to get the value of the resulting BigDecimal in String form.
            switch (operand) {
                case "add":
                    return valu1.add(valu2).toString();
                case "sub":
                    return valu1.sub(valu2).toString();
                case "mul":
                    return valu1.mul(valu2).toString();
                case "div":
                //We use the if condition to check for the division by 0 case which is undefined.
                    if (valu2.getVal().isZero()) {
                        return new String("Division by zero error");
                    } 
                    else{
                        return valu1.div(valu2).toString();
                    }
                default:
                //If there is a mistake in the operand (if anything other than add, sub, mul, div is inputted), then we get the output 
                //"Invalid"
                    return "Usage: java MyInfArith <int/float> <add/sub/mul/div> <operand1> <operand2>";
            }
        }
        //If there is a mistake in the type (if anything other than float, int is inputted), then we get the output "Invalid"
        return "Usage: java MyInfArith <int/float> <add/sub/mul/div> <operand1> <operand2>";
    }
    public static void main(String[] args) {
        //The given if statement is used for testing using the MyInfTest.java code and must be uncommented for the same.
        //It is used to check if the number of arguments inputted is 0 and if so, then it should run the MyInfArithTest code.
        if(args.length == 0){
            MyInfArithTest.test();
            return;
        }
        //If the number of arguments is not 4, it indicates and error and hence the given if condition displays the error message: 
        //"Usage: java MyInfArith <int/float> <add/sub/mul/div> <operand1> <operand2>"
        else if (args.length != 4){
            System.out.println("Usage: java MyInfArith <int/float> <add/sub/mul/div> <operand1> <operand2>");
            return;
        }
        //Setting the values of the arguments as args[0] (Argument 1), args[1] (Argument 2) and so on.
        String type = args[0];
        String operand = args[1];
        String value1 = args[2];
        String value2 = args[3];
        //Calling the MyInfArithCalc function defined above within the same class using the arguments defined as String variables above.
        System.out.println(MyInfArithCalc(type, operand, value1, value2));
    }
}
