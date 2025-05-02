/**This class is used to perform operations on arbitrary precision integers of arbitrarily large or small values.**/

//Declaring that this file is present in the predefined package arbitraryarithmetic. 
//A package is set of organized related classes, interfaces in java.
package arbitraryarithmetic;
//arbitrarily large integers.
//Main class AInteger which is used to initialize objects of class AInteger using the constructors and performing arithmetic operations 
//using methods.
public class AInteger {
    //The value is given the type BigInteger and is made private to protect the data and make sure others cannot access it. 
    //However, the functions of the given class can still access it through the getter and setter methods defined below.
    //This private data is said to be encapsulated.
    private BigInteger value;
    //Default constructor which initializes the value of value with 0 (of type BigInteger)
    public AInteger() {
        this.value = BigInteger.ZERO;
    }
    //Constructor used to initialize the value of an object of this class with the number inputted as a string while 
    //defining an object of the class.
    public AInteger(String s){
        value = new BigInteger(s);
    }
    //Copy constructor used to create an instance of an AInteger using a predefined value of another object of the same class.
    public AInteger(AInteger other) {
        this.value = other.value;
    }
    //Parse function which is used to return a newly created object of the AInteger class.
    public static AInteger parse(String s) {
        return new AInteger(s);
    }
    //Getter function used to get the value of the given object of the class (as the value is initially set as private).
    public BigInteger getVal(){
        return value;
    }
    //Setter function used to set the value of a given object of the class to a given inputted value.
    public void setVal(BigInteger value1){
        value = value1;
    }
    //We override the definition of the toString function implemented in the universal Java class (object class) to a new definition
    //which converts the given value of type BigInteger to a string (using the toString() function definition for the BigInteger class) 
    //and returns it.
    @Override
    public String toString(){
        return value.toString();
    }
    //Definition of addition of 2 AIntegers using the addition of 2 BigIntegers definition already pre-defined in the BigInteger class.
    public AInteger add(AInteger other) {
        BigInteger result = (this.value).add(other.getVal());
        return new AInteger(result.toString());
    }
    //Definition of subtraction of 2 AIntegers using the subtraction of 2 BigIntegers definition already pre-defined in the BigInteger 
    //class.
    public AInteger sub(AInteger other) {
        BigInteger result = (this.value).subtract(other.getVal());
        return new AInteger(result.toString());
    }
    //Definition of multiplication of 2 AIntegers using the multiplication of 2 BigIntegers definition already pre-defined in the
    //BigInteger class.
    public AInteger mul(AInteger other) {
        BigInteger result = (this.value).multiply(other.getVal());
        return new AInteger(result.toString());
    }
    //Definition of division of 2 AIntegers using the division of 2 BigIntegers definition already pre-defined in the BigInteger class.
    public AInteger div(AInteger other) {
        //We use the if condition to check for the division by 0 case which is undefined. The newInteger returned is not of any use,
        //though we still return it (with value 0) as it is a part of a function whose return type is an AInteger.
        if (other.getVal().equals(BigInteger.ZERO)) {
            return new AInteger();
        } else {
            BigInteger result = (this.value).divide(other.getVal());
            return new AInteger(result.toString());
        }
    }
}
