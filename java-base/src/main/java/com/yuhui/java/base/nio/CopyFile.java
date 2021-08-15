package com.yuhui.java.base.nio;// $Id$

import com.yuhui.java.base.collections.ListTest;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.List;

/**
 * IBM社区文章
 * https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
 */
public class CopyFile {
    static public void main(String args[]) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java CopyFile infile outfile");
            System.exit(1);
        }

        List<String> list = new ArrayList<>();

        String infile = args[0];
        String outfile = args[1];

        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            buffer.clear();

            int r = fcin.read(buffer);

            if (r == -1) {
                break;
            }

            buffer.flip();

            fcout.write(buffer);
        }
    }
}
