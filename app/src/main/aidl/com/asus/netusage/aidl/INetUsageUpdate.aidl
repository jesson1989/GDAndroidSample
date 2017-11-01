// INetUsageUpdate.aidl
package com.asus.netusage.aidl;
import com.asus.netusage.aidl.NetUsageInfo;

// Declare any non-default types here with import statements

interface INetUsageUpdate {

    NetUsageInfo getNetInfo();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
