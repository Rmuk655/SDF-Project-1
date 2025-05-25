/**This class is used to perform operations on arbitrary precision decimals of arbitrarily large or small values.**/

//Declaring that this file is present in the predefined package arbitraryarithmetic. 
//A package is set of organized related classes, interfaces in java.
package arbitraryarithmetic;

//Main class AFloat which is used to initialize objects of class AFloat using the constructors and performing arithmetic operations 
//using methods.
public class AFloat {
    //We set the precision of all the results returned to 30.
    private int precision = 30;

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }
    //The value is given the type BigDecimal and is made private to protect the data and make sure others cannot access it. 
    //However, the functions of the given class can still access it through the getter and setter methods defined below.
    //This private data is said to be encapsulated.
    private BigDecimal value;
    //Default constructor which initializes the value of value with 0 (of type BigDecimal)
    // Default constructor which initializes the value of value with 0 (of type BigDecimal)
    public AFloat() {
        this.value = BigDecimal.ZERO;
        // Set scale for precision if needed, e.g.:
        // this.value = this.value.setScale(precision, RoundingMode.HALF_UP);
    }
    //Constructor used to initialize the value of an object of this class with the number inputted as a string while 
    //defining an object of the class.
    public AFloat(String s) {
   
            this.value = new BigDecimal(s);
        }
        
        
    
    //Copy constructor used to create an instance of an AFloat using a predefined value of another object of the same class.
    public AFloat(AFloat other) {
        this.value = other.value;
        
    }
    //Parse function which is used to return a newly created object of the AFloat class.
    public static AFloat parse(String s) {
        return new AFloat(s);
    }
    //Getter function used to get the value of the given object of the class (as the value is initially set as private).
    public BigDecimal getVal(){
        return value;
    }
    //Setter function used to set the value of a given object of the class to a given inputted value.
    public void setVal(BigDecimal value1){
        value = value1;
    }
    //We override the definition of the toString function implemented in the universal Java class (object class) to a new definition
    //which converts the given value of type BigDecimal to a string (using the toString() function definition for the BigDecimal class) 
    //and returns it.
    @Override
    public String toString(){
        //We strip the unncessary trailing zeros.
        //value = value.stripTrailingZeros();
        return value.toString();
    }
    //Definition of addition of 2 AFloats using the addition of 2 BigDecimals definition already pre-defined in the BigDecimal class.
    public AFloat add(AFloat other) {
        //We set the precision of all the results returned to 30.
        BigDecimal result = this.value.add(other.value);
        return new AFloat(result.toString());
    }
    //Definition of subtraction of 2 AFloats using the subtraction of 2 BigDecimals definition already pre-defined in the BigDecimal 
    //class.
    public AFloat sub(AFloat other) {
        //We set the precision of all the results returned to 30.
        BigDecimal result = this.value.subtract(other.value);
        return new AFloat(result.toString());
    }
    //Definition of multiplication of 2 AFloats using the multiplication of 2 BigDecimals definition already pre-defined in the
    //BigDecimal class.
    public AFloat mul(AFloat other) {
        //We set the precision of all the results returned to 30.

        BigDecimal result = this.value.multiply(other.value);
        return new AFloat(result.toString());
    }
    //Definition of division of 2 AFloats using the division of 2 BigDecimals definition already pre-defined in the BigDecimal class.
    public AFloat div(AFloat other) {
        //We use the if condition to check for the division by 0 case which is undefined. The newInteger returned is not of any use,
        //though we still return it (with value 0) as it is a part of a function whose return type is an AFloat.
        if (other.getVal().isZero()) {
            return new AFloat("0");
        } else {
            BigDecimal result = this.value.divide(other.value);
            //result = result.setScale(precision, RoundingMode.DOWN);
            return new AFloat(result.toString());
        }
    }
}
