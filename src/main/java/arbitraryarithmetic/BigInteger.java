/*This is the class which my AInteger class uses to perform calculations on the AIntegers.*/

//Declaring that this file is present in the predefined package arbitraryarithmetic. 
//A package is set of organized related classes, interfaces in java.
package arbitraryarithmetic;

//This class is used to perform operations on arbitrary precision integers of arbitrarily large or small values.
public class BigInteger {
    // Stores digits as string, always in base 10, no prefix/suffix
    private String value;
    // We use this boolean to check the sign of the BigInteger
    private boolean isNegative;

    // Definition of BigInteger.ZERO as "0".
    public static final BigInteger ZERO = new BigInteger("0");
    // Checking if the BigInteger is negative or not.
    public BigInteger(String s) {
        // If the BigInteger is negative, we remove the negative sign (to parse the remaining integer 
        //and perform operations on it). 
        if (s.charAt(0) == '-') {
            // We set the boolean isNegative to true.
            isNegative = true;
            // We remove the negative sign from the string to parse the remaining integer by setting 
            //the value of s to the substring of s from index 1 to the end.
            s = s.substring(1);
        } 
        //Else, if the BigInteger is positive, we set the boolean isNegative to false.
        else {
            // We set the boolean isNegative to false.
            isNegative = false;
        }
        // We trim the leading zeros from the string to get the value of the BigInteger in a nice format.
        // Ex: 0024 is trimmed to 24.
        value = trimLeadingZeros(s);
    }

    // Copy constructor to create a new BigInteger from another BigInteger
    public BigInteger(BigInteger other) {
        // We set the value of the new BigInteger to the value of the other BigInteger.
        this.value = other.value;
        // The isNegative of the new BigInteger is set to the isNegative of the other BigInteger as they have the same sign.
        this.isNegative = other.isNegative;
    }

    // Function to parse two integers in the string format and add them
    public BigInteger add(BigInteger other) {
        // Dealing with the case when both BigIntegers are either positive or negative.
        if (this.isNegative == other.isNegative) {
            // Finding the sum of the two BigIntegers using the addStrings function defined below.
            String sum = addStrings(this.value, other.value);
            // The if condition sets the sign of the sum as the sign of the two BigIntegers (they are same)
            return new BigInteger((this.isNegative ? "-" : "") + sum);
        } 
        // Dealing with the case when one BigInteger is positive and the other is negative.
        else {
            // We compare the absolute values of the two BigIntegers this.value and other.value using the compareAbs 
            // function defined below.
            int cmp = compareAbs(this.value, other.value);
            // If the absolute values are equal, the compareAbs function return 0.
            // Hence, the sum of the two BigIntegers is 0, and we return it.
            if (cmp == 0) return ZERO;
            // If the absolute value of this.value is greater than other.value, then the compareAbs function returns 
            // a positive number. 
            if (cmp > 0) {
                // We subtract the smaller value from the larger value.
                String diff = subtractStrings(this.value, other.value);
                // The sign of the result is set to the sign of the larger value.
                // Ex: -10 + 5 = -(10 - 5) = -5, while 10 + (-5) = (10 - 5) = 5
                return new BigInteger((this.isNegative ? "-" : "") + diff);
            } 
            // If the absolute value of this.value is less than other.value, then the compareAbs function returns 
            // a negative number.
            else {
                // We subtract the smaller value from the larger value.
                String diff = subtractStrings(other.value, this.value);
                // The sign of the result is set to the sign of the larger value.
                // Ex: -10 + 5 = -(10 - 5) = -5, while 10 + (-5) = (10 - 5) = 5
                return new BigInteger((other.isNegative ? "-" : "") + diff);
            }
        }
    }

    // Function to parse two integers in the string format and subtract them
    public BigInteger subtract(BigInteger other) {
        // Defining a new BigInteger with the value of the other BigInteger.
        BigInteger newOther = new BigInteger(other);
        // We set the sign of the new BigInteger to the opposite of the other BigInteger.
        newOther.isNegative = !other.isNegative;
        // We now add the this BigInteger and the new BigInteger (which is the negative of the other BigInteger).
        // as addition of the negative of a number to a number is equal to the subtraction of the two numbers.
        return this.add(newOther);
    }

