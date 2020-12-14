package com.zjw.apps3pluspro.adapter;import android.annotation.SuppressLint;import android.content.Context;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.BaseAdapter;import android.widget.TextView;import com.zjw.apps3pluspro.R;import com.zjw.apps3pluspro.sql.entity.MeasureSpo2Info;import com.zjw.apps3pluspro.utils.JavaUtil;import com.zjw.apps3pluspro.utils.MyTime;import java.util.ArrayList;import java.util.List;/** * 心电测量历史列表适配器 */public class MesureSpo2HistoryListAdapter extends BaseAdapter {    static class ViewHolder {        TextView list_spo2_mesure_value;        TextView list_spo2_mesure_date;    }    private List<MeasureSpo2Info> mMeasureSpo2Info;    private LayoutInflater mInflator;    private int type;    private Context context;    public MesureSpo2HistoryListAdapter(Context context) {        super();        this.mInflator = LayoutInflater.from(context);        mMeasureSpo2Info = new ArrayList<MeasureSpo2Info>();        this.context = context;        this.type = type;    }    public void addDevice(MeasureSpo2Info device) {        if (!mMeasureSpo2Info.contains(device)) {            mMeasureSpo2Info.add(device);        }    }    public MeasureSpo2Info getDevice(int position) {        return mMeasureSpo2Info.get(position);    }    public void RemoveDevice(int position) {    }    public void setDeviceList(List<MeasureSpo2Info> healt_info_list) {        mMeasureSpo2Info = healt_info_list;    }    public void RemoveAllDevice() {        mMeasureSpo2Info.removeAll(mMeasureSpo2Info);    }    public void clear() {        mMeasureSpo2Info.clear();    }    @Override    public int getCount() {        return mMeasureSpo2Info.size();    }    @Override    public Object getItem(int i) {        return mMeasureSpo2Info.get(i);    }    @Override    public long getItemId(int i) {        return i;    }    @SuppressLint("ResourceAsColor")    @Override    public View getView(int i, View view, ViewGroup viewGroup) {        ViewHolder viewHolder;        // General ListView optimization code.        if (view == null) {            view = mInflator.inflate(R.layout.listitem_spo2_mesure_history, null);            viewHolder = new ViewHolder();            viewHolder.list_spo2_mesure_value = (TextView) view.findViewById(R.id.list_spo2_mesure_value);            viewHolder.list_spo2_mesure_date = (TextView) view.findViewById(R.id.list_spo2_mesure_date);            view.setTag(viewHolder);        } else {            viewHolder = (ViewHolder) view.getTag();        }        MeasureSpo2Info myMeasureSpo2Info = mMeasureSpo2Info.get(i);        if (!JavaUtil.checkIsNull(myMeasureSpo2Info.getMeasure_time())) {            viewHolder.list_spo2_mesure_date.setText(MyTime.getMyData(myMeasureSpo2Info.getMeasure_time()));        } else {            viewHolder.list_spo2_mesure_date.setText("");        }        if (!JavaUtil.checkIsNull(myMeasureSpo2Info.getMeasure_spo2())) {            viewHolder.list_spo2_mesure_value.setText(myMeasureSpo2Info.getMeasure_spo2());        } else {            viewHolder.list_spo2_mesure_value.setText("");        }        return view;    }}