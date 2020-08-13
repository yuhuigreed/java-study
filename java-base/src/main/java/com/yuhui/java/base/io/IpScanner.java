package com.yuhui.java.base.io;

import org.springframework.util.StringUtils;

import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author yuhui
 */
public class IpScanner {

    private static ExecutorService pool = Executors.newFixedThreadPool(30);

    public static void main(String[] args) {
        try {
            InetAddress address = getLocalHostLanAddress();
            byte[] ip = address.getAddress();
            //创建一个任务列表
            List<FutureTask<String>> taskList = new ArrayList<>();
            CountDownLatch latch = new CountDownLatch(254);
            for (int i = 1; i <= 254; i++) {
                ip[3] = (byte) i;
                ScanIpTask task = new ScanIpTask(ip,latch);
                FutureTask<String> f = new FutureTask<>(task);
                taskList.add(f);
                pool.submit(f);
            }
            latch.await();
            for (FutureTask<String> task : taskList) {
                String result = task.get();
                if (!StringUtils.isEmpty(result)) {
                    System.out.println(result);
                }
            }
            pool.isShutdown();
            System.out.println("主线程结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 正确的IP拿法，即优先拿site-local地址
    private static InetAddress getLocalHostLanAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress;
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }


    static class ScanIpTask implements Callable<String> {
        private byte[] ip;

        private CountDownLatch latch;

        ScanIpTask(byte[] ip, CountDownLatch latch) {
            this.ip = ip;
            this.latch = latch;
        }

        @Override
        public String call() {
            try {
                InetAddress address = InetAddress.getByAddress(ip);
                //可用，不可用
                if (address.isReachable(200)) {
                    return address.getHostName();
                } else {
                    return "";
                }
            } catch (Exception e) {
                return "";
            } finally {
                latch.countDown();
            }
        }
    }


}

