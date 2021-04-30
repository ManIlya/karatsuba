package ru.vsu.cs.manukovsky;

import java.math.*;
import java.io.*;
import java.util.Random;

public class Main{
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        BigInteger a = new BigInteger(64, random);
        BigInteger b = new BigInteger(64, random);
        BigInteger c = karMult(a, b);
        BigInteger d = a.multiply(b);

        System.out.println((c.equals(d)));
    }
    public static BigInteger karMult(BigInteger x, BigInteger y) {
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000) return x.multiply(y);
        N = (N / 2) + (N % 2);
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));
        BigInteger ac = karMult(a, c);
        BigInteger bd = karMult(b, d);
        BigInteger abcd = karMult(a.add(b), c.add(d));
        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2*N));
    }
}
