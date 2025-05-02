/**This file was used to test for different cases (as provided in the problem statement itself), to check if the code is working 
correctly. Though, in some test cases, the answers dont match exactly due to precision error (I have done a precise calculation 
upto 30 digits as stated in the question, but the real test cases only has a precise answer upto max 100-200 decimal places), it
helped me debug my code effectively to get the right answers.**/

public class MyInfArithTest {
    /*A runtests function that helps me run the tests and check if my answer matches the expected answers. */
    public static void runtests(String[] args){
        /*Storing the output of the calculation in the string a.*/
        String a = MyInfArith.MyInfArithCalc(args[0], args[1], args[2], args[3]);
        /*Checking if the output of the calculation is equal to the expected answer (args[4]).*/
        if(!a.equals(args[4])){
            System.err.println("Error: " + args[0] + "," + args[1] + "," + args[2] + "," + args[3] + "," + args[4] + "," + a);
        }
        /*If not, then we print the error message.*/
        else{
            System.out.println("Success");
        }
    }
    /*List of test cases*/
    public static void test() {
        /*Test cases provided in the problem document*/
        runtests(new String[] {"int", "add", "23650078224912949497310933240250", "42939783262467113798386384401498", 
        "66589861487380063295697317641748"});
        runtests(new String[]{"int", "sub", "3116511674006599806495512758577", "57745242300346381144446453884008", 
        "-54628730626339781337950941125431"});
        runtests(new String[]{"int", "mul", "14344163160445929942680697312322", "23017167694823904478474013730519", 
        "330162008905899217578310782382075660760972861550182008086155118"});
        runtests(new String[]{"int", "div", "8792726365283060579833950521677211", "493835253617089647454998358", "17804979"});
        runtests(new String[]{"float", "div", "8792726365283060579833950521677211.0", "493835253617089647454998358", 
        "17804979.091469989302961159520087878533"});
        runtests(new String[]{"float", "add", "84486723.420039", "70974199.843732", "155460923.263771"});
        runtests(new String[]{"float", "sub", "840196454.51725", "712586963.70283", "127609490.81442"});
        runtests(new String[]{"float", "mul", "6400251.9377695", "2326541.6827934", "14890452913599.97174572532130"});
        runtests(new String[]{"float", "div", "244727.15202", "75964.3891", "3.221603634537752111008551505615"});
        runtests(new String[]{"int", "div", "25", "123", "0"});
        runtests(new String[]{"float", "div", "3227", "555", "5.814414414414414414414414414414"});
        runtests(new String[]{"float", "div", "5.5", "2", "2.750000000000000000000000000000"});
        runtests(new String[]{"int", "div", "2", "0", "Division by zero error"});
        /*Additional test cases I created and used to check my code.*/
        runtests(new String[]{"int", "add", "-0", "0", "0"});
        runtests(new String[]{"float", "sub", "-0.0000000", "-0.0", "0.0000000"});
        runtests(new String[]{"float", "mul", "1", "0", "0"});
        runtests(new String[]{"float", "add", "1.00000000", "-1.000", "0.00000000"});
        runtests(new String[]{"int", "sub", "-1", "-2", "1"});
        runtests(new String[]{"float", "mul", "-0.00000", "0.000000", "0.00000000000"});
    }
}