package programmers.level1;

public class CaesarCrypto {
    public static void main(String[] args) {
        char a = 'A';
        Integer integer = new Integer(a);
        System.out.println(integer);
        //대문자 A 65
        //소문자 a 97
        //대문자 Z 90
        //소문자 z 122
        //공백 32

        //소문자 범위 97 ~ 122
        //대문자 범위 65 ~ 90
        byte b = integer.byteValue();
        byte[] bytes = new byte[1];
        bytes[0] = b;
        String s = new String(bytes);
        System.out.println(s);

    }

    private static class Solution {
        public String solution(String s, int n) {
            StringBuffer sb = new StringBuffer();
            char[] charArr = s.toCharArray();
            for (char c : charArr) {
                Integer integer = new Integer(c);
                //1. 공백은 공백이다.
                if (integer == 32) {
                    sb.append(intToString(integer));
                } else {
                    int shiftInteger = integer + n;
                    if (integer >= 97 && integer <= 122) {// 소문자
                        if (shiftInteger > 122) {
                            int newShift = shiftInteger - 122;
                            shiftInteger = 96 + newShift;
                        }
                    }else if (integer >= 65 && integer <= 90) { //대문자
                        if (shiftInteger > 90) {
                            int newShift = shiftInteger - 90;
                            shiftInteger = 64 + newShift;
                        }
                    }
                    sb.append(intToString(shiftInteger));
                }

            }
            return sb.toString();
        }

        private String intToString(Integer n) {
            byte b = n.byteValue();
            byte[] bytes = new byte[1];
            bytes[0] = b;
            String s = new String(bytes);
            return s;
        }
    }
}
