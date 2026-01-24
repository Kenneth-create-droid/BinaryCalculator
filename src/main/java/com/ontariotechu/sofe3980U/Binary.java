package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 */
public class Binary {
    private String number = "0"; // string containing the binary value '0' or '1'

    /**
     * A constructor that generates a binary object.
     *
     * @param number a String of the binary values. It should contain only zeros or ones with any length and order.
     *               otherwise, the value of "0" will be stored. Leading zeros will be excluded and empty string will be considered as zero.
     */
    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0";
            return;
        }

        // Validate the binary string (only '0' or '1' allowed)
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0";
                return;
            }
        }

        // Remove leading zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }

        // If all digits are '0', ensure number is "0"
        this.number = (beg == number.length()) ? "0" : number.substring(beg);

        // replace empty strings with a single zero (safety)
        if (this.number.isEmpty()) {
            this.number = "0";
        }
    }

    /**
     * Return the binary value of the variable
     *
     * @return the binary value in a string format.
     */
    public String getValue() {
        return this.number;
    }

    /**
     * Adding two binary variables.
     *
     * @param num1 The first addend object
     * @param num2 The second addend object
     * @return A binary variable with a value of num1+num2.
     */
    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;

        int carry = 0;
        String num3 = "";

        while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
            int sum = carry;

            if (ind1 >= 0) {
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
                ind1--;
            }
            if (ind2 >= 0) {
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
                ind2--;
            }

            carry = sum / 2;
            sum = sum % 2;

            num3 = ((sum == 0) ? "0" : "1") + num3;
        }

        return new Binary(num3);
    }

    /* =========================================================
       NEW FUNCTIONS REQUIRED: OR, AND, MULTIPLY
       ========================================================= */

    /**
     * Bitwise logical OR between two binary variables.
     * Pads the shorter number with leading zeros, performs OR, returns trimmed result.
     */
    public static Binary or(Binary num1, Binary num2) {
        String a = num1.number;
        String b = num2.number;

        int n = Math.max(a.length(), b.length());
        a = padLeftZeros(a, n);
        b = padLeftZeros(b, n);

        String result = "";
        for (int i = 0; i < n; i++) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);
            result += ((ca == '1' || cb == '1') ? '1' : '0');
        }

        return new Binary(trimLeadingZeros(result));
    }

    /**
     * Bitwise logical AND between two binary variables.
     * Pads the shorter number with leading zeros, performs AND, returns trimmed result.
     */
    public static Binary and(Binary num1, Binary num2) {
        String a = num1.number;
        String b = num2.number;

        int n = Math.max(a.length(), b.length());
        a = padLeftZeros(a, n);
        b = padLeftZeros(b, n);

        String result = "";
        for (int i = 0; i < n; i++) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);
            result += ((ca == '1' && cb == '1') ? '1' : '0');
        }

        return new Binary(trimLeadingZeros(result));
    }

    /**
     * Multiply two binary variables using shift-and-add.
     * Requirement note: uses add().
     */
    public static Binary multiply(Binary num1, Binary num2) {
        String a = trimLeadingZeros(num1.number);
        String b = trimLeadingZeros(num2.number);

        // quick exits
        if (a.equals("0") || b.equals("0")) return new Binary("0");
        if (a.equals("1")) return new Binary(b);
        if (b.equals("1")) return new Binary(a);

        Binary result = new Binary("0");

        // iterate multiplier bits from right to left
        for (int i = 0; i < b.length(); i++) {
            char bit = b.charAt(b.length() - 1 - i);
            if (bit == '1') {
                // shift multiplicand left by i (append i zeros)
                Binary shifted = new Binary(a + repeatChar('0', i));
                result = Binary.add(result, shifted);
            }
        }

        return result;
    }

    /* =========================================================
       PRIVATE HELPERS
       ========================================================= */

    private static String padLeftZeros(String s, int n) {
        if (s.length() >= n) return s;
        return repeatChar('0', n - s.length()) + s;
    }

    private static String trimLeadingZeros(String s) {
        int i = 0;
        while (i < s.length() - 1 && s.charAt(i) == '0') i++;
        return s.substring(i);
    }

    private static String repeatChar(char c, int times) {
        if (times <= 0) return "";
        String out = "";
        for (int i = 0; i < times; i++) out += c;
        return out;
    }
}
