package com.xjx.pastecode.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

/**
 * there is introduction
 *
 * @author xjx
 * @date 2022/3/24 18:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encryption {
    private static final BigInteger ADD_AFTER_10 = BigInteger.ONE;
    private static final String ADD_AFTER_10_STRING = ADD_AFTER_10.toString();

    private BigInteger multiply;
    private BigInteger add;

    /**
     * 进制
     */
    private Integer radix;

    public Encryption(Long multiply, Long add, Integer radix) {
        this.multiply = BigInteger.valueOf(multiply);
        this.add = BigInteger.valueOf(add);
        this.radix = radix;
    }

    public String encryptId(Long id) {
        if (id == null) {
            return null;
        }
        BigInteger b1 = BigInteger.valueOf(id).multiply(multiply).add(add).multiply(BigInteger.TEN).add(ADD_AFTER_10);
        BigInteger b2 = new BigInteger(reverseString(b1.toString()));
        BigInteger b3 = b2.multiply(b2).add(add);
        return b3.toString(radix);
    }

    public Long decryptEid(String eid) {
        if (StringUtils.isBlank(eid)) {
            return null;
        }
        try {
            BigInteger b1 = new BigInteger(eid, radix).subtract(add);
            BigInteger b2 = b1.sqrt();
            if (!b2.multiply(b2).equals(b1)) {
                return null;
            }
            BigInteger b3 = new BigInteger(reverseString(b2.toString()));
            if (!b3.toString().endsWith(ADD_AFTER_10_STRING)) {
                return null;
            }

            BigInteger b4 = b3.subtract(ADD_AFTER_10).divide(BigInteger.TEN).subtract(add);
            BigInteger[] bigDecimals = b4.divideAndRemainder(multiply);
            if (!bigDecimals[1].equals(BigInteger.ZERO)) {
                return null;
            }

            return bigDecimals[0].longValue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String reverseString(String target) {
        StringBuilder sb = new StringBuilder();
        char[] targetArr = target.toCharArray();

        for (int index = target.length() - 1; index >= 0; index--) {
            sb.append(targetArr[index]);
        }


        return sb.toString();
    }
}
