package com.zjw.apps3pluspro.module.device.entity;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import no.nordicsemi.android.support.v18.scanner.ScanResult;

public class DeviceModel implements Parcelable {

    public BluetoothDevice device;
    public  int rssi;
    public int mumber;
    public String name;
    public String address;


    public DeviceModel(@NonNull final ScanResult scanResult) {
        this.device = scanResult.getDevice();
        this.name = device.getName();
        this.address = device.getAddress();
//        this.name = scanResult.getScanRecord() != null ? scanResult.getScanRecord().getDeviceName() : null;
        this.rssi = scanResult.getRssi();
    }

    protected DeviceModel(Parcel in) {
        device = in.readParcelable(BluetoothDevice.class.getClassLoader());
        rssi = in.readInt();
        mumber = in.readInt();
        name = in.readString();
        address = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(device, flags);
        dest.writeInt(rssi);
        dest.writeInt(mumber);
        dest.writeString(name);
        dest.writeString(address);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeviceModel> CREATOR = new Creator<DeviceModel>() {
        @Override
        public DeviceModel createFromParcel(Parcel in) {
            return new DeviceModel(in);
        }

        @Override
        public DeviceModel[] newArray(int size) {
            return new DeviceModel[size];
        }
    };

    public int getMumber() {
        return mumber;
    }

    public void setMumber(int mumber) {
        this.mumber = mumber;
    }

    public BluetoothDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    @Override
    public String toString() {
        return "DeviceModel{" +
                "device=" + device +
                ", rssi=" + rssi +
                ", mumber=" + mumber +
                '}';
    }
}
