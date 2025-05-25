/*This is the class which my Afloat class uses to perform calculations on the AFloats.
Interestingly, the BigDecimal class calls the functions of the BigInteger class to perform the operations on the BigDecimals.
*/

//Declaring that this file is present in the predefined package arbitraryarithmetic. 
package arbitraryarithmetic;

public class BigDecimal {
    /**The integer value of this BigDecimal, represented as a  BigInteger.
    This field holds the unscaled value of the number. (Like the value of 1234567 in 123.4567)*/
    private final BigInteger intVal;
    /**The scale of this {BigDecimal}, representing the number of digits to the right of the decimal point.
        * A positive value means the number has that many fractional digits; a zero or negative value means the number 
        * is an integer or has trailing zeros.*/
        private int precision = 30; // Default precision for all computations

        /** Setting the precision for all BigDecimal computations.
        @param p is the maximum number of significant digits to use*/
        public void setPrecision(int p) {
           precision = p;
        }

        /** Finds and outputs the current global precision.
        * @return the maximum number of significant digits used in computations*/
        public int getPrecision() {
           return precision;
        }
    /**A constant representing a BigDecimal value of zero with scale 0.*/
    private int scale;
    // We initialize the BigDecimal ZERO with a default value of 0.
    public static final BigDecimal ZERO = new BigDecimal("0");

    
/* Representation of 123.567
Input: "123.567"
Parsing Logic: The decimal point is found at index 3.
The number of digits after the decimal point (scale) is 3.
The decimal point is removed, resulting in the unscaled value "123567".
Representation: intVal = BigInteger("123567") scale = 3*/

    public BigDecimal(String s) {
        // Trimming the unnecessary leading and trailing spaces.
        s = s.trim();
        // Check if the number is negative
        boolean isNeg = s.startsWith("-");
        if (isNeg) s = s.substring(1); // strip minus for now
        // Checking if the number has a decimal point
        if (s.contains(".")) {
            // If the number has a decimal point, we need to split it into integer and fractional parts about the decimal point.
            int dotIndex = s.indexOf('.');
            // We find the scaling factor by counting the number of digits after the decimal point.
            int scaleDigits = s.length() - dotIndex - 1;
            // Splitting the string into two parts: before and after the decimal point, and join them without including
            // the decimal point itself.
            String raw = s.substring(0, dotIndex) + s.substring(dotIndex + 1);
            // If the number is negative, we append the minus sign to the raw value before the first digit of the number.
            intVal = new BigInteger((isNeg ? "-" : "") + raw);
            // The scale is set to the number of digits after the decimal point.
            scale = scaleDigits;
        } 
        // If the number does not have a decimal point, we treat it as an integer.
        else {
            // If the number is negative, we append the minus sign to the raw value before the first digit of the number.
            intVal = new BigInteger((isNeg ? "-" : "") + s);
            // The scale is set to 0, as there are no digits after the decimal point.
            scale = 0;
        }
    }

    /** A constructor to create a BigDecimal with a given unscaled value and scale.*/
    private BigDecimal(BigInteger intVal, int scale) {
        this.intVal = intVal;
        this.scale = scale;
    }
/*
Addition between 1.234 and 0.00005678 
1.234: intVal = BigInteger("1234") and scale = 3 
0.00005678: intVal = BigInteger("5678") scale = 8 
maxScale  = 8.; Both numbers are scaled to the maximum scale
For 1.234: = BigInteger("123400000").
For 0.00005678:= BigInteger("5678").
resultScaled = BigInteger("123400000") + BigInteger("5678") = BigInteger("123405678").
final intVal = BigInteger("123405678"). scale = 8.
The resulting BigDecimal represents the number 1.23405678:
*/
    /** Adds two BigDecimal numbers and returns the result as a new BigDecimal.*/
    public BigDecimal add(BigDecimal other) {
        // Find the maximum scale between the two numbers
        int maxScale = Math.max(this.scale, other.scale);
        // Scale both numbers to the maximum scale
        BigInteger thisScaled = this.intVal.multiply(new BigInteger(pow10(maxScale - this.scale)));
        BigInteger otherScaled = other.intVal.multiply(new BigInteger(pow10(maxScale - other.scale)));
        // We use the add function of the BigInteger class to add the two scaled values.
        return new BigDecimal(thisScaled.add(otherScaled), maxScale);
    }

