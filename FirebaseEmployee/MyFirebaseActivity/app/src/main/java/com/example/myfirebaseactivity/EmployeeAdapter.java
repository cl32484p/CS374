package com.example.myfirebaseactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private List<Employee> mEmployees;

    public EmployeeAdapter(List<Employee> employees) {
        mEmployees = employees;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = mEmployees.get(position);
        holder.mFirstNameTextView.setText(employee.getFirstName());
        holder.mLastNameTextView.setText(employee.getLastName());
    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mFirstNameTextView;
        public TextView mLastNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mFirstNameTextView = itemView.findViewById(R.id.first_name);
            mLastNameTextView = itemView.findViewById(R.id.last_name);
        }
    }
}