    // Function to parse two integers in the string format and multiply them
    public BigInteger multiply(BigInteger other) {
        // We multiply the two BigIntegers using the multiplyStrings function defined below.
        String product = multiplyStrings(this.value, other.value);
        // This boolean represents the sign of the result.
        boolean rNeg;
        if(this.isNegative != other.isNegative){
            // If the signs are different, we set the sign of the result to negative.
            rNeg = true;
        } else {
            // If the signs are same, we set the sign of the result to positive.
            rNeg = false;
        }
        // The sign of the product is set to the - or + based on the sign of the result.
        // Ex: -10 * 5 = -(10 * 5) = -50, while 10 * (-5) = (10 * -5) = -50
        return new BigInteger((rNeg ? "-" : "") + product);
    }

    // Function to parse two integers in the string format and divide them
    public BigInteger divide(BigInteger other) {
        // We check for the division by zero case and return 0.
        if (other.equals(ZERO)) return ZERO;
        // If the other BigInteger is not 0, we divide safely.
        String quotient = divideStrings(this.value, other.value);
        // This boolean represents the sign of the result.
        boolean rNeg;
        if(this.isNegative != other.isNegative){
            // If the signs are different, we set the sign of the result to negative.
            rNeg = true;
        } else {
            // If the signs are same, we set the sign of the result to positive.
            rNeg = false;
        }
        // The sign of the product is set to the - or + based on the sign of the result.
        // Ex: -10 / 5 = -2, while 10 * (-5) = -50
        return new BigInteger((rNeg ? "-" : "") + quotient);
    }

    // Function to check if a BigIntegers and an Object o are equal or not.
    public boolean equals(Object o) {
        // Checks if the this BigInteger is equal to the other Object o.
        if (this == o) return true;
        // If the Object o is not an instance of the BigInteger class, we return false.
        if (!(o instanceof BigInteger)) return false;
        // Else, we create a new BigInteger object other with the value of the Object o.
        BigInteger other = (BigInteger) o;
        // We check if the value of the this BigInteger is equal to the value of the other BigInteger (in string type).
        return this.toString().equals(other.toString());
    }

    // I override the toString method to return the string representation of the BigInteger in the format I want.
    @Override
    public String toString() {
        // If the value is not 0, and isNegative is true, we set the sign of the value to negative.
        // Else, we set the sign of the value to positive.
        // If the value is 0, we return 0, unsigned.
        return (isNegative && !value.equals("0") ? "-" : "") + value;
    }

    //This function is used to trim the leading zeros from the string.
    // Ex: 0024 is trimmed to 24.
    private static String trimLeadingZeros(String s) {
        int i = 0;
        // We check for the leading zeros in the string and increment i until we find a non-zero character.
        while (i < s.length() - 1 && s.charAt(i) == '0') i++;
        // We return the substring of s which starts from the first non-zero character.
        return s.substring(i);
    }

    // Function to add two strings representing integers
    private static String addStrings(String a, String b) {
        // StringBuilder is used to create the string that is used to store the result of the sum.
        StringBuilder sb = new StringBuilder();
        // We find the length of the two strings and set i and j to the last index of the two strings.
        // We also set the carry to 0.
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        // While loop is used to carry out the addition of the two strings.
        while (i >= 0 || j >= 0 || carry > 0) {
            // If the ith and jth indices are valid, we get the digit at that index and subtract '0' to convert it to an integer.
            // If the index is invalid, we set the digit to 0.
            int d1 = i >= 0 ? a.charAt(i--) - '0' : 0;
            int d2 = j >= 0 ? b.charAt(j--) - '0' : 0;
            // We find the sum of the two digits and the carry.
            int sum = d1 + d2 + carry;
            // We append the last digit of the sum to the StringBuilder and set the carry to the first digit of the sum.
            sb.append(sum % 10);
            // We set the carry to the first digit of the sum.
            carry = sum / 10;
        }
        //Since we stored the digits in reverse order, we need to reverse the string to get the correct result.
        // We reverse the string and return it.
        return sb.reverse().toString();
    }

