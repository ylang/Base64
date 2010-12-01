/*
 * Copyright (c) 2010 Jason Howk (subaquatic.net)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.subaquatic.util;

import java.security.MessageDigest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jason Howk
 */
public class Base64Test {
    private static String input ;
    private static String output;
    private static byte[] digest;
    private static String encDigest;

    public Base64Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Standard String data.
        input = "The quick brown fox jumps over the lazy dog.";
        output = "VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZy4=";

        // Binary data.  Going to create a SHA-256 hash of the input above.
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        digest = md.digest(input.getBytes());
        for (byte b: digest) {
            if (b < 0) {
                System.out.format("%s",Integer.toHexString(0x000000ff & b));
            } else {
                System.out.format("%s",Integer.toHexString(b));
            }
            
        }
        System.out.printf("\n");
        encDigest = "71N/JciVv6eCUmUpqbY9l6pjFWTV14nCt2VEjIY1+2w=";
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of class Base64.
     */
    @Test
    public void testEncodeString() {
        System.out.println("Encoding String...");
        System.out.printf("\tINPUT: %s\n",input);
        String result = Base64.encode(input.getBytes());
        System.out.printf("\tRESULT: %s\n",result);
        assertEquals(output, result);
    }

    /**
     * Test of decode method, of class Base64.
     */
    @Test
    public void testDecodeString() {
        System.out.println("Decode String...");
        System.out.printf("\tINPUT: %s\n",output);
        String result = new String(Base64.decode(output));
        System.out.printf("\tRESULT: %s\n",result);
        assertEquals(input, result);
    }

    @Test
    public void testEncodeBinary() {
        System.out.println("Encoding binary data...");
        String result = Base64.encode(digest);
        System.out.printf("\tRESULT: %s\n",result);
        assertEquals(encDigest, result);
    }

    @Test
    public void testDecodeBinary() {
        System.out.println("Decoding binary data...");
        byte[] result = Base64.decode(encDigest);
        //System.out.printf("\tRESULT: %s\n",result);
        assertArrayEquals(digest, result);
    }
    

}