    /** Subtracts two BigDecimal numbers and returns the result as a new BigDecimal.*/
    public BigDecimal subtract(BigDecimal other) {
        // Find the maximum scale between the two numbers.
        int maxScale = Math.max(this.scale, other.scale);
        // Scale both numbers to the maximum scale.
        BigInteger thisScaled = this.intVal.multiply(new BigInteger(pow10(maxScale - this.scale)));
        BigInteger otherScaled = other.intVal.multiply(new BigInteger(pow10(maxScale - other.scale)));
        // We use the subtract function of the BigInteger class to subtract the two scaled values.
        return new BigDecimal(thisScaled.subtract(otherScaled), maxScale);
    }

    /** Multiplies two BigDecimal numbers and returns the result as a new BigDecimal.*/
    public BigDecimal multiply(BigDecimal other) {
        // Multiply the scaled versions of the two numbers using the multiply function of the BigInteger class.
        // The scale of the result is the sum of the scales of the two numbers.
        return new BigDecimal(this.intVal.multiply(other.intVal), this.scale + other.scale);
    }

    /** Divides two BigDecimal numbers and returns the result as a new BigDecimal.*/
    public BigDecimal divide(BigDecimal other) {

        // Set the result scale to a higher value to ensure precision during division.
        // I am using a higher value for the scale during the division process.
        int resultScale = this.precision;
        // Divide the scaled version of the first number by the second number.
        BigInteger numerator = this.intVal.multiply(new BigInteger(pow10(resultScale + other.scale - this.scale)));
        BigInteger denominator = other.intVal;
        // Use the divide function of the BigInteger class to perform the division.
        BigInteger quotient = numerator.divide(denominator);
    // Create the result BigDecimal and return it.
    BigDecimal result = new BigDecimal(quotient, resultScale);
    return result;
    }

    /** A helper function to create a string representation of 10 raised to the power of exp.*/
    private String pow10(int exp) {
        StringBuilder sb = new StringBuilder("1");
        // Append the appropriate number of zeros to the string and return it.
        for (int i = 0; i < exp; i++) sb.append("0");
        return sb.toString();
    }

    //The toString function is used to convert the BigDecimal to a String for returning from the function.
    // We have to override the toString function of the Object class to convert the BigDecimal to a string.
    // I have printed out the raw value of the BigDecimal to debug my code. I have commented it out for now.
    @Override
    public String toString() {
        // Converting the BigInteger value of the BigDecimal to a string.
        String raw = intVal.toString();
        //System.out.println("raw: " + raw);
        // Check if the number is negative, and if it is, we need to remove the minus sign for now.
        boolean isNegative = raw.startsWith("-");
        if (isNegative) raw = raw.substring(1);
        // System.out.println("raw: " + raw);
        // If the scale is greater than the length of the raw value, we need to add leading zeros to the raw value.
        while (raw.length() <= scale) raw = "0" + raw;
        //System.out.println("raw: " + raw);
        // Store the result in a string.
        String result;
        // If the scale is 0, we do not need to add a decimal point.
        if (scale == 0) {
            result = raw;
            //System.out.println("raw: " + raw);
        } 
        // If the scale is greater than 0, we need to add a decimal point and split the raw value into two parts - 
        // integer and decimal. We concatenate them with a decimal point in between.
        else {
            String intPart = raw.substring(0, raw.length() - scale);
            //System.out.println("intPart " + intPart);
            String deciPart = raw.substring(raw.length() - scale);
            //System.out.println("deciPart: " + deciPart);
            result = intPart + "." + deciPart;
        }
        //System.out.println("result: " + result);
        // If the number is negative, we need to add the minus sign to it.
        if (isNegative) result = "-" + result;
        //System.out.println("result: " + result);
        // Clean trailing decimal zeros, but do not erase "0" entirely (Code is commented out for now - failed a few test cases)
        //result = result.replaceAll("\\.?0+$", "");
        //System.out.println("result: " + result);
        // If the result is empty or contains only a minus sign, we set it to "0". We then return the result.
        if (result.equals("") || result.equals("-")) result = "0";
        return result;
    }

    /** A getter function to get the unscaled value of this BigDecimal.*/
    public int getScale() {
        return scale;
    }

    /** A setter function to set the scale of this BigDecimal.*/
    public void setScale(int scale) {
        this.scale = scale;
    }

    // To check if the given variable of the BigDecimal type is zero
    public boolean isZero() {
        return this.intVal.equals(BigInteger.ZERO);
    }
    

}
