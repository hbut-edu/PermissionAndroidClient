package cn.edu.hbut.liujinhang.permissionandroidclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.edu.hbut.liujinhang.permissionandroidclient.R;
import cn.edu.hbut.liujinhang.permissionandroidclient.entity.Permission;

public class PermissionAdapter extends RecyclerView.Adapter <PermissionAdapter.ViewHolder>{

    private final List<Permission> permissionList;

    public PermissionAdapter(List<Permission> permissionList){
        this.permissionList =  permissionList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDepartmentName;
        TextView txtPermissionType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDepartmentName = itemView.findViewById(R.id.txtDepartmentName);
            txtPermissionType = itemView.findViewById(R.id.txtPermissionType);
        }
    }

    @NonNull
    @Override
    public PermissionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.permission, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PermissionAdapter.ViewHolder holder, int position) {
        holder.txtDepartmentName.setText(this.permissionList.get(position).getDepartment().getName());
        holder.txtPermissionType.setText(this.permissionList.get(position).getPermissionType());
    }

    @Override
    public int getItemCount() {
        return this.permissionList.size();
    }
}
