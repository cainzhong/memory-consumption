package com.cain.memory.consumption;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MemoryConsumption {
  private static List list = new ArrayList();
  private static int count = 0;

  public static void main(String args[]) throws InterruptedException {
    OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    //"mem.getTotalPhysicalMemorySize() >> 20" equals "runtime.freeMemory() / 1024 / 1024"
    System.out.println(String.format("Total RAM: %s MB", mem.getTotalPhysicalMemorySize() >> 20));

    while (true) {
      list.add(new byte[1024 * 1024]);//用实例变量申请1M内存，当方法执行完毕时，这个static的变量是不会被释放
      count++;
      if (count % 100 == 0) {
        System.out.println(String.format("Available RAM: %s MB", mem.getFreePhysicalMemorySize() >> 20));
        Thread.sleep(1000);
      }
    }
  }
}