    // Function to subtract two strings representing integers
    private static String subtractStrings(String a, String b) {
        // assumes a >= b
        // StringBuilder is used to create the string that is used to store the result of the subtraction.
        StringBuilder sb = new StringBuilder();
        // We find the length of the two strings and set i and j to the last index of the two strings.
        // We also set the borrow to 0.
        int i = a.length() - 1, j = b.length() - 1, borrow = 0;
        // While loop is used to carry out the subtraction of the two strings.
        while (i >= 0) {
            // If the ith and jth indices are valid, we get the digit at that index and subtract '0' to convert it to an integer.
            int d1 = a.charAt(i) - '0';
            int d2 = j >= 0 ? b.charAt(j) - '0' : 0;
            // We find the difference of the two digits and the borrow.
            int diff = d1 - d2 - borrow;
            // If the difference is negative, we need to borrow from the next digit.
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } 
            // If the difference is positive, we set the borrow to 0.
            else borrow = 0;
            // We append the difference to the StringBuilder.
            sb.append(diff);
            // We decrement the indices.
            i--; j--;
        }
        // Since we stored the digits in reverse order, we need to reverse the string to get the correct result.
        // We also trim leading zeroes which form as subtract a small number from a large number.
        return trimLeadingZeros(sb.reverse().toString());
    }

    // Function to multiply two strings representing integers
    private static String multiplyStrings(String a, String b) {
        // The product of the two numbers will be at most the sum of the lengths of the two numbers.
        // We dont initialize result, hence all the values are 0.
        int[] result = new int[a.length() + b.length()];
        // We multiply the two numbers digit by digit and store the result in the result array.
        for (int i = a.length() - 1; i >= 0; i--) {
            for (int j = b.length() - 1; j >= 0; j--) {
                // We find the product of the two digits and add it to the result array.
                int mul = (a.charAt(i) - '0') * (b.charAt(j) - '0');
                // We find the sum of the product and the value at the current index (carryover) in the result array.
                int sum = result[i + j + 1] + mul;
                // Setting result[i + j + 1] as the last digit of the product.
                result[i + j + 1] = sum % 10;
                // Setting result[i + j] as the carryover.
                result[i + j] += sum / 10;
            }
        }
        // StringBuilder is used to create the string that is used to store the result of the multiplication.
        StringBuilder sb = new StringBuilder();
        // We iterate through the result array and append the digits to the StringBuilder.
        for (int num : result) sb.append(num);
        // We trim leading zeroes which form as multiplying a small number with a large number.
        // Ex: 0 * 100 = 0, 0 * 1000 = 0, etc.
        return trimLeadingZeros(sb.toString());
    }

    // Function to divide two strings representing integers.
    private static String divideStrings(String dividend, String divisor) {
        // We check if we are dividing a smaller number by a larger number.
        if (compareAbs(dividend, divisor) < 0) return "0";
        // StringBuilder is used to create the string that is used to store the quotient.
        StringBuilder result = new StringBuilder();
        // We set the current value to "".
        String current = "";
        // We iterate through the dividend and divide it by the divisor.
        for (int i = 0; i < dividend.length(); i++) {
            // We append the current digit to the current value.
            current += dividend.charAt(i);
            // We trim the leading zeroes from the current value as they dont add any value to the number.
            current = trimLeadingZeros(current);
            // Number of times the divisor can be subtracted from the current value.
            int count = 0;
            // While loop is used to subtract the divisor from the current value until it is less than the divisor.
            while (compareAbs(current, divisor) >= 0) {
                current = subtractStrings(current, divisor);
                count++;
            }
            // We append the count to the result. The count is the quotient of the current value and the divisor.
            result.append(count);
        }
        // We trim leading zeroes from the result.
        return trimLeadingZeros(result.toString());
    }

    // Function to compare two strings representing integers
    private static int compareAbs(String a, String b) {
        // If the lengths are different, we compare the lengths. The one with the greater length is greater.
        if (a.length() != b.length()) return Integer.compare(a.length(), b.length());
        // If the lengths are same, we compare the strings lexicographically, which is the same as comparing the digits.
        // The one with the greater digit is greater.
        return a.compareTo(b);
    }

    // toPlainString function is used to convert the BigInteger to a string format - the format in which the integer 
    // is returned by the functions.
    public String toPlainString() {
        return toString();
    }
    // Function to check if the BigInteger is zero
    public boolean isZero() {
        return value.equals("0");
    }

    // Functiion to check if the given BigInteger is equal to the other BigInteger. 
    public boolean equals(BigInteger other) {
        return this.toString().equals(other.toString());
    }